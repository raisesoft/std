package com.cd.bbh.common.section.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cd.bbh.common.enums.RoleEnum;

/**
 * 登录用户可以访问 该注解标注的方法
 * 
 * @author aowin
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BBHPerm {
	RoleEnum value() default RoleEnum.PNORMAL;
}
