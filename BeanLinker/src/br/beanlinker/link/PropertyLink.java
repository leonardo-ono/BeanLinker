package br.beanlinker.link;

import br.beanlinker.converter.Converter;
import br.beanlinker.core.BeanLinker;
import br.beanlinker.updater.Updater;
import br.beanlinker.validator.Validator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class PropertyLink.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (02/01/2012 11:20)
 */
public class PropertyLink extends Link {

    private String conversor;
    private String validator;
    private String onConversionException;
    private String onValidationException;
    
    public PropertyLink(BeanLinker linker, String leftProperty, String rightProperty
            , String conversor, String validator
            , String onConversionException, String onValidationException) {
        
        super(linker);
        this.leftProperty = leftProperty;
        this.rightProperty = rightProperty;
        this.conversor = conversor;
        this.validator = validator;
        this.onConversionException = onConversionException;
        this.onValidationException = onValidationException;
    }

    public String getConversor() {
        return conversor;
    }

    public void setConversor(String conversor) {
        this.conversor = conversor;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public String getOnConversionException() {
        return onConversionException;
    }

    public void setOnConversionException(String onConversionException) {
        this.onConversionException = onConversionException;
    }
    
    @Override
    public void update(List<Updater> updaters
            , final Object sourceObject, final Object destinationObject
            , String ... regex) throws Exception {
        
        String reg = ".*";
        if (regex.length > 0) reg = regex[0];
        Pattern pattern = Pattern.compile(reg);

        final UpdateInfo updateInfo =  generateUpdateInfo(sourceObject, destinationObject);

        // Provisorio teste para gerar onConversionException
        String sourceClassAlias = linker.getObjectClassAlias(sourceObject);
        String destinationClassAlias = linker.getObjectClassAlias(destinationObject);
        ClassAliasReplacements car = replaceAllClassAlias(onConversionException, sourceClassAlias, "sourceObject", destinationClassAlias, "destinationObject");
        updateInfo.onConversionException = car.replacedExpr;

        // Provisorio teste para gerar onValidationException
        ClassAliasReplacements car2 = replaceAllClassAlias(onValidationException, sourceClassAlias, "sourceObject", destinationClassAlias, "destinationObject");
        updateInfo.onValidationException = car2.replacedExpr;
        
        System.out.println("---------------------------------------");
        System.out.println("needs update: " + updateInfo.needsUpdate );
        System.out.println("regex: " + reg );
        System.out.println("sourc: " + updateInfo.sourceExpression + " / match: " + pattern.matcher(updateInfo.sourceExpression).matches() );
        System.out.println("desti: " + updateInfo.destinationExpression + " / match: " + pattern.matcher(updateInfo.destinationExpression).matches() );
        boolean patternMatches = pattern.matcher(updateInfo.sourceExpression).matches() 
                || pattern.matcher(updateInfo.destinationExpression).matches();
        
        if (updateInfo.needsUpdate && patternMatches) {
            
            final Updater updater = new Updater() {
                
                {
                    setUpdateInfo(updateInfo);
                    setConversor(conversor);
                    setValidator(validator);
                    setOnConversionException(onConversionException);
                    setOnValidationException(onValidationException);
                }
                
                @Override
                public void update() throws Exception {
                    updatePrivate(true, true, true);
                }

                @Override
                public void testConversion() throws Exception {
                    updatePrivate(false, true, false);
                }

                @Override
                public void testValidation() throws Exception {
                    updatePrivate(false, true, true);
                }
                
                public void updatePrivate(boolean enableUpdate
                        , boolean enableConversor, boolean enableValidator) throws Exception {
                    
                    Object objValue = getUpdateInfo().evalSourceExpression("");
                    if (objValue == null) {
                        throw new RuntimeException("Erro precisa corrigir ...");
                    }
                    Validator validator = null;
                    Converter conversor = null;
                    if (enableConversor && getConversor().length()>0) {
                        conversor = (Converter) linker.eval(getConversor());
                        objValue = conversor.get(objValue, this);
                    }
                    if (enableValidator && getValidator().length()>0) {
                        validator = (Validator) linker.eval(getValidator());
                        validator.validate(objValue, this);
                    }
                    if (enableUpdate) {
                        linker.assign("updateSetValue", objValue);
                        getUpdateInfo().evalDestinationExpression("=updateSetValue;");
                        linker.assign("updateSetValue", null);
                    }
                }

            };
            updaters.add(updater);
        }
        
    }

}
