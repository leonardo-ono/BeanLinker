package jbean2.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {
    String value();
}
