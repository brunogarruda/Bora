//package br.com.bandtec.bora.controller.api.sms;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class ApiSms {
//	// Configurando as credenciais
//	public static final String ACCOUNT_SID = "ACa0e96740f122c2deeb975a0ea58763fa";
//	public static final String AUTH_TOKEN = "11d604aa04aa10b3657cd159dab2fec6";
//
//	public static void main(String[] args) {
//		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//		Message message = Message
//				.creator(new PhoneNumber("+5511983355797"), new PhoneNumber("+5531933007316"), "Ola Mundo").create();
//		System.out.println(message.getSid());
//
//	}
//
//}
