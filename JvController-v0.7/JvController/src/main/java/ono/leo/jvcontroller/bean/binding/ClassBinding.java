package ono.leo.jvcontroller.bean.binding;

/**
 * Binding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:36)
 */
public abstract class ClassBinding {
    
    protected String id = "";
    protected String itemId = "";
    
    protected String viewClassAlias = "";
    protected String modelClassAlias = "";
    
    protected String viewFrom = "";
    protected String viewTo = "";
    protected String modelTo = ""; 
    protected String modelFrom = ""; 
    protected String conversorTo = ""; 
    protected String conversorFrom = ""; 
    protected String validator = "";
    protected String viewVar = "";
    protected String modelVar = "";
    protected String viewInstance = "";
    protected String modelInstance = "";
    
    protected String viewValueVar = "";
    protected String modelValueVar = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelValueVar() {
        return modelValueVar;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    
    public String getModelClassAlias() {
        return modelClassAlias;
    }

    public void setModelClassAlias(String modelClassAlias) {
        this.modelClassAlias = modelClassAlias;
    }

    public String getViewClassAlias() {
        return viewClassAlias;
    }

    public void setViewClassAlias(String viewClassAlias) {
        this.viewClassAlias = viewClassAlias;
    }

    public String getConversorFrom() {
        return conversorFrom;
    }

    public void setConversorFrom(String fromConversor) {
        this.conversorFrom = fromConversor;
    }

    public String getModelFrom() {
        return modelFrom;
    }

    public void setModelFrom(String modelFrom) {
        this.modelFrom = modelFrom;
    }

    public String getModelInstance() {
        return modelInstance;
    }

    public void setModelInstance(String modelInstance) {
        this.modelInstance = modelInstance;
    }

    public String getModelTo() {
        return modelTo;
    }

    public void setModelTo(String modelTo) {
        this.modelTo = modelTo;
    }

    public String getModelVar() {
        return modelVar;
    }

    public void setModelVar(String modelVar) {
        this.modelVar = modelVar;
    }

    public String getConversorTo() {
        return conversorTo;
    }

    public void setConversorTo(String toConversor) {
        this.conversorTo = toConversor;
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

    public String getViewInstance() {
        return viewInstance;
    }

    public void setViewInstance(String viewInstance) {
        this.viewInstance = viewInstance;
    }

    public String getViewTo() {
        return viewTo;
    }

    public void setViewTo(String viewTo) {
        this.viewTo = viewTo;
    }

    public String getViewVar() {
        return viewVar;
    }

    public void setViewVar(String viewVar) {
        this.viewVar = viewVar;
    }

    public abstract InstanceBinding createInstanceBinding(
            InstanceBeanBinding beanBinding);

    public String replaceClassToInstance(String classValue, String instanceValue, String classAlias) {
        String value = "";
        String valueComplete = "";
        for (int i=0; i<classValue.length(); i++) {
            char c = classValue.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                value += c;
            }
            if (!Character.isLetterOrDigit(c) || i==(classValue.length()-1)) {
                if (value.equals(classAlias)) {
                    valueComplete += instanceValue + c;
                }
                else {
                    String cc = (classValue.length()-1==i && Character.isLetterOrDigit(c) ? "" : c + "");
                    valueComplete += value + cc;
                }
                value = "";
            }
        }
        return valueComplete;
    }
    
    @Override
    public String toString() {
        return "ClassBinding{" + "viewClassAlias=" + viewClassAlias 
                + ", modelClassAlias=" + modelClassAlias + ", viewFrom=" 
                + viewFrom + ", viewTo=" + viewTo + ", modelTo=" + modelTo 
                + ", modelFrom=" + modelFrom + ", conversorTo=" + conversorTo 
                + ", conversorFrom=" + conversorFrom + ", validator=" 
                + validator + ", viewVar=" + viewVar + ", modelVar=" 
                + modelVar + ", viewInstance=" + viewInstance 
                + ", modelInstance=" + modelInstance + '}';
    }
    
}
