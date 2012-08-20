package jbean2.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import jbean2.annotation.Action;
import jbean2.annotation.BindBean;
import jbean2.annotation.BindCollection;
import jbean2.annotation.BindProperty;
import jbean2.annotation.View;
import jbean2.conversor.Conversor;

/**
 *
 * @author leo
 */
public class JController {

    private static JController instance;
    private BeanELAcess be = BeanELAcess.getInstance();

    //public void startApp() {
    static {
        try {
            BeanFinder.main(null);
        } catch (IOException ex) {
            Logger.getLogger(BeanELAcess.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }

    public synchronized static JController getInstance() {
        if (instance == null) {
            instance = new JController();
        }
        return instance;
    }

    public void newInstance(String name, String classId) throws Exception {
        Class beanClass = BeanFinder.getModelClasses().get(classId);
        if (beanClass == null) {
            beanClass = BeanFinder.getControllerClasses().get(classId);
        }
        if (beanClass == null) {
            beanClass = BeanFinder.getConversorClasses().get(classId);
        }
        if (beanClass == null) {
            beanClass = BeanFinder.getViewClasses().get(classId);
        }
        if (beanClass != null) {
            Object beanObj = beanClass.newInstance();
            ActionHandler.createAction(beanObj);
            be.addBean(name, beanObj);
        }
    }

    public void updateView(String viewId) throws Exception {
        //System.out.println("update view: " + viewId);

        if (propertyBindings.containsKey(viewId)) {
            //System.out.println("Found propertyBindings: " + viewId);
            updatePropertyView(viewId, propertyBindings.get(viewId));

        } 
        else if (beanBindings.containsKey(viewId)) {
            //System.out.println("Found beanBindings: " + viewId);
            Object viewObj = be.evaluate(viewId);
            if (viewObj == null) {
                throw new Exception("View " + viewId + " not fount !");
            }

            View view = viewObj.getClass().getAnnotation(View.class);
            if (view == null) {
                throw new Exception(viewId + " not is view !");
            }
            updateBeanViews(viewId, viewObj, beanBindings.get(viewId));
        
        } 
        else if (collectionBindings.containsKey(viewId)) {
            //System.out.println("Found collectionBindings: " + viewId);
            Object viewObj = be.evaluate(viewId);
            updateCollectionView(viewId, viewObj, collectionBindings.get(viewId));
        } 
        else {
            throw new Exception("View " + viewId + " not fount !");
        }

    }

    private void updateBeanViews(String viewId, Object viewObj, BeanBinding beanBinding) throws Exception {
        //System.out.println("");
        //System.out.println("===> updateBeanViews");
        Class viewClass = viewObj.getClass();
        String viewClassId = ((View) viewClass.getAnnotation(View.class)).value();
        for (Method m : viewClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BindProperty.class)) {
                BindProperty bindProperty = (BindProperty) m.getAnnotation(BindProperty.class);
                String modelClassId = bindProperty.toAndfrom().split("\\.")[0];
                String fromProperty = bindProperty.property().replace(viewClassId + ".", viewId + ".");
                String toProperty = bindProperty.toAndfrom().replace(modelClassId + ".", beanBinding.model + ".");
                String conversor = bindProperty.conversor();
                PropertyBinding propertyBinding = null;
                //System.out.println("---> viewId: " + viewId);
                //System.out.println("---> viewObj:" + viewObj);
                //System.out.println("---> propertyBinding: " + propertyBinding);
                //System.out.println("---> fromProperty: " + fromProperty);
                //System.out.println("---> toProperty: " + toProperty);
                //System.out.println("---> conversor: " + conversor);
                bindProperty(fromProperty, toProperty, conversor);
                updateView(fromProperty);
                
            } else if (m.isAnnotationPresent(BindBean.class)) {
                BindBean bindBean = (BindBean) m.getAnnotation(BindBean.class);
                String modelClassId = bindBean.to().split("\\.")[0];
                String fromProperty = bindBean.property().replace(viewClassId + ".", viewId + ".");
                String toProperty = bindBean.to().replace(modelClassId + ".", beanBinding.model + ".");
                //System.out.println("--->" + bindBean);
                //System.out.println("--->" + fromProperty);
                //System.out.println("--->" + toProperty);
                bindBean(fromProperty, toProperty);
                updateView(fromProperty);
            } else if (m.isAnnotationPresent(BindCollection.class)) {
                BindCollection bindCollection = (BindCollection) m.getAnnotation(BindCollection.class);
                String modelClassId = bindCollection.to().split("\\.")[0];
                String fromProperty = bindCollection.property().replace(viewClassId + ".", viewId + ".");
                String toProperty = bindCollection.to().replace(modelClassId + ".", beanBinding.model + ".");
                //System.out.println("--->" + bindCollection);
                //System.out.println("--->" + fromProperty);
                //System.out.println("--->" + toProperty);
                bindCollection(fromProperty, toProperty);
                updateView(fromProperty);
            } else if (m.isAnnotationPresent(Action.class)) {
                Action action = (Action) m.getAnnotation(Action.class);
                System.out.println("action: " + action);
            }
        }
    }

