package com.ethan.emall.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendForgotPasswordEmail(String to) {

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("lmt860219@gmail.com");
			messageHelper.setTo("lmt860219@gmail.com");
			messageHelper.setSubject("123");
			messageHelper.setText("123");
		};

		javaMailSender.send(messagePreparator);
	}
}
