package com.aop.Impl;

import jason.annotation.ControllerAOP;

import java.lang.reflect.Method;

import com.aop.AOPService;

@ControllerAOP(value = "1")
public class AOPSeviceImpl implements AOPService {

	@Override
	public void after(Object obj, Method method, Object[] args) {
		System.out.println("after" + "����" + obj.getClass().getName() + "����"
				+ method.getName());

	}

	@Override
	public void before(Object obj, Method method, Object[] args) {
		System.out.println("before" + "����" + obj.getClass().getName() + "����"
				+ method.getName());

	}

}
