package jason.Utils;

import jason.Utils.ClassesUtil;
import jason.annotation.Controller;
import jason.annotation.ControllerAOP;
import jason.proxy.CglibProxy;

import java.util.HashMap;
import java.util.List;

import com.aop.AOPService;

public class AnnotationManagement {
	private static AnnotationManagement myAnnotationManagement;

	private HashMap<String, Object> map = new HashMap<String, Object>();

	public static AnnotationManagement getInstance() {
		if (myAnnotationManagement == null) {
			myAnnotationManagement = new AnnotationManagement();
		}
		try {
			myAnnotationManagement.initMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myAnnotationManagement;
	}

	@SuppressWarnings("rawtypes")
	public HashMap<String, Object> initMap() throws Exception {
		AOPService aopService = null;
		List<String> getClass = ClassesUtil.getInstence().findClass();
		
		for (String className : getClass) {
			String classNameSub = className.substring(className
					.lastIndexOf(".") + 1);
			String newClassname = classNameSub.substring(0, 1).toLowerCase()
					+ classNameSub.substring(1);
			Class clazz = Class.forName(className);
			ControllerAOP control = (ControllerAOP) clazz
					.getAnnotation(ControllerAOP.class);
			if (control != null) {
				aopService = (AOPService) clazz.newInstance();
			}
		}
		for (String className : getClass) {
			String classNameSub = className.substring(className
					.lastIndexOf(".") + 1);

			String newClassname = classNameSub.substring(0, 1).toLowerCase()
					+ classNameSub.substring(1);
			Class clazz = Class.forName(className);
			Controller control = (Controller) clazz
					.getAnnotation(Controller.class);
			if (control != null) {
				String value = control.value();
				if (value.trim().isEmpty()) {
					value = newClassname;
				}
				// System.out.println(value);
				CglibProxy proxy = new CglibProxy(aopService);
				Object obj = proxy.getProxy(clazz);
				// System.out.println("----"+obj);
				// Object obj = clazz.newInstance();
				// System.out.println("key == "+value+"------ value   "+obj);
				map.put(value, obj);
			}
		}
		// System.out.println(map);
		return map;
	}

	public <T> T getController(String key) {

		Object obj = map.get(key);
		return (T) obj;

	}
}
