package com.atguigu.atcrowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {
	@RequestMapping("login")
	public String login() {
		System.out.println("11111111");
		return "login";
	}
}
