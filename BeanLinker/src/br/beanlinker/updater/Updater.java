package br.beanlinker.updater;

import br.beanlinker.link.Link.UpdateInfo;

/**
 * Abstract class Updater.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (02/01/2012 14:46)
 */
public abstract class Updater {
    
    private UpdateInfo updateInfo;
    private String conversor;
    private String validator;
    private String onConversionException;
    private String onValidationException;

    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
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

    public String getOnValidationException() {
        return onValidationException;
    }

    public void setOnValidationException(String onValidationException) {
        this.onValidationException = onValidationException;
    }

    public abstract void update() throws Exception;
    public abstract void testConversion() throws Exception;
    public abstract void testValidation() throws Exception;

}
