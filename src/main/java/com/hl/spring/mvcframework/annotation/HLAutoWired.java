package com.hl.spring.mvcframework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface HLAutoWired {
    String value() default "";
}
