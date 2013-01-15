package br.beanlinker.link;

import br.beanlinker.core.BeanLinker;
import br.beanlinker.updater.Updater;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class Link.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (02/01/2012 11:20)
 */
public abstract class Link {

    BeanLinker linker;
    String leftProperty;
    String rightProperty;

    public Link(BeanLinker linker) {
        this.linker = linker;
    }

    public BeanLinker getLinker() {
        return linker;
    }

    public String getLeftProperty() {
        return leftProperty;
    }

    public String getRightProperty() {
        return rightProperty;
    }

    public boolean hasGetter(String exp) {
        boolean ret;
        try {
            String property = exp.substring(exp.indexOf("."));
            String firstLetter = property.substring(0, 1);
            exp = exp.replaceFirst(firstLetter, firstLetter.toUpperCase());
            ret = (Boolean) linker.eval("(a.getName != null)");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return ret;
    }
    
    public boolean isClassProperty(String exp) {
        Pattern isClassPropertyPattern = Pattern.compile("^[A-Z]{1}[\\w]*((\\.[a-z]){1}\\w*)+");
        Matcher m = isClassPropertyPattern.matcher(exp);
        return m.matches();
    }

    public class ClassAliasReplacements {
        public String replacedExpr;
        public boolean classAlias1Replaced;
        public boolean classAlias2Replaced;
    }
    
    public ClassAliasReplacements replaceAllClassAlias(
            String expr, String classAlias1, String var1, String classAlias2, String var2) {
        
        Pattern extractAllClassAliasPattern = Pattern.compile("(^|(?<=\\W))[A-Z]{1}\\w*");
        int numberOfClassAliasFound = 0;
        ClassAliasReplacements cap = new ClassAliasReplacements();
        Matcher m = extractAllClassAliasPattern.matcher(expr);
        while (m.find()) {
            // System.out.println(m.group() + " hitend: " + m.hitEnd());
            if (m.group().equals(classAlias1)) {
                expr = expr.replaceAll("(^|(?<=\\W))" + m.group() + "($|(?=\\W))", var1);
                cap.classAlias1Replaced = true;
            }
            else if (m.group().equals(classAlias2)) {
                expr = expr.replaceAll("(^|(?<=\\W))" + m.group() + "($|(?=\\W))", var2);
                cap.classAlias2Replaced = true;
            }
            numberOfClassAliasFound++;
        }
        if (numberOfClassAliasFound>2) {
            throw new RuntimeException("Found more than 2 class alias !");
        }
        cap.replacedExpr = expr;
        return cap;
    }
    
    public class UpdateInfo {
        
        public boolean needsUpdate = false;
        public String sourceExpression = "";
        public String destinationExpression = "";
        public Object sourceObject = null;
        public Object destinationObject = null;
        public String onConversionException = "";
        public String onValidationException = "";

        public UpdateInfo(Object sourceObject, Object destinationObject) {
            this.sourceObject = sourceObject;
            this.destinationObject = destinationObject;
        }
        
        public void evalOnValidationExceptionExpression(Exception exc) throws Exception {
            linker.assign("sourceObject", sourceObject);
            linker.assign("destinationObject", destinationObject);
            linker.assign("exception", exc);
            Object ret = linker.eval(onValidationException);
            linker.assign("exception", null);
            linker.assign("sourceObject", null);
            linker.assign("destinationObject", null);
        }
        
        public void evalOnConversionExceptionExpression(Exception exc) throws Exception {
            linker.assign("sourceObject", sourceObject);
            linker.assign("destinationObject", destinationObject);
            linker.assign("exception", exc);
            Object ret = linker.eval(onConversionException);
            linker.assign("exception", null);
            linker.assign("sourceObject", null);
            linker.assign("destinationObject", null);
        }

        public Object evalSourceExpression(String expr) throws Exception {
            linker.assign("sourceObject", sourceObject);
            linker.assign("destinationObject", destinationObject);
            Object ret = linker.eval(sourceExpression + expr);
            linker.assign("sourceObject", null);
            linker.assign("destinationObject", null);
            return ret;
        }

        public Object evalDestinationExpression(String expr) throws Exception {
            linker.assign("sourceObject", sourceObject);
            linker.assign("destinationObject", destinationObject);
            Object ret = linker.eval(destinationExpression + expr);
            linker.assign("sourceObject", null);
            linker.assign("destinationObject", null);
            return ret;
        }
    
    }

    public UpdateInfo generateUpdateInfo(
            Object sourceObject, Object destinationObject) throws Exception {

        UpdateInfo updateInfo = new UpdateInfo(sourceObject, destinationObject);
        String sourceClassAlias = linker.getObjectClassAlias(sourceObject);
        String destinationClassAlias = linker.getObjectClassAlias(destinationObject);

        ClassAliasReplacements leftRepl = replaceAllClassAlias(
                leftProperty, sourceClassAlias, "sourceObject", destinationClassAlias, "destinationObject");
        
        ClassAliasReplacements rightRepl = replaceAllClassAlias(
                rightProperty, sourceClassAlias, "sourceObject", destinationClassAlias, "destinationObject");
        
        if (!isClassProperty(leftProperty) && !isClassProperty(rightProperty)) {
            throw new RuntimeException("One of the expressions must be a class property !");
        }
        
        if (isClassProperty(leftProperty) && leftRepl.classAlias1Replaced) {
            updateInfo.sourceExpression = leftRepl.replacedExpr;
            updateInfo.destinationExpression = rightRepl.replacedExpr;
            updateInfo.needsUpdate = isClassProperty(rightProperty);
        }
        if (isClassProperty(leftProperty) && leftRepl.classAlias2Replaced) {
            updateInfo.sourceExpression = rightRepl.replacedExpr;
            updateInfo.destinationExpression = leftRepl.replacedExpr;
            updateInfo.needsUpdate = true;
        }
        else if (isClassProperty(rightProperty) && rightRepl.classAlias1Replaced) {
            updateInfo.sourceExpression = rightRepl.replacedExpr;
            updateInfo.destinationExpression = leftRepl.replacedExpr;
            updateInfo.needsUpdate = isClassProperty(leftProperty);
        }
        else if (isClassProperty(rightProperty) && rightRepl.classAlias2Replaced) {
            updateInfo.sourceExpression = leftRepl.replacedExpr;
            updateInfo.destinationExpression = rightRepl.replacedExpr;
            updateInfo.needsUpdate =  true;
        }
        else {
            updateInfo.needsUpdate = false;
        }
        return updateInfo;
    }
    
    public abstract void update(List<Updater> updaters
            , Object sourceObject, Object destinationObject, String... regex) throws Exception;

}
