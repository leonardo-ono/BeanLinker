package ono.leo.jvcontroller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ValidatorClass annotation.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 11:38)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidatorClass {
    String value() default "";
}
