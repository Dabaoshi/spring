package com.hl.spring.mvcframework.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface HLRequestParm {
    String value() default "";
}
