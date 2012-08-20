package ono.leo.jvcontroller.bean.binding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;
import ono.leo.jvcontroller.core.JvControllerClassBinding;
import ono.leo.jvcontroller.core.JvControllerClassContext;
import ono.leo.jvcontroller.core.JvControllerInstanceBinding;

/**
 * InstanceCollectionBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 09:47)
 */
public class InstanceCollectionBinding extends InstanceBinding {

    private String viewItemType = "";
    private String viewInvokeAdd = "";
    private String viewInvokeRemove = "";
    
    private Collection localViewCollection = null;
    
    private static JvControllerClassContext classContext = JvControllerClassContext.getInstance();
    private static JvControllerClassBinding classBinding = JvControllerClassBinding.getInstance();
    private static BeanInstanceELAccessor elContext = BeanInstanceELAccessor.getInstance();
    
    private Map<Object, Object> views = new HashMap<Object, Object> ();
    
    private JvControllerInstanceBinding instanceBinding
            = new JvControllerInstanceBinding();

    public String getViewItemType() {
        return viewItemType;
    }

    public void setViewItemType(String viewItemType) {
        this.viewItemType = viewItemType;
    }

    public String getViewInvokeAdd() {
        return viewInvokeAdd;
    }

    public void setViewInvokeAdd(String viewInvokeAdd) {
        this.viewInvokeAdd = viewInvokeAdd;
    }

    public String getViewInvokeRemove() {
        return viewInvokeRemove;
    }

    public void setViewInvokeRemove(String viewInvokeRemove) {
        this.viewInvokeRemove = viewInvokeRemove;
    }
    
    @Override
    public void updateView() throws Exception {
        System.out.println("===> updateCollectionView");
        
        Collection viewCollection = null;
        
        if (viewTo.trim().length() > 0) {
            viewCollection = (Collection) elContext.get(viewTo);
        }
        else {
            if (localViewCollection == null) {
                localViewCollection = new ArrayList();
            }
            viewCollection = localViewCollection;
        }
        if (viewCollection == null) {
            throw new Exception("Collection \"" + viewTo + "\" not found !");
        }
        Collection modelCollection = (Collection) elContext.get(modelFrom);

        Class viewClass = elContext.getGenericType(viewTo);
        Class modelClass = elContext.getGenericType(modelFrom);
        
        if (viewClass == null) {
            viewClass = classContext.getViewClasses().get(viewItemType);
        }
        
        System.out.println(viewClass);
        System.out.println(modelClass);

        // Cria view's novos
        Iterator modelIterator = modelCollection.iterator();
        while (modelIterator.hasNext()) {
            Object modelObj = modelIterator.next();
            if (!views.containsKey(modelObj)) {
                Object viewObj = viewClass.newInstance();
                views.put(modelObj, viewObj);
                String viewId = "col" + UUID.randomUUID().toString().replace("-", "");
                String modelId = "col" + UUID.randomUUID().toString().replace("-", "");
                elContext.addBean(viewId, viewObj);
                elContext.addBean(modelId, modelObj);

                // Create action's if necessary TODO ?
                String viewClassAlias = classContext.getClassNames().get(viewClass.getName());
                String modelClassAlias = classContext.getClassNames().get(modelClass.getName());
                List<ClassActionBinding> classActionBindings = classBinding.getActionClassBindings(viewClassAlias);
                for (ClassActionBinding classActionBinding : classActionBindings) {
                    classActionBinding.setViewClassAlias(viewClassAlias);
                    classActionBinding.setModelClassAlias(modelClassAlias); 
                    InstanceActionBinding iab = classActionBinding.createInstanceActionBinding(viewId, modelId);
                    iab.createAndBindAction();
                }
        
                InstanceBeanBinding ibb = new InstanceBeanBinding();
                ibb.setViewFrom(viewId);
                ibb.setViewTo(viewId);
                ibb.setModelFrom(modelId);
                ibb.setModelTo(modelId);
                instanceBinding.addBeanBinding(ibb);
                
                if (viewCollection != null) {
                    viewCollection.add(viewObj);
                }
                if (viewInvokeAdd.trim().length() > 0) {
                    elContext.invoke(viewInvokeAdd, viewObj);
                }
            }
        }
        
        // Remove view's correspondentes aos modelos excluidos
        Iterator i = views.entrySet().iterator();
        while (i.hasNext()) {
            Entry e = (Entry) i.next();
            Object modelObj = e.getKey();
            if (!modelCollection.contains(modelObj)) {
                System.out.println("removendo obj" + modelObj);
                Object viewObj = views.get(modelObj);
                i.remove();
                if (viewCollection != null) {
                    viewCollection.remove(viewObj);
                }
                if (viewInvokeRemove.trim().length() > 0) {
                    elContext.invoke(viewInvokeRemove, viewObj);
                }
            }
        }
        
        // update view
        instanceBinding.updateView();
        
        // set
        if (viewTo.trim().length() > 0) {
            elContext.set(viewTo, viewCollection);        
        }
    }

    @Override
    public void updateModel() throws Exception {
        System.out.println("===> updateCollectionModel");
        
        Collection modelCollection = (Collection) elContext.get(modelFrom);

        instanceBinding.updateModel();
        
        // set
        elContext.set(modelTo, modelCollection);        
    }
    
}
