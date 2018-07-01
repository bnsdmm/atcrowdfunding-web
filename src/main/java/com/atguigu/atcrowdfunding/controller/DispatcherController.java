package com.atguigu.atcrowdfunding.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
public class DispatcherController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/doLogin")
	public String dologin(User user, Model model) throws UnsupportedEncodingException {
		String loginacct=user.getLoginacct();
		byte[] bs=loginacct.getBytes("ISO8859-1");
		loginacct =new String(bs,"UTF-8");
		System.out.println(loginacct);
		User dbUser = userService.query4login(user);
		if (dbUser != null) {
			return "main";
		} else {
			String errorMsg = "登录账号或密码不正确，请重新输入";
			model.addAttribute("errorMsg", errorMsg);
			return "redirect:login";
		}

	}
}
