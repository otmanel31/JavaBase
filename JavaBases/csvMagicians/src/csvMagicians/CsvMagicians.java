package csvMagicians;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import csvMagicians.annotations.BooleanConverter;

public class CsvMagicians {
	public static String beanToCsv(Object bean) {
		try {
			Class beanClass = bean.getClass();
			
			List<Method> getters = Arrays.stream(beanClass.getMethods())
						.filter(m -> isGetter(m))
						.sorted((m1,m2)-> m1.getName().substring(3).compareTo(m2.getName().substring(3)))
						.collect(Collectors.toList());
			
			StringBuilder sb = new StringBuilder(beanClass.getSimpleName());
			boolean first =  true;
			for (Method getter: getters) {
				/*if (!first) {
					sb.append(";");
				}*/
				sb.append(";");
				if (getter.getReturnType().equals(boolean.class)) {
					String trueValue = "true";
					String falseValue = "false";
					if (getter.isAnnotationPresent(BooleanConverter.class)) {
						BooleanConverter bc = getter.getAnnotation(BooleanConverter.class);
						trueValue= bc.trueValue();
						falseValue = bc.falseValue();
					}
					sb.append((boolean)getter.invoke((bean))? trueValue: falseValue);
				}
				else {
					sb.append(getter.invoke(bean));
					//first = false;
				}
				
			}
		}catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{e.printStackTrace(); }
		
		return "";
	}
	
	public static boolean isGetter(Method m) {
		if (!m.getName().startsWith("get")) return false;
		if (!Modifier.isPublic(m.getModifiers()) || Modifier.isStatic(m.getModifiers())) return false;
		if (m.getReturnType().equals(void.class)) return false;
		if (m.getParameterTypes().length != 0) return false; // pas dargs
		// puis que les guetters avec les type voulu
		Class returnType = m.getReturnType();
		if (returnType.equals(String.class) || returnType.equals(double.class) || returnType.equals(int.class)
				|| returnType.equals(Boolean.class) || returnType.equals(char.class)) return true;
		return false;
	}
	private static boolean isSetter(Method m) {
		// si la méthode ne commence pas par "set", ce n'est pas un setter
		if (!m.getName().startsWith("set"))
			return false;
		// si ce n'est pas public, pas intéréssé
		if (!Modifier.isPublic(m.getModifiers()))
			return false;
		// si ca ne renvoie pas void, pas un setter
		if (!m.getReturnType().equals(void.class))
			return false;
		// s'il ya plus ou moins de 1 parametre, ce n'est pas un setter
		if (m.getParameterTypes().length != 1)
			return false;
		return true;
	}
	
	// A COMPLETER IMPORT CSV MAGICIAN PROF
	
	public static Object CsvToBean(String line, Class beanClass) {
		try {
			Object bean = beanClass.newInstance();
			List<Method> setters = Arrays.stream(beanClass.getMethods())
					.filter(m -> isSetter(m))
					.sorted((m1,m2)->  m1.getName().substring(3).compareTo(m2.getName().substring(3)))
					.collect(Collectors.toList());
			String[] champs = line.split(";");
			if (champs[0].equals(beanClass.getSimpleName())) {
				throw new IllegalArgumentException("Pas la bonne classe de bean");
			}
			int i = 1;
			for (Method setter : setters) {
				String currentField = champs[i];
				if (setter.getParameterTypes()[0].equals(String.class)) setter.invoke(bean, currentField);
				else if (setter.getParameterTypes()[0].equals(double.class)) setter.invoke(bean, Double.parseDouble(currentField));
				else if(setter.getParameterTypes()[0].equals(int.class)) setter.invoke(bean, Integer.parseInt(currentField));
				else if (setter.getParameterTypes()[0].equals(char.class)) 
					setter.invoke(bean, currentField.charAt(0));
				else if (setter.getParameterTypes()[0].equals(Boolean.class)) {
					String trueValue = "true";
					String falseValue = "false";
					
					if(setter.isAnnotationPresent(BooleanConverter.class)) {
						BooleanConverter bc = setter.getAnnotation(BooleanConverter.class);
						trueValue = bc.trueValue();
						falseValue = bc.falseValue();
					}
				}
			}
		}catch (Exception e) {
			
		}
	}
}
