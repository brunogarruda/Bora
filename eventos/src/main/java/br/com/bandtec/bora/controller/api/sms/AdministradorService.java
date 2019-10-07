//package br.com.bandtec.bora.controller.api.sms;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//
//import org.springframework.stereotype.Service;
//import com.google.gson.Gson;
//
//
//@Service
//public class AdministradorService {
//    private String filePath;
//
//	public AdministradorService() {
//		this.filePath = getClass().getClassLoader().getResource("administrators.json").getPath();
//	}
//
//	public AdministradorService(String filePath) {
//		this.filePath = filePath;
//	}
//	
//	public Administrador[] getAdministradores() {
//		try {
//			return new Gson().fromJson(new FileReader(filePath), Administrador[].class);
//		}catch (FileNotFoundException e) {
//            e.printStackTrace();
//            
//            return new Administrador[0];
//		}
//	}
//	
//
//    
//	
//
//}
