package ono.leo.jvcontroller.bean.binding;

import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;

/**
 * InstanceBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 09:41)
 */
public abstract class InstanceBinding {

    protected String viewFrom = "";
    protected String viewTo = "";
    protected String viewFromEval = "";
    protected String viewToEval = "";
    protected String viewValueVar = "";
    
    protected String modelTo = ""; 
    protected String modelFrom = ""; 
    protected String modelToEval = ""; 
    protected String modelFromEval = ""; 
    protected String modelValueVar = ""; 
    
    protected String conversorTo = ""; 
    protected String conversorFrom = ""; 
    protected String validator = "";

    protected BeanInstanceELAccessor biela 
            = BeanInstanceELAccessor.getInstance();

    public InstanceBinding() {
    }

    public String getConversorFrom() {
        return conversorFrom;
    }

    public void setConversorFrom(String conversorFrom) {
        this.conversorFrom = conversorFrom;
    }

    public String getConversorTo() {
        return conversorTo;
    }

    public void setConversorTo(String conversorTo) {
        this.conversorTo = conversorTo;
    }

    public String getModelFrom() {
        return modelFrom;
    }

    public void setModelFrom(String modelFrom) {
        this.modelFrom = modelFrom;
    }

    public String getModelFromEval() {
        return modelFromEval;
    }

    public void setModelFromEval(String modelFromEval) {
        this.modelFromEval = modelFromEval;
    }

    public String getModelTo() {
        return modelTo;
    }

    public void setModelTo(String modelTo) {
        this.modelTo = modelTo;
    }

    public String getModelToEval() {
        return modelToEval;
    }

    public void setModelToEval(String modelToEval) {
        this.modelToEval = modelToEval;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public String getViewFrom() {
        return viewFrom;
    }

    public void setViewFrom(String viewFrom) {
        this.viewFrom = viewFrom;
    }

    public String getViewFromEval() {
        return viewFromEval;
    }

    public void setViewFromEval(String viewFromEval) {
        this.viewFromEval = viewFromEval;
    }

    public String getViewTo() {
        return viewTo;
    }

    public void setViewTo(String viewTo) {
        this.viewTo = viewTo;
    }

    public String getViewToEval() {
        return viewToEval;
    }

    public void setViewToEval(String viewToEval) {
        this.viewToEval = viewToEval;
    }

    public String getModelValueVar() {
        return modelValueVar;
    }

    public void setModelValueVar(String modelValueVar) {
        this.modelValueVar = modelValueVar;
    }

    public String getViewValueVar() {
        return viewValueVar;
    }

    public void setViewValueVar(String viewValueVar) {
        this.viewValueVar = viewValueVar;
    }

    public abstract void updateView() throws Exception;
    public abstract void updateModel() throws Exception;

    @Override
    public String toString() {
        return "InstanceBinding{" + "viewFrom=" + viewFrom + ", viewTo=" 
                + viewTo + ", viewFromEval=" + viewFromEval + ", viewToEval=" 
                + viewToEval + ", viewValueVar=" + viewValueVar + ", modelTo=" 
                + modelTo + ", modelFrom=" + modelFrom + ", modelToEval=" 
                + modelToEval + ", modelFromEval=" + modelFromEval 
                + ", modelValueVar=" + modelValueVar + ", conversorTo=" 
                + conversorTo + ", conversorFrom=" + conversorFrom 
                + ", validator=" + validator + ", biela=" + biela + '}';
    }
    
}
