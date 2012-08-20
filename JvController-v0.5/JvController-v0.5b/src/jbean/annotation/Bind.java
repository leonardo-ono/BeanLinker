package jbean.annotation;

/**
 *
 * @author leo
 */
public @interface Bind {
    String property() default "";
    String to() default "";
    String from() default "";
    String toAndfrom() default "";
    String conversor() default "";
    String toConversor() default "";
    String fromConversor() default "";
    String validator() default "";
}
