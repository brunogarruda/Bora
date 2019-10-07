//package br.com.bandtec.bora.controller.api.sms;
//
//
//import com.twilio.http.TwilioRestClient;
//
//public class Cliente {
//	private Credenciais credenciais;
//    private CriaMensagemSMS mensagemSMS;
//
//    public Cliente() {
//        this.credenciais = new Credenciais();
//        this.mensagemSMS = new CriaMensagemSMS(
//                new TwilioRestClient.Builder(credenciais.getAccountSid(), credenciais.getAuthToken()).build()
//        );
//    }
//
//    public Cliente(CriaMensagemSMS mensagemSMS, Credenciais credenciais) {
//        this.credenciais = credenciais;
//        this.mensagemSMS = mensagemSMS;
//    }
//
//    public void sendMessage(String to, String message, String mediaUrl) {
//    	mensagemSMS.create(to, credenciais.getPhoneNumber(), message, mediaUrl);
//    }
//	
//}
