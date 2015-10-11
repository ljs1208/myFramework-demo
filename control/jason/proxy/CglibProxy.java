package jason.proxy;

import java.lang.reflect.Method;

import com.aop.AOPService;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	private AOPService aopService;

	public CglibProxy(AOPService aopService) {
		this.aopService = aopService;
	}

	public Object getProxy(Class clazz) {
		// 设置需要创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		// 通过字节码技术动态创建子类实例
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		if (aopService != null) {
			aopService.before(obj, method, args);
		}
		// System.out.println("代理前");
		Object result = null;
		try {
			result = proxy.invokeSuper(obj, args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (aopService != null) {
			aopService.after(obj, method, args);
		}
		// System.out.println("代理后" + result);
		return result;
	}

}
