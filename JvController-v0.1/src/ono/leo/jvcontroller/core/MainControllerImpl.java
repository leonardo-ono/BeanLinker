package ono.leo.jvcontroller.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToBigDecimal;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToBoolean;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToBooleanPrim;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToDate;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToInt;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToInteger;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToLong;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToShort;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToShortPrim;
import ono.leo.jvcontroller.conversor.default_.ConversorStringToString;
import ono.leo.jvcontroller.validator.ValidationException;
import ono.leo.jvcontroller.validator.Validator;
import ono.leo.jentity.Entity;

/**
 *
 * @author leo
 */
public class MainControllerImpl implements MainController {

    protected Map<String, Object> objects = new HashMap<String, Object>();
    protected List<Bind> binds = new ArrayList<Bind>();

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
            new Bind(viewProperty, beanProperty
                , messageTextProperty, messageSeverityProperty
                , conversorId, validatorIds));
    }

    @Override
    public Bind getBind(String viewProperty, String beanProperty) {
        for (Bind bind : binds) {
            if (bind.viewProperty.equals(viewProperty)
                    && bind.beanProperty.equals(beanProperty)) 
                return bind;
        }
        return null;
    }

    private String getConversorAutomatically(
            String viewProperty, String beanProperty) {
        
        String conversor = null;
        
        if (getType(viewProperty) == null) return conversor;
        
        if (getType(viewProperty).equals(String.class)) {
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
    public boolean hasSetter(String property) {
        return Entity.hasSetter(objects, property);
    }

    @Override
    public Object invoke(String method, Object... arg) {
        return Entity.invoke(objects, method, arg);
    }

    @Override
    public void updateViews(String... viewUpdateIds) 
            throws ConstraintException {

        clearAllMessages();
        ConstraintException ce = new ConstraintException();
        for (Bind bind : binds) {
            boolean needsUpdate = false;
            if (viewUpdateIds.length == 0) needsUpdate = true;
            for (String viewUpdateId : viewUpdateIds) {
                if (bind.viewProperty.equals(viewUpdateId) 
                    || bind.viewProperty.startsWith(viewUpdateId + ".")) {
                    
                    needsUpdate = true;
                    break;
                }
            }
            if (!needsUpdate) continue;
            Object beanValue = get(bind.beanProperty);
            Conversor conversor = null;
            if (bind.conversorId != null) 
                conversor = (Conversor) get(bind.conversorId);
            try {
                if (conversor != null) 
                    beanValue = conversor.getAsView(beanValue);
            }
            catch (ConversionException ex) {
                ex.setViewProperty(bind.viewProperty);
                ce.getConstraintExceptions().add(ex);
                if (bind.messageTextProperty != null) 
                    set(bind.messageTextProperty, ex.getMessage());
                
                continue;
            }
            
            set(bind.viewProperty, beanValue);
        }
        if (ce.getErrorCount() > 0) {
            ce.setMessage("Há " + ce.getErrorCount() + " inconsistência(s) !");
            throw ce;
        }
    }

    @Override
    public void updateBeans(String... viewExecuteIds) 
            throws ConstraintException {
        
        clearAllMessages();
        ConstraintException ce = new ConstraintException();
        for (Bind bind : binds) {
            if (!hasSetter(bind.beanProperty)) continue;
            boolean needsUpdate = false;
            if (viewExecuteIds.length == 0) needsUpdate = true;
            for (String viewExecuteId : viewExecuteIds) {
                if (bind.viewProperty.equals(viewExecuteId) 
                    || bind.viewProperty.startsWith(viewExecuteId + ".")) {
                    
                    needsUpdate = true;
                    break;
                }
            }
            if (!needsUpdate) continue;
            Object viewValue = get(bind.viewProperty);
            Conversor conversor = null;
            if (bind.conversorId != null) 
                conversor = (Conversor) get(bind.conversorId);
            
            try {
                if (conversor != null) 
                    viewValue = conversor.getAsBean(viewValue);
            }
            catch (ConversionException ex) {
                ex.setViewProperty(bind.viewProperty);
                ce.getConstraintExceptions().add(ex);
                if (bind.messageTextProperty != null) 
                    set(bind.messageTextProperty, ex.getMessage());
                
                continue;
            }

            if (bind.validatorIds != null) {
                for (String validatorId : bind.validatorIds) {
                    Validator validator = (Validator) get(validatorId);
                    try {
                        if (validator != null) validator.validate(viewValue);
                    }
                    catch (ValidationException ex) {
                        ex.setViewProperty(bind.viewProperty);
                        ce.getConstraintExceptions().add(ex);
                        if (bind.messageTextProperty != null) 
                            set(bind.messageTextProperty, ex.getMessage());
                        
                        continue;
                    }
                }
            }
            set(bind.beanProperty, viewValue);
        }
        if (ce.getErrorCount() > 0) {
            ce.setMessage("Há " + ce.getErrorCount() + " inconsistência(s) !");
            throw ce;
        }
    }
    
    private void clearAllMessages() {
        for (Bind bind : binds) {
            if (bind.messageTextProperty != null) 
                set(bind.messageTextProperty, "");
        }
    }
    
    protected class Bind {
        public String viewProperty;
        public String beanProperty;
        public String messageTextProperty;
        public String messageSeverityProperty;
        public String conversorId;
        public List<String> validatorIds = new ArrayList<String>();

        private Bind(String viewProperty, String beanProperty
                , String messageTextProperty, String messageSeverityProperty
                , String conversorId, String[] validatorIds) {
            this.viewProperty = viewProperty;
            this.beanProperty = beanProperty;
            this.messageTextProperty = messageTextProperty;
            this.messageSeverityProperty = messageSeverityProperty;
            this.conversorId = conversorId;
            this.validatorIds.addAll(Arrays.asList(validatorIds));
        }

        @Override
        public String toString() {
            return "Bind{" + "viewProperty=" + viewProperty 
                    + ", beanProperty=" + beanProperty 
                    + ", messageTextProperty=" + messageTextProperty 
                    + ", messageSeverityProperty=" + messageSeverityProperty 
                    + ", conversorId=" + conversorId 
                    + ", validatorIds=" + validatorIds + '}';
        }

    }
    
}
