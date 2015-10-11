package jason.Utils;

import java.lang.reflect.Method;

public class MethodUtil {

	private static MethodUtil getMethod;

	public static MethodUtil getInstence() {
		if (getMethod == null) {
			getMethod = new MethodUtil();
		}
		return getMethod;
	}

	public Method getMethod(String methodName ,Class clazz) {
		Method[] methods = clazz.getMethods();
		for(Method method :methods){
			if(method.getName().equals(methodName) ){

				return method;
			}
			
		}
		return null;

	}
}
