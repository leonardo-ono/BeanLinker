package jbean.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    String var();
    String prop();
    String conversor();
    String validator();
}
