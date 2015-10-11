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
		// ������Ҫ�����������
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		// ͨ���ֽ��뼼����̬��������ʵ��
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		if (aopService != null) {
			aopService.before(obj, method, args);
		}
		// System.out.println("����ǰ");
		Object result = null;
		try {
			result = proxy.invokeSuper(obj, args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (aopService != null) {
			aopService.after(obj, method, args);
		}
		// System.out.println("�����" + result);
		return result;
	}

}
