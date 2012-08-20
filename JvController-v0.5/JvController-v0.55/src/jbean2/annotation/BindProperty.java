package jbean2.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME) 
public @interface BindProperty { 
    String property() default "";
    String to() default "";
    String from() default "";
    String toAndfrom() default "";
    String conversor() default "";
    String toConversor() default "";
    String fromConversor() default "";
    String validator() default "";
}
