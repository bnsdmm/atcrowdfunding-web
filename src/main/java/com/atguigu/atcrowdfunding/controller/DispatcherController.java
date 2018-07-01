package com.atguigu.atcrowdfunding.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
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

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// session.removeAttribute("loginUser");
		session.invalidate();
		return "redirect:login";
	}

	@ResponseBody
	@RequestMapping("/doAjaxLogin")
	public Object doAjaxLogin(User user, HttpSession session) {
		AjaxResult result = new AjaxResult();
		User dbUser = userService.query4login(user);
		if (dbUser != null) {
			session.setAttribute("loginUser", dbUser);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/main")
	public String Main() {
		return "main";
	}

	@RequestMapping("/doLogin")
	public String dologin(User user, Model model) throws UnsupportedEncodingException {
		String loginacct = user.getLoginacct();
		byte[] bs = loginacct.getBytes("ISO8859-1");
		loginacct = new String(bs, "UTF-8");
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
