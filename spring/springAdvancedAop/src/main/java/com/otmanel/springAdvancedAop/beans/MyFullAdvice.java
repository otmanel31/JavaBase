package com.otmanel.springAdvancedAop.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyFullAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation meth) throws Throwable {
		Object[] args = meth.getArguments();
		if (args.length < 1 || args[0] == null) {
			System.out.println("pas d'arguments ou null args");
			return 0;
		}
		// je passe largument en minuscules
		args[0] = args[0].toString().toLowerCase();
		// appel de la fonction originale
		Object returnValue = meth.proceed();
		System.out.println(" valeur renvoyé par la methide " + returnValue);
		
		return returnValue;
	}

}
