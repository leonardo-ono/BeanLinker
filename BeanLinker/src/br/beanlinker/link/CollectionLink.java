package br.beanlinker.link;

import br.beanlinker.core.BeanLinker;
import br.beanlinker.core.BeanLinkerImpl;
import br.beanlinker.updater.Updater;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class CollectionLink.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (02/01/2012 19:17)
 */
public class CollectionLink extends Link {

    private String leftAddItemMethod;
    private String leftRemoveItemMethod;
    private String leftGetIteratorMethod;
    private String leftClearMethod;
    private String leftItemClassAlias;
    
    private String rightAddItemMethod;
    private String rightRemoveItemMethod;
    private String rightGetIteratorMethod;
    private String rightClearMethod;
    private String rightItemClassAlias;
   
    // Map<SourceCollectionMap, Map<DestinationCollectionMap, Map<sourceItem, destinationItem>>>
    private Map<CollectionKeyAdapter, Map<CollectionKeyAdapter, Map<Object, Object>>> collectionLinkCache 
            = new HashMap<CollectionKeyAdapter, Map<CollectionKeyAdapter, Map<Object, Object>>>();

    private static class CollectionKeyAdapter {

        private WeakReference<Object> collection;

        public CollectionKeyAdapter(Object collection) {
            this.collection = new WeakReference<Object>(collection);
        }

        @Override
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        public boolean equals(Object o) {
            CollectionKeyAdapter cka = (CollectionKeyAdapter) o;
            return (cka.collection.get() == collection.get());
        }

        @Override
        public int hashCode() {
            return collection.getClass().getName().hashCode();
        }
        
    }

    public CollectionLink(BeanLinker linker, String leftProperty, String rightProperty
            , String rightItemClassAlias) {
        
        super(linker);
        this.leftProperty = leftProperty;
        this.rightProperty = rightProperty;
        
        this.leftAddItemMethod = "add";
        this.leftRemoveItemMethod = "remove";
        this.leftGetIteratorMethod = "iterator";
        this.leftClearMethod = "clear";
        this.leftItemClassAlias = "";
        
        this.rightAddItemMethod = "add";
        this.rightRemoveItemMethod = "remove";
        this.rightGetIteratorMethod = "iterator";
        this.rightClearMethod = "clear";
        this.rightItemClassAlias = rightItemClassAlias;
    }

    public String getLeftAddItemMethod() {
        return leftAddItemMethod;
    }

    public void setLeftAddItemMethod(String leftAddItemMethod) {
        this.leftAddItemMethod = leftAddItemMethod;
    }

    public String getLeftClearMethod() {
        return leftClearMethod;
    }

    public void setLeftClearMethod(String leftClearMethod) {
        this.leftClearMethod = leftClearMethod;
    }

    public String getLeftGetIteratorMethod() {
        return leftGetIteratorMethod;
    }

    public void setLeftGetIteratorMethod(String leftGetIteratorMethod) {
        this.leftGetIteratorMethod = leftGetIteratorMethod;
    }

    public String getLeftItemClassAlias() {
        return leftItemClassAlias;
    }

    public void setLeftItemClassAlias(String leftItemClassAlias) {
        this.leftItemClassAlias = leftItemClassAlias;
    }

    public String getLeftRemoveItemMethod() {
        return leftRemoveItemMethod;
    }

    public void setLeftRemoveItemMethod(String leftRemoveItemMethod) {
        this.leftRemoveItemMethod = leftRemoveItemMethod;
    }

    public String getRightAddItemMethod() {
        return rightAddItemMethod;
    }

    public void setRightAddItemMethod(String rightAddItemMethod) {
        this.rightAddItemMethod = rightAddItemMethod;
    }

    public String getRightClearMethod() {
        return rightClearMethod;
    }

    public void setRightClearMethod(String rightClearMethod) {
        this.rightClearMethod = rightClearMethod;
    }

    public String getRightGetIteratorMethod() {
        return rightGetIteratorMethod;
    }

    public void setRightGetIteratorMethod(String rightGetIteratorMethod) {
        this.rightGetIteratorMethod = rightGetIteratorMethod;
    }

    public String getRightItemClassAlias() {
        return rightItemClassAlias;
    }

    public void setRightItemClassAlias(String rightItemClassAlias) {
        this.rightItemClassAlias = rightItemClassAlias;
    }

    public String getRightRemoveItemMethod() {
        return rightRemoveItemMethod;
    }

    public void setRightRemoveItemMethod(String rightRemoveItemMethod) {
        this.rightRemoveItemMethod = rightRemoveItemMethod;
    }

