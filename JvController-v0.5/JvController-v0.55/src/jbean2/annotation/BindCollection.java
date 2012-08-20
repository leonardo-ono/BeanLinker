package jbean2.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME) 
public @interface BindCollection {
    String property() default "";
    String to() default "";
    String viewAddMethod() default "";
    String viewRemoveMethod() default "";
    String viewClass() default "";
}
