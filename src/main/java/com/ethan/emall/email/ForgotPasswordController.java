package com.ethan.emall.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/email")
@RestController
public class ForgotPasswordController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendMail")
	public String forgotPassword(@RequestParam String email) {

		// 發送信件
		emailService.sendForgotPasswordEmail(email);

		return "success";

	}

}
