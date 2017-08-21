package com.otmanel.springAdvancedAop.beans;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

public class MyRetourAdvice implements AfterReturningAdvice, ThrowsAdvice {

	@Override
	public void afterReturning(Object returnValue, Method meth, Object[] argsParam, Object cible) throws Throwable {
		System.out.println(" after returning ->" + meth.getName() + " valeur retourné ->" + returnValue);
	}
	public void afterThrowing(Method meth, Object[] argsParam, Object cible, RuntimeException ex) throws Throwable {
		System.out.println(" after throwing ->" + meth.getName() + " execption ->" + ex.getMessage());
	}
}
