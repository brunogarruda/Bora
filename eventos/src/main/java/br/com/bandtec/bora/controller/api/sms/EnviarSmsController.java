//package br.com.bandtec.bora.controller.api.sms;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.twilio.exception.TwilioException;
//import com.twilio.http.TwilioRestClient;
//import com.twilio.rest.api.v2010.account.MessageCreator;
//import com.twilio.type.PhoneNumber;
//
//@RestController
//public class EnviarSmsController {
//	@RequestMapping("/")
//	public List<Employee> getEmployees() {
//		List<Employee> employeesList = new ArrayList<Employee>();
//		employeesList.add(new Employee(1, "lokesh", "gupta", "howtodoinjava@gmail.com"));
//		return employeesList;
//	}
//}
//// @PostMapping("/api/sms")
////	public ResponseEntity<String> validateLogin(@RequestBody Usuario usuario) {
////		usuario.setCelular("111");
////		return ResponseEntity.ok("Success");
////		if (usuario.getCelular().equals(usuario.getCelular())) {
////			return ResponseEntity.ok("Success");
////		}
////		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed");
//// }
////	private static final Logger logger = LoggerFactory.getLogger(CriaMensagemSMS.class);
////	private final TwilioRestClient client;
////	Credenciais credenciais = new Credenciais();
////
////	public EnviarSmsController(TwilioRestClient client) {
////		this.client = client;
////	}
////	public void create(String to, String from, String body, String mediaUrl) {
////		MessageCreator messageCreator = new MessageCreator(new PhoneNumber(to), new PhoneNumber(from), body);
////		messageCreator.setMediaUrl(mediaUrl);
////		try {
////			messageCreator.create(this.client);
////		} catch (TwilioException e) {
////			logger.error("An exception occurred " + "trying to send a message to {}, exception: {}", to,
////					e.getMessage());
////		}
////	}
////	public ResponseEntity<String> create(@RequestBody Usuario usuario) {
////			create(usuario.getCelular(), credenciais.getPhoneNumber(), "Ola Mundo", "teste");
////			return ResponseEntity.ok("Success");
////		
////	}
