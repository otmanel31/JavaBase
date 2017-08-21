package com.otmanel.springAdvancedAop.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class NullStringProtectionAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method meth, Object[] argsMeth, Object arg2) throws Throwable {
		System.out.println("dans null stringadvice, apel de " + meth.getName() +
				" avec " + Arrays.toString(argsMeth));
		if (argsMeth.length >= 1 && argsMeth[0] == null && 
				meth.getParameterTypes()[0].equals(String.class))  {
			argsMeth[0] = "";
		}
	}

}
