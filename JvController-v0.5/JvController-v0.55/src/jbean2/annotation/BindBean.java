package jbean2.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME) 
public @interface BindBean {
    String property() default "";
    String to() default "";
}
