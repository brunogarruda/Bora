//package br.com.bandtec.bora.controller.api.sms;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.twilio.exception.TwilioException;
//import com.twilio.http.TwilioRestClient;
//import com.twilio.rest.api.v2010.account.MessageCreator;
//import com.twilio.type.PhoneNumber;
//
//public class CriaMensagemSMS {
//	private static final Logger logger = LoggerFactory.getLogger(CriaMensagemSMS.class);
//	private final TwilioRestClient client;
//
//	public CriaMensagemSMS(TwilioRestClient client) {
//		this.client = client;
//	}
//
//	public void create(String to, String from, String body, String mediaUrl) {
//		MessageCreator messageCreator = new MessageCreator(new PhoneNumber(to), new PhoneNumber(from), body);
//		messageCreator.setMediaUrl(mediaUrl);
//		try {
//			messageCreator.create(this.client);
//		} catch (TwilioException e) {
//			logger.error("An exception occurred " + "trying to send a message to {}, exception: {}", to,
//					e.getMessage());
//		}
//	}
//
//}
