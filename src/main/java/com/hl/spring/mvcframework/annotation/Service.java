package com.hl.spring.mvcframework.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Service {
    String value() default "";
}
