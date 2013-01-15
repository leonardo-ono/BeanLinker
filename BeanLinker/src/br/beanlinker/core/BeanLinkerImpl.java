package br.beanlinker.core;

import br.beanlinker.updater.UpdateException;
import br.beanlinker.updater.Updater;
import br.beanlinker.link.CollectionLink;
import br.beanlinker.link.PropertyLink;
import br.beanlinker.link.Link;
import br.beanlinker.link.BeanLink;
import br.beanlinker.converter.ConversionException;
import br.beanlinker.validator.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Implementacao da Interface BeanLinker.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/12/2012 20:00)
 */
public class BeanLinkerImpl implements BeanLinker {
    
    private ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("JavaScript");

    private Map<String, String> registeredClasses = new HashMap<String, String>();
    private Map<String, String> classAliasMappings = new HashMap<String, String>();
    private Map<String, Set<Link>> links = new HashMap<String, Set<Link>>();

    private Map<String, String> onCreateNewInstanceExpressions = new HashMap<String, String>();
    private Map<String, String> onUpdateExpressions = new HashMap<String, String>();
    
    

    public BeanLinkerImpl() {
        jsEngine.put("bl", this);
    }

    public ScriptEngine getJsEngine() {
        return jsEngine;
    }

    public Map<String, Set<Link>> getLinks() {
        return links;
    }

    public Map<String, String> getRegisteredClasses() {
        return registeredClasses;
    }
    
    // TODO verificar so pode registrar uma unica vez uma classe
    // TODO verificar nao pode ter dois apelidos diferentes para uma mesma classe
    // TODO verificar classAlias deve obrigatoriamente comecar com letra maiuscula
    // TODO verificar classAlias caracter validos, mesma regra de declaracao das variaveis do java
    @Override
    public void registerClass(String classAlias, String className) {
        registeredClasses.put(classAlias, className);
        classAliasMappings.put(className, classAlias);
    }

    // OK
    @Override
    public void createAllNewInstances() {
        for (String classAlias : registeredClasses.keySet()) {
            String varName = classAlias.substring(0, 1).toLowerCase() + classAlias.substring(1);
            createNewInstance(varName, classAlias);
        }
    }

    // TODO verificar varName deve obrigatoriamente comecar com letra maiuscula
    // TODO verificar varName caracter validos, mesma regra de declaracao das variaveis do java
    @Override
    public void createNewInstance(String varName, String classAlias) {
        jsEngine.put(varName, createNewInstancePrivate(classAlias));
    }
    
