package br.beanlinker.link;

import br.beanlinker.core.BeanLinker;
import br.beanlinker.core.BeanLinkerImpl;
import br.beanlinker.updater.Updater;
import java.util.List;

/**
 * Class BeanLink.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (02/01/2012 14:20)
 */
public class BeanLink extends Link {

    public BeanLink(BeanLinker linker
            , String leftProperty, String rightProperty) {
        
        super(linker);
        this.leftProperty = leftProperty;
        this.rightProperty = rightProperty;
    }

    @Override
    public void update(List<Updater> updaters
            , Object sourceObject, Object destinationObject, String ... regex) throws Exception {
        
        final UpdateInfo updateInfo = generateUpdateInfo(sourceObject, destinationObject);
        if (updateInfo.needsUpdate) {
            Object sourceObjectArg = updateInfo.evalSourceExpression("");
            Object destinationObjectArg = updateInfo.evalDestinationExpression("");
            ((BeanLinkerImpl) linker).updatePrivate(updaters
                    , sourceObjectArg, destinationObjectArg, regex);
        }
    }
    
}
