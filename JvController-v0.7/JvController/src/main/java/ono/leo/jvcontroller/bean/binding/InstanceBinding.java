package ono.leo.jvcontroller.bean.binding;

import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;
import ono.leo.jvcontroller.core.JvControllerClassContext;

/**
 * InstanceBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 09:41)
 */
public abstract class InstanceBinding {

    protected String id = "";
    protected String itemId = "";
    
    protected String viewClassAlias = "";
    protected String modelClassAlias = "";
    
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
    
    protected BeanInstanceELAccessor elContext = BeanInstanceELAccessor.getInstance();
    protected JvControllerClassContext classContext = JvControllerClassContext.getInstance();
    
    public InstanceBinding() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public abstract void updateView(String ... modelUpdateEval) throws Exception;

    public abstract void updateModel(String ... modelUpdateEval) throws Exception;

    public abstract Object getAssociatedModelInstance(Object viewInstance)
            throws Exception;

    public abstract Object getAssociatedViewInstance(Object modelInstance)
            throws Exception;

    public Object getModelInstance() throws Exception {
        Object ret = null;
        try {
            String model = modelTo;
            if (model.indexOf(".")>=0) {
                model = modelTo.split("\\.")[0];
            }
            ret = elContext.evaluate(model);
            String classAlias = classContext.getClassNames().get(ret.getClass().getName());
            if (classAlias.trim().length()>0 && !modelClassAlias.equals(classAlias)) {
                return null;
            }
        }
        catch (Exception e) { }
        return ret;
    }
    
    public Object getViewInstance() throws Exception {
        Object ret = null;
        try {
            String view = viewFrom;
            if (view.indexOf(".")>=0) {
                view = viewFrom.split("\\.")[0];
            }
            ret = elContext.evaluate(view);
            String classAlias = classContext.getClassNames().get(ret.getClass().getName());
            if (classAlias.trim().length()>0 && !viewClassAlias.equals(classAlias)) {
                return null;
            }
        }
        catch (Exception e) { }
        return ret;
    }
    
    public abstract void removeAllChildrens() throws Exception;
    
    @Override
    public String toString() {
        return "InstanceBinding{" + "viewFrom=" + viewFrom + ", viewTo="
                + viewTo + ", viewFromEval=" + viewFromEval + ", viewToEval="
                + viewToEval + ", viewValueVar=" + viewValueVar + ", modelTo="
                + modelTo + ", modelFrom=" + modelFrom + ", modelToEval="
                + modelToEval + ", modelFromEval=" + modelFromEval
                + ", modelValueVar=" + modelValueVar + ", conversorTo="
                + conversorTo + ", conversorFrom=" + conversorFrom
                + ", validator=" + validator + ", biela=" + elContext + '}';
    }

    // TODO esse viewFrom e modelTo precisa pensar em algum jeito de obter de forma correta
    @Override
    protected synchronized void finalize() throws Throwable {
        /* Nao ficou bacana colocar aqui
        System.out.println("DESTRUINDO InstanceBinding" + this + " ...");
        try {
            String viewId = viewFrom.split("\\.")[0];
            String modelId = modelTo.split("\\.")[0];
            Iterator i = elContext.getBeans().entrySet().iterator();
            while (i.hasNext()) {
                Entry<String, Object> es = (Entry<String, Object>) i.next();
                if (es.getKey().equals(viewId) || es.getKey().equals(modelId)) {
                    i.remove();
                    System.out.println("DESTRUINDO InstanceBinding -> REMOVENDO" + es + " ...");
                }
            }
        } catch (Exception e) { }
         * 
         */
        super.finalize();
    }
    
}
