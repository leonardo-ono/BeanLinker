package br.beanlinker.core;

import br.beanlinker.link.Link;

/**
 * Interface BeanLinker.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/12/2012 18:51)
 */
public interface BeanLinker {

    // verifyArgIsPropertyOrExpression
    // "1" -> expression
    // "'teste'" -> expression
    // "model.method()" -> expression
    // "model.nome" -> property
    
    public void registerClass(String classAlias, String className);
    public String getPropertyClassAlias(String property);
    public String getObjectClassAlias(Object object);
    public void createAllNewInstances();   
    public void createNewInstance(String varName, String classAlias);   
    public void setOnCreateNewInstanceExpression(String classAlias, String expression);

    // TODO precisa verifica se a propriedade existe ?
    // Class.property   <---> Class.property (assume que existem getters e setters para as duas propriedade, se nao existir, vai ocorrer uma excessao)
    // Class.property   <---  instance.method() (nao so metodo mas expressao tambem -> method(), 'string', 111 (numero)
    // instance.method() ---> Class.property
    // Class.property   <---  Class.method()
    // Class.method()    ---> Class.property
    public Link linkProperty(String leftProperty, String rightProperty, String conversorProperty, String validatorProperty, String onConversionException, String onValidationException);
    
    // TODO se destinationBean==null, criar uma nova instancia automaticamente ?
    // Class.property   <---> Class.property
    // Class.property   <---  instance.method() (method returns a bean)
    // instance.method() ---> Class.property    (method returns a bean)
    // Class.property   <---  Class.method()    (method returns a bean)
    // Class.method() ---> Class.property       (method returns a bean)
    public Link linkBean(String leftBeanProperty, String rightBeanProperty);
    
    // Implementation
    //                                                    source                            dest
    // check new item in source (unidirectional)                  * --> new instance -->  *             
    // check removed itens in source (unidirectional)       remove ---------------------> remove
    // update bean (bidirectional)                           * <----------------------------> *
    // public void linkCollection(String sourceCollectionProperty, String destinationCollectionProperty, String destinationClassAlias, String destinationVarGenerator, String sourceVarGenerator);
    public Link linkCollection(String sourceCollectionProperty, String destinationCollectionProperty, String destinationItemClassAlias);
    
    // policy source->conversor->validator->destination (vai para o proximo link) se der erro, prossegue ate o final ?
    //           roda todos conversores / roda todos validadores / por fim a atualizacao
    // pode definir a quantidade ocorrencia de erros antes de retornar
    // public void setUpdateMode(String updateMode, int maxErrorStop);

    // Regex exemplo:
    // linker.update("model", "view", ".*(telefone.(tipo|ddd|num.*)).*");
    public void update(String sourceProperty, String destinationProperty, String ... regex) throws Exception;
    public void setOnUpdateExpression(String classAlias, String beforeExpression, String afterExpression) throws Exception;
    
    public Object eval(String expression) throws Exception;
    public void assign(String var, Object object) throws Exception;

    // public Object getCollectionByItem(Object item)
    // public Object getAssociatedCollectionItem(Object item);
}

