package br.com.bandtec.bora.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bandtec.bora.model.entity.Categoria;
import br.com.bandtec.bora.model.service.CategoriaService;

@RestController
@RequestMapping("/api")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> pesquisarCategorias(){
		
		List<Categoria> categorias = categoriaService.pesquisarTodasCategorias();
		
		if(categorias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(categorias);
		}
	}
}