    private void updatePropertyView(String viewId, PropertyBinding propertyBinding) throws Exception {
        //System.out.println("");
        //System.out.println("===> updatePropertyView");
        //System.out.println("--->" + viewId);
        //System.out.println("--->" + propertyBinding);
        Conversor conversor = (Conversor) be.get(propertyBinding.conversor);
        Object arg = be.get(propertyBinding.model);
        if (conversor != null) {
            arg = conversor.getAsView(arg);
        }
        be.set(viewId, arg);
    }

    private void updateCollectionView(String viewId, Object viewObj, CollectionBinding collectionBinding) throws Exception {
        System.out.println("");
        System.out.println("===> updateCollectionView");
        System.out.println("--->" + viewId);
        System.out.println("--->" + collectionBinding);
        
        Collection modelCollection = (Collection) be.get(collectionBinding.getModel());
        Collection viewCollection = (Collection) be.get(collectionBinding.getView());

        Class viewClass = be.getCollectionType(collectionBinding.view);
        Class modelClass = be.getCollectionType(collectionBinding.model);
        
        System.out.println(viewClass);
        System.out.println(modelClass);
        
        // Cria view's novos
        Iterator modelIterator = modelCollection.iterator();
        while (modelIterator.hasNext()) {
            Object modelObj = modelIterator.next();
            if (!collectionBinding.getBinding().containsKey(modelObj)) {
                View viewAnnotation = (View) viewClass.getAnnotation(View.class);
                newInstance("newInstance", viewAnnotation.value());
                viewObj = be.get("newInstance");
                viewCollection.add(viewObj);
                collectionBinding.getBinding().put(modelObj, viewObj);
            }
        }
        
        // Remove view's correspondentes aos modelos excluidos
        Iterator i = collectionBinding.getBinding().entrySet().iterator();
        while (i.hasNext()) {
            Entry e = (Entry) i.next();
            Object modelObj = e.getKey();
            if (!modelCollection.contains(modelObj)) {
                System.out.println("removendo obj" + modelObj);
                viewObj = collectionBinding.getBinding().get(modelObj);
                viewCollection.remove(viewObj);
                i.remove();
            }
        }
        
        // Atualiza as propriedades do model para view
        for (Object modelObj : collectionBinding.getBinding().keySet()) {
                // System.out.println("Atualizando propriedades de " + modelObj + " para " + viewObj);
                viewObj = collectionBinding.getBinding().get(modelObj);
                be.addBean("collectionItemView", viewObj);
                be.addBean("collectionItemModel", modelObj);
                bindBean("collectionItemView", "collectionItemModel");
                updateView("collectionItemView");
        }
        
        // call setCollection
        be.set(viewId, viewCollection);
        
        System.out.println("--->");
    }
    

    public void updateModel(String string) {
        // throw new UnsupportedOperationException("Not yet implemented");
    }

    public void bindProperty(String view, String model, String conversor) {
        propertyBindings.put(view, new PropertyBinding(view, model, conversor));
    }

    public void bindBean(String view, String model) {
        beanBindings.put(view, new BeanBinding(view, model));
    }
    
    public void bindCollection(String view, String model) {
        // Se ja estiver adicionado, ignora
        if (collectionBindings.containsKey(view)) {
            CollectionBinding cb = collectionBindings.get(view);
            if (cb.getModel().equals(model) && cb.getView().equals(view)) {
                return;
            }
        }
        collectionBindings.put(view, new CollectionBinding(view, model));
    }

    private Map<String, PropertyBinding> propertyBindings = new HashMap<String, PropertyBinding>();
    private Map<String, BeanBinding> beanBindings = new HashMap<String, BeanBinding>();
    private Map<String, CollectionBinding> collectionBindings = new HashMap<String, CollectionBinding>();

    public Map<String, BeanBinding> getBeanBindings() {
        return beanBindings;
    }

    public Map<String, CollectionBinding> getCollectionBindings() {
        return collectionBindings;
    }

    public Map<String, PropertyBinding> getPropertyBindings() {
        return propertyBindings;
    }

    public class CollectionBinding {

        private String view;
        private String model;
        private Map<Object, Object> binding = new HashMap<Object, Object>();

        public CollectionBinding(String view, String model) {
            this.view = view;
            this.model = model;
        }

        public String getModel() {
            return model;
        }

        public String getView() {
            return view;
        }

        public Map<Object, Object> getBinding() {
            return binding;
        }

        @Override
        public String toString() {
            return "CollectionBinding{" + "view=" + view + ", model=" + model + '}';
        }
    }
    
    public class BeanBinding {

        private String view;
        private String model;

        public BeanBinding(String view, String model) {
            this.view = view;
            this.model = model;
        }

        public String getModel() {
            return model;
        }

        public String getView() {
            return view;
        }

        @Override
        public String toString() {
            return "BeanBinding{" + "view=" + view + ", model=" + model + '}';
        }
    }

    public class PropertyBinding {

        private String view;
        private String model;
        private String conversor;
        
        public PropertyBinding(String view, String model, String conversor) {
            this.view = view;
            this.model = model;
            this.conversor = conversor;
        }

        public String getModel() {
            return model;
        }

        public String getView() {
            return view;
        }

        public String getConversor() {
            return conversor;
        }

        @Override
        public String toString() {
            return "PropertyBinding{" + "view=" + view + ", model=" + model + ", conversor=" + conversor + '}';
        }

    }
}
