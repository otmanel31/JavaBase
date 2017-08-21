package com.otmanel.springAdvancedAop.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class TrimAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method meth, Object[] argsMeth, Object target) throws Throwable {
		System.out.println("dans TrimAdvice, apel de " + meth.getName() +
				" avec " + Arrays.toString(argsMeth));
		if (argsMeth.length >= 1 ) {
			// appel de trim sur le 1er argument
			argsMeth[0] = argsMeth[0].toString().trim();
		}
	}

}
