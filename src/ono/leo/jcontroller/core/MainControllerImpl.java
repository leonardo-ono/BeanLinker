package ono.leo.jcontroller.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ono.leo.jcontroller.conversor.Conversor;
import ono.leo.jcontroller.conversor.default_.ConversorStringToBigDecimal;
import ono.leo.jcontroller.conversor.default_.ConversorStringToBoolean;
import ono.leo.jcontroller.conversor.default_.ConversorStringToBooleanPrim;
import ono.leo.jcontroller.conversor.default_.ConversorStringToDate;
import ono.leo.jcontroller.conversor.default_.ConversorStringToInt;
import ono.leo.jcontroller.conversor.default_.ConversorStringToInteger;
import ono.leo.jcontroller.conversor.default_.ConversorStringToLong;
import ono.leo.jcontroller.conversor.default_.ConversorStringToShort;
import ono.leo.jcontroller.conversor.default_.ConversorStringToShortPrim;
import ono.leo.jcontroller.conversor.default_.ConversorStringToString;
import ono.leo.jcontroller.validator.Validator;
import ono.leo.jentity.Entity;

/**
 *
 * @author leo
 */
public class MainControllerImpl implements MainController {

    private Map<String, Object> objects = new HashMap<String, Object>();
    private List<Bind> binds = new ArrayList<Bind>();

    public MainControllerImpl() {
        addDefaultConversors();
    }
    
    private void addDefaultConversors() {
        objects.put("conversorStringToBigDecimal"
                , new ConversorStringToBigDecimal());
        
        objects.put("conversorStringToBoolean"
                , new ConversorStringToBoolean());
        
        objects.put("conversorStringToBooleanPrim"
                , new ConversorStringToBooleanPrim());
        
        objects.put("conversorStringToDate"
                , new ConversorStringToDate());
        
        objects.put("conversorStringToInt"
                , new ConversorStringToInt());
        
        objects.put("conversorStringToInteger"
                , new ConversorStringToInteger());
        
        objects.put("conversorStringToLong"
                , new ConversorStringToLong());
        
        objects.put("conversorStringToShort"
                , new ConversorStringToShort());
        
        objects.put("conversorStringToShortPrim"
                , new ConversorStringToShortPrim());
        
        objects.put("conversorStringToString"
                , new ConversorStringToString());
    }
    
    @Override
    public void addView(String id, Object view) {
        objects.put(id.trim(), view);
    }

    @Override
    public void addBean(String id, Object bean) {
        objects.put(id.trim(), bean);
    }

    @Override
    public void addConversor(String id, Object conversor) {
        objects.put(id.trim(), conversor);
    }

    @Override
    public void addValidator(String id, Object validator) {
        objects.put(id.trim(), validator);
    }
    
    public void bindProperty(String viewProperty, String beanProperty) {
        bindProperty(viewProperty, beanProperty, null, null, null);
    }
    
    @Override
    public void bindProperty(String viewProperty, String beanProperty
            , String messageTextProperty, String messageSeverityProperty
            , String conversorId, String... validatorIds) {
        
        if (conversorId == null) 
            conversorId = getConversorAutomatically(viewProperty, beanProperty);
        
        binds.add(
            new Bind(viewProperty, beanProperty, conversorId, validatorIds));
    }

    private String getConversorAutomatically(
            String viewProperty, String beanProperty) {
        
        String conversor = null;
        if (get(viewProperty).getClass().equals(String.class)) {
            if (getType(beanProperty).equals(BigDecimal.class)) 
                conversor="conversorStringToBigDecimal";
                    
            else if (getType(beanProperty).equals(Boolean.class)) 
                conversor="conversorStringToBoolean";
                    
            else if (getType(beanProperty).equals(boolean.class)) 
                conversor="conversorStringToBooleanPrim";
                    
            else if (getType(beanProperty).equals(Date.class)) 
                conversor="conversorStringToDate";
                    
            else if (getType(beanProperty).equals(int.class)) 
                conversor="conversorStringToInt";
                    
            else if (getType(beanProperty).equals(Integer.class)) 
                conversor="conversorStringToInteger";
                    
            else if (getType(beanProperty).equals(Long.class)) 
                conversor="conversorStringToLong";
                    
            else if (getType(beanProperty).equals(Short.class)) 
                conversor="conversorStringToShort";
                    
            else if (getType(beanProperty).equals(short.class)) 
                conversor="conversorStringToShortPrim";
                    
            else if (getType(beanProperty).equals(String.class)) 
                conversor="conversorStringToString";
        }
        return conversor;
    }
    
    @Override
    public Object get(String property) {
        return Entity.get(objects, property);
    }

    @Override
    public Class getType(String property) {
        return Entity.getType(objects, property);
    }
    
    @Override
    public void set(String property, Object value) {
        Entity.set(objects, property, value);
    }

    @Override
    public Object invoke(String method, Object... arg) {
        return Entity.invoke(objects, method, arg);
    }

    @Override
    public void updateViews(String... viewUpdateIds) 
            throws ConstraintException {
        
        for (Bind bind : binds) {
            Object beanValue = get(bind.beanProperty);
            Conversor conversor = (Conversor) get(bind.conversorId);
            if (conversor != null) beanValue = conversor.getAsView(beanValue);
            set(bind.viewProperty, beanValue);
        }
    }

    @Override
    public void updateBeans(String... viewExecuteIds) 
            throws ConstraintException {
        
        for (Bind bind : binds) {
            Object viewValue = get(bind.viewProperty);
            Conversor conversor = (Conversor) get(bind.conversorId);
            if (conversor != null) viewValue = conversor.getAsBean(viewValue);
            if (bind.validatorIds != null) {
                for (String validatorId : bind.validatorIds) {
                    Validator validator = (Validator) get(validatorId);
                    if (validator != null) validator.validate(viewValue);
                }
            }
            set(bind.beanProperty, viewValue);
        }
    }

    private class Bind {
        String viewProperty;
        String beanProperty;
        String conversorId;
        String[] validatorIds;

        public Bind(String viewProperty, String beanProperty
                , String conversorId, String[] validatorIds) {
            
            this.viewProperty = viewProperty;
            this.beanProperty = beanProperty;
            this.conversorId = conversorId;
            this.validatorIds = validatorIds;
        }

        @Override
        public String toString() {
            return "Bind{" + "viewId=" + viewProperty 
                    + ", beanId=" + beanProperty 
                    + ", conversorId=" + conversorId 
                    + ", validatorIds=" + validatorIds + '}';
        }
    }
    
}
