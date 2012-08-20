package jbean.annotation;

/**
 *
 * @author leo
 */
public @interface AddBean {
    String name() default "";
    String className() default "";
}