    @Override
    public void update(List<Updater> updaters
            , Object sourceObject, Object destinationObject, String... regex) throws Exception {

        // Depois, implementar o ReferenceQueue para ficar mais eficiente
        // Delete inactive source collections
        for (Iterator<Entry<CollectionKeyAdapter, Map<CollectionKeyAdapter, Map<Object, Object>>>> it 
                = collectionLinkCache.entrySet().iterator(); it.hasNext();) {
            
            Entry<CollectionKeyAdapter, Map<CollectionKeyAdapter, Map<Object, Object>>> entry = it.next();
            if (entry.getKey().collection.get() == null) {
                it.remove();
                System.out.println("");
                System.out.println("===> Removendo source collection inativo ...");
                System.out.println("");
            }
        }

        final UpdateInfo updateInfo = generateUpdateInfo(sourceObject, destinationObject);
        if (updateInfo.needsUpdate) {

                Object sourceCollection = updateInfo.evalSourceExpression(""); 
                Object destinationCollection = updateInfo.evalDestinationExpression(""); 
                
                boolean inverted = false;

                // Check if there is already an association between source collection and destination collection
                Map<CollectionKeyAdapter, Map<Object, Object>> destinationCollectionMap 
                        = collectionLinkCache.get(new CollectionKeyAdapter(sourceCollection));
                
                // Se fizer o update do view para model, pode ser que inverta
                if (destinationCollectionMap == null) {
                    destinationCollectionMap = collectionLinkCache.get(new CollectionKeyAdapter(destinationCollection));
                    // invertendo, encontrou ? Se sim, significa que o source->dest foi feito uma vez, 
                    // agora o usuario esta solicitando a atualizacao contraria
                    if (destinationCollectionMap != null) {
                        Object prov = sourceCollection;
                        sourceCollection = destinationCollection;
                        destinationCollection = prov;

                        prov = updateInfo.destinationExpression;
                        updateInfo.destinationExpression = updateInfo.sourceExpression;
                        updateInfo.sourceExpression = (String) prov;
                        inverted = true;
                    }
                }
                
                // Se mesmo assim, continuar null, significa primeira vez que os dois collections estao sendo vinculados
                if (destinationCollectionMap == null) {
                    destinationCollectionMap = new HashMap<CollectionKeyAdapter, Map<Object, Object>>();
                    collectionLinkCache.put(new CollectionKeyAdapter(sourceCollection), destinationCollectionMap);
                    
                    updateInfo.evalDestinationExpression("." + rightClearMethod + "()");
                }

                Map<Object, Object> itemAssociationCollectionMap = destinationCollectionMap.get(new CollectionKeyAdapter(destinationCollection));
                if (itemAssociationCollectionMap == null) {
                    itemAssociationCollectionMap = new HashMap<Object, Object>();
                    destinationCollectionMap.put(new CollectionKeyAdapter(destinationCollection), itemAssociationCollectionMap);
                }

                Set<Object> itensToDeleteFromDestination = new HashSet<Object>();
                itensToDeleteFromDestination.addAll(itemAssociationCollectionMap.keySet());
                Iterator iterator = iterator = (Iterator) updateInfo.evalSourceExpression("." + getLeftGetIteratorMethod() + "()");
                
                while (iterator.hasNext()) {
                    Object sourObj = iterator.next();
                    Object destObj = itemAssociationCollectionMap.get(sourObj);
                    
                    // view -> model
                    if (inverted) {
                        if (destObj != null) {
                            ((BeanLinkerImpl) linker).updatePrivate(updaters, destObj, sourObj, regex);
                        }
                        
                        // como fica ?
                        // itensToDeleteFromDestination.clear();
                    }
                    // model -> view
                    else {
                        if (destObj == null) {

                            linker.assign("associatedInstance", sourObj);
                            destObj = ((BeanLinkerImpl) linker).createNewInstancePrivate(rightItemClassAlias);
                            linker.assign("associatedInstance", null);
                            itemAssociationCollectionMap.put(sourObj, destObj);
                            
                            //=============================================
                            
                            linker.assign("addItemObj", destObj);
                            updateInfo.evalDestinationExpression("." + rightAddItemMethod + "(addItemObj)");
                            linker.assign("addItemObj", null);
                        }
                        
                        ((BeanLinkerImpl) linker).updatePrivate(updaters, sourObj, destObj, regex);

                        itensToDeleteFromDestination.remove(sourObj);
                    }
                }

                if (!inverted) {
                    // Delete itens from destination
                    for (Object itemToRemove : itensToDeleteFromDestination) {
                        Object sourObj = itemToRemove;
                        Object destObj = itemAssociationCollectionMap.get(sourObj);

                        linker.assign("removeItemObj", destObj);
                        updateInfo.evalDestinationExpression("." + rightRemoveItemMethod + "(removeItemObj)");
                        linker.assign("removeItemObj", null);

                        linker.assign("removeItemObj", destObj);
                        updateInfo.evalDestinationExpression("." + leftRemoveItemMethod + "(removeItemObj)");
                        linker.assign("removeItemObj", null);

                        itemAssociationCollectionMap.remove(sourObj);
                    }
                }
    
                System.out.println("Tamanho do collection cache:" + collectionLinkCache.size());
            }
        }
    
    // fazer o teste:
    // vincula collection<A> com collection<B>
    // porem A e B nao estao vinculados, antes estava ocorrendo erro
}
