package teste2;

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
            if ((Integer) value > 999) {
                throw new ValidationException("Numero precisa ser menor que 1000 !", updater);
            }
        }
    }
    
}
