package beanlinker4test;

import br.beanlinker.updater.Updater;
import br.beanlinker.validator.ValidationException;
import br.beanlinker.validator.Validator;

/**
 *
 * @author leonardo
 */
public class MyValidator implements Validator {

    @Override
    public void validate(Object value, Updater updater) throws ValidationException {
        if (value instanceof Integer) {
            Integer v = (Integer) value;
            if (v>100) {
                throw new ValidationException("Valor deve ser menor que 100 !", updater);
            }
        }
    }
    
}
