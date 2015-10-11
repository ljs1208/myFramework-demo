package com.aop.Impl;

import jason.annotation.ControllerAOP;

import java.lang.reflect.Method;

import com.aop.AOPService;

@ControllerAOP(value = "1")
public class AOPSeviceImpl implements AOPService {

	@Override
	public void after(Object obj, Method method, Object[] args) {
		System.out.println("after" + "类名" + obj.getClass().getName() + "方法"
				+ method.getName());

	}

	@Override
	public void before(Object obj, Method method, Object[] args) {
		System.out.println("before" + "类名" + obj.getClass().getName() + "方法"
				+ method.getName());

	}

}