    public Object createNewInstancePrivate(String classAlias) {
        Object newInstance = null;
        try {
            Class cl = Class.forName(registeredClasses.get(classAlias));
            newInstance = cl.newInstance();
            
            // Execute post oncreate expression
            String onCreateNewInstanceExpression = null;
            onCreateNewInstanceExpression = onCreateNewInstanceExpressions.get(classAlias);
            if (onCreateNewInstanceExpression != null) {
                jsEngine.put("newInstance", newInstance);
                jsEngine.eval(onCreateNewInstanceExpression);
                jsEngine.put("newInstance", null);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return newInstance;
    }

    private Set<Link> getLinks(String property) {
        String classAlias = property.split("\\.")[0];
        Set<Link> privLinks = links.get(classAlias);
        if (privLinks == null) {
            privLinks = new HashSet<Link>();
            links.put(classAlias, privLinks);
        }
        return privLinks;
    }
    
    // OK
    @Override
    public Link linkProperty(String leftProperty, String rightProperty
            , String conversor, String validator
            , String onConversionException, String onValidationException) {
        
        Set<Link> leftLinks = getLinks(leftProperty);
        Set<Link> rightLinks = getLinks(rightProperty);
        Link link = new PropertyLink(this,leftProperty, rightProperty, conversor, validator
                , onConversionException, onValidationException);
        
        leftLinks.add(link);
        rightLinks.add(link);
        return link;
    }    
    
    // OK
    @Override
    public Link linkBean(String leftProperty, String rightProperty) {
        Set<Link> leftLinks = getLinks(leftProperty);
        Set<Link> rightLinks = getLinks(rightProperty);
        Link link = new BeanLink(this,leftProperty, rightProperty);
        leftLinks.add(link);
        rightLinks.add(link);
        return link;
    }    

    // Class.property   <---> Class.property
    // Class.property   <---  instance.method() (method returns a bean)
    // instance.method() ---> Class.property    (method returns a bean)
    // Class.property   <---  Class.method()    (method returns a bean)
    // Class.method()    ---> Class.property       (method returns a bean)    
    @Override
    public Link linkCollection(String sourceCollectionProperty
            , String destinationCollectionProperty, String destinationClassAlias) {

        Set<Link> leftLinks = getLinks(sourceCollectionProperty);
        Set<Link> rightLinks = getLinks(destinationCollectionProperty);
        Link link = new CollectionLink(this, sourceCollectionProperty
                , destinationCollectionProperty, destinationClassAlias);
        
        leftLinks.add(link);
        rightLinks.add(link);
        return link;
    }
    
    // OK
    @Override
    public void assign(String varName, Object object) throws Exception {
        jsEngine.put(varName, object);
    }

    // OK
    @Override
    public Object eval(String expression) throws Exception {
        Object ret = null;
        ret = jsEngine.eval(expression);
        return ret;
    }    
    
    // OK
    @Override
    public synchronized void update(String sourceProperty
            , String destinationProperty, String ... regex) throws Exception {
        
        System.out.println("iniciando update ... " + Thread.currentThread().getId());
        
        List<Updater> updaters = new ArrayList<Updater>();
        Object sourceObject = eval(sourceProperty);
        Object destinationObject = eval(destinationProperty);
        updatePrivate(updaters, sourceObject, destinationObject, regex);

        if (updaters != null) {
            // Faz toda a conversao e validacao primeiro
            UpdateException updateException = null;
            for (Updater updater : updaters) {
                try {
                     updater.testConversion();
                     updater.testValidation();
                }
                catch (ConversionException ce) {
                    if (updateException == null) updateException = new UpdateException();
                    updateException.getConversionExceptions().add(ce);
                    ce.getUpdater().getUpdateInfo().evalOnConversionExceptionExpression(ce);
                }
                catch (ValidationException ve) {
                    if (updateException == null) updateException = new UpdateException();
                    updateException.getValidationExceptions().add(ve);
                    ve.getUpdater().getUpdateInfo().evalOnValidationExceptionExpression(ve);
                }
                catch (Exception e) {
                    throw e;
                }
            }
            if (updateException != null) {
                throw updateException;
            }
            for (Updater updater : updaters) {
                // System.out.println(updater);
                updater.update();
                
                // TODO Aqui esta errado, precisa chamar (ou marcar) depois que termina a atualizacao de um bean
                // Call on update expression 
                if (updater.getUpdateInfo().destinationObject == null) continue;
                String destinationClassAlias = getObjectClassAlias(updater.getUpdateInfo().destinationObject);
                String onUpdateExpression =  onUpdateExpressions.get(destinationClassAlias);
                if (onUpdateExpression == null) continue;
                assign("sourceObj", updater.getUpdateInfo().sourceObject);
                assign("destObj", updater.getUpdateInfo().destinationObject);
                System.out.println("executando onUpdateExpression " + onUpdateExpression);
                eval(onUpdateExpression);
                eval("delete destObj");
                eval("delete sourceObj");                    
            }
        }
        updaters = null;

        System.out.println("terminando update ... " + Thread.currentThread().getId());
        System.out.println("");
    }

    // updatePrivate pode ser:
    // "model" ---> "view" (instancias criadas atraves da funcao linker.createNewInstance("model", "Model")
    // "model.telefone" ---> "view.telefoneView" (pode informar expressoes tambem)
    // "collModelItem" ---> "collViewItem" sao instancias criadas pela parte do CollectionLink, 
    // so que ai informa os objetos sourceObject e destinationObject e isCollectionItemUpdate=true
    // Sera que criar updatePrivate(Object obja, Object objb) ja nao resolveria tudo ?
    public void updatePrivate(List<Updater> updaters
            , Object sourceObject, Object destinationObject,String ... regex) throws Exception {
        
        Set<Link> linksProv = links.get(getObjectClassAlias(destinationObject));
        if (linksProv == null) return;
        for (Link link : linksProv) {
            link.update(updaters, sourceObject, destinationObject, regex);
        }
    }
    
    // OK
    @Override
    public String getPropertyClassAlias(String property) {
        try {
            Object object = jsEngine.eval(property);
            return getObjectClassAlias(object);
        } catch (ScriptException ex) {
            //throw new RuntimeException(ex);
            System.out.println(ex);
        }
        return "";
    }
    
    @Override
    public String getObjectClassAlias(Object object) {
        String objectClassName = object.getClass().getName();
        String objectClassAlias = classAliasMappings.get(objectClassName);
        return objectClassAlias;
    }
    
    // OK
    @Override
    public void setOnCreateNewInstanceExpression(String classAlias, String expression) {
        onCreateNewInstanceExpressions.put(classAlias, expression);
    }

    // TODO
    @Override
    public void setOnUpdateExpression(String classAlias, String expression, String afterExpression) throws Exception {
        onUpdateExpressions.put(classAlias, expression);
    }
    
}
