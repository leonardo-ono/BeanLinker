package ono.leo.jvcontroller.annotation.binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BindProperty annotation.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 14:06)
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.ANNOTATION_TYPE)
public @interface BindProperty { 
    String view() default "";
    String viewFrom() default "";
    String viewTo() default "";
    String model() default "";
    String modelTo() default "";
    String modelFrom() default "";
    String conversor() default "";
    String toConversor() default "";
    String fromConversor() default "";
    String validator() default "";
    String viewVar() default "";
    String modelVar() default "";
    String viewInstance() default "";
    String modelInstance() default "";
}
