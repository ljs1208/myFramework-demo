package com.aop;

import java.lang.reflect.Method;

public interface AOPService {

	void after(Object obj, Method method,Object[] args);

	void before(Object obj, Method method,Object[] args);

}
