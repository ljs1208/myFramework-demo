package com.controller;

import jason.annotation.Controller;

@Controller("user")
public class UserController {
	public String userList() {
		System.out.println("����ִ��userList");
		return "123";
	}

	public void addUser() {
		System.out.println("add user");
	}

	public void deleteUser() {
		System.out.println("delete user");
	}

	public void modifyUser() {
		System.out.println("modify user");
	}
}
