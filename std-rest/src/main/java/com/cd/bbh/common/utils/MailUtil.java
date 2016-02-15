package com.cd.bbh.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class MailUtil {
	private static final String HOST = "smtp.ym.163.com";
	private static final String ACCOUNT = "feedback@itelchase.com";
	private static final String PASSWORD = "wapwap12";
	private static final String FROM = "feedback@itelchase.com";
	private static final String TO = "service@itelchase.com";

	public static void sendEmail(String content, String device) {
		sendEmail(HOST, ACCOUNT, PASSWORD, FROM, TO, "用户反馈", content, device);
	}

	public static void sendEmail(String serverHost, String account, String password, String from, String to, String subject, String content, String device) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", serverHost);
		props.put("mail.smtp.user", account);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.auth", "true");
		props.put("mail.transport.protocol", "smtp");
		Session session = createSession(props);
		Message msg = createMessage(session, from, to, subject, content, device);
		Transport transport;

		try {
			transport = session.getTransport();

			transport.connect(serverHost, account, password);
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception cause) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR, cause);
		}
	}

	private static Message createMessage(Session session, String from, String to, String subject, String content, String device) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(content);
			msg.saveChanges();
			return msg;
		} catch (Exception cause) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR, cause);
		}
	}

	@SuppressWarnings("unused")
	private static String createContent(String content, String fromDevice) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> map = new HashMap<String, String>();
			map.put("content", content);
			map.put("from", fromDevice);
			return mapper.writeValueAsString(map);
		} catch (Exception cause) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR, cause);
		}
	}

	private static Session createSession(Properties props) {
		final String account = (String) props.get("mail.smtp.user");
		final String password = (String) props.get("mail.smtp.password");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
			}
		});
		// session.setDebug(true);
		return session;
	}

}
