package com.packtracker.site.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(String[] recipients, String petName) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		String subject = petName+" went missing from your area!";
		String body = "Hi! "+petName+" was just reported missing from your area."
					  + " Please log in to Pack Tracker application to see if you can help.";
		
		message.setFrom("packtracker123@gmail.com");
		message.setTo(recipients);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
	}
}
