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
    
    private Map<Object, InstanceBeanBindingAndViewInstance> ibbsAndviews = new HashMap<Object, InstanceBeanBindingAndViewInstance> ();
    
    private JvControllerInstanceBinding instanceBinding
            = new JvControllerInstanceBinding();

    @Override
    public void removeAllChildrens() throws Exception {
        elContext.getBeans().values().remove(getModelInstance());
        elContext.getBeans().values().remove(getViewInstance());
        
        instanceBinding.removeAllBindings();
    }

    private class InstanceBeanBindingAndViewInstance {
        public InstanceBeanBindingAndViewInstance(InstanceBeanBinding ibb, Object viewInstance) {
            this.ibb = ibb;
            this.viewInstance = viewInstance;
        }
        private InstanceBeanBinding ibb;
        private Object viewInstance;
    }
    
    public JvControllerInstanceBinding getInstanceBinding() {
        return instanceBinding;
    }

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
    public void updateView(String ... beanIds) throws Exception {
        // System.out.println("===> updateCollectionView");
        
        // Aqui na atualizacao da view para itens da collecao
        // parte de que remove ou adiciona novos itens
        // poderia tentar substituir por uma proxy da Collection
        // interceptando os metodos para add e remover
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
        
        //System.out.println(viewClass);
        //System.out.println(modelClass);

        // Cria view's novos
        Iterator modelIterator = modelCollection.iterator();
        while (modelIterator.hasNext()) {
            Object modelObj = modelIterator.next();
            if (!ibbsAndviews.containsKey(modelObj)) {

                Object viewObj = viewClass.newInstance();
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
        
                // Verifica se itemId eval deve ser utilizado para formar o id do item
                InstanceBeanBinding ibb = new InstanceBeanBinding();
                String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
                if (itemId.trim().length() > 0) {
                    elContext.removeBeanById("modelItem");
                    elContext.addBean("modelItem", modelObj);
                    elContext.removeBeanById("viewItem");
                    elContext.addBean("viewItem", modelObj);
                    id = (String) elContext.evaluate(itemId);
                    elContext.removeBeanById("modelItem");
                    elContext.removeBeanById("viewItem");
                }
                
                ibb.setId(id);
                ibb.setViewFrom(viewId);
                ibb.setViewTo(viewId);
                ibb.setModelFrom(modelId);
                ibb.setModelTo(modelId);
                ibb.setViewClassAlias(viewClassAlias);
                ibb.setModelClassAlias(modelClassAlias);
                
                instanceBinding.addBeanBinding(ibb);
                ibbsAndviews.put(modelObj, new InstanceBeanBindingAndViewInstance(ibb, viewObj));
                
                if (viewCollection != null) {
                    viewCollection.add(viewObj);
                }
                if (viewInvokeAdd.trim().length() > 0) {
                    elContext.invoke(viewInvokeAdd, viewObj);
                }
            }
        }
        
        // Remove view's correspondentes aos modelos excluidos
        Iterator i = ibbsAndviews.entrySet().iterator();
        while (i.hasNext()) {
            Entry e = (Entry) i.next();
            Object modelObj = e.getKey();
            if (!modelCollection.contains(modelObj)) {
                
                //System.out.println("removendo obj" + modelObj);
                
                InstanceBeanBindingAndViewInstance ibbavi = ibbsAndviews.get(modelObj);
                
                Object viewObj = ibbavi.viewInstance;
                i.remove();
                if (viewCollection != null) {
                    viewCollection.remove(viewObj);
                }
                if (viewInvokeRemove.trim().length() > 0) {
                    elContext.invoke(viewInvokeRemove, viewObj);
                }
                
                // Remove os objetos do EL Context
                // elContext.removeBeanByInstance(viewObj);
                // elContext.removeBeanByInstance(modelObj);
                
                // Remove todos os vinculos relacionado a essas instancias
                InstanceBeanBinding ibb = ibbavi.ibb;
                instanceBinding.removeBeanBinding(ibb);

                // Remove todas actions relacionadas a essas instancias
                // As actions nao precisam remover porque elas sao
                // criadas na hora e nao ficam armazedas em alguma collection
                
                // invoke garbage colector ?
                ibb = null;
                modelObj = null;
                viewObj = null;
                // System.gc();
            }
        }
        
        // update view
        instanceBinding.updateView(beanIds);
        
        // set
        if (viewTo.trim().length() > 0) {
            elContext.set(viewTo, viewCollection);        
        }
    }

    @Override
    public void updateModel(String ... beanIds) throws Exception {
        // System.out.println("===> updateCollectionModel");
        
        /* Aqui eh direto, nao precisa
        String bindingId = "";
        if (beanIds.length > 0) {
            bindingId = beanIds[0].trim();
        }
        if (bindingId.trim().length() > 0) {
            bindingId = bindingId.split("\\.")[0];
            if (beanIds[0].contains(".")) {
                beanIds[0] = beanIds[0].replace(bindingId + ".", "");
            }
            else {
                beanIds[0] = beanIds[0].replace(bindingId, "");
            }
        }        
         * 
         */
        
        Collection modelCollection = (Collection) elContext.get(modelFrom);

        instanceBinding.updateModel(beanIds);
        
        // set
        elContext.set(modelTo, modelCollection);        
    }

    // TODO ?
    @Override
    public Object getAssociatedModelInstance(Object viewInstance) throws Exception {
        Object viewObj = elContext.get(viewFrom.split("\\.")[0]);
        if (viewInstance.equals(viewObj)) {
            Object modelObj = elContext.get(modelTo.split("\\.")[0]);
            return modelObj;
        }
        
        return instanceBinding.getAssociatedModelInstance(viewInstance);
    }

    // TODO ?
    @Override
    public Object getAssociatedViewInstance(Object modelInstance) throws Exception {
        Object modelObj = elContext.get(modelTo.split("\\.")[0]);
        if (modelInstance.equals(modelObj)) {
            Object viewObj = elContext.get(viewFrom.split("\\.")[0]);
            return viewObj;
        }

        return instanceBinding.getAssociatedViewInstance(modelInstance);
    }
    
    
}
