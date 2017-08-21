package com.otmanel.springExo2Memento.beans;

import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Memento implements MethodInterceptor {
	
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("intercepted method ->" + arg0.getMethod().getName());
		Map<Integer, Integer> values;
		Object[] args = arg0.getArguments();
		if (args.length < 1 || args[0] == null) {
			System.out.println("pas d'arguments ou null args");
			return 0;
		//args[0] = (int)args[0];
		}
		int returnValue =  (int)arg0.proceed();
		//je stoke mon argument ds mon tableau ainsi que le resultat de son calcul
		
		return null;
	}

}
