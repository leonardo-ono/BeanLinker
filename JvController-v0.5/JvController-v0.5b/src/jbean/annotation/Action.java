package jbean.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String label() default "";
    String property() default "";
    String exec() default "";
    String retVar() default "";
    Param[] params() default {};
    String update() default "";
    String process() default "";
}
