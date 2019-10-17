package br.com.bandtec.bora.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bandtec.bora.model.entity.Categoria;
import br.com.bandtec.bora.model.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaController {

	private final CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> buscarTodasCategorias() throws Exception{
		return ResponseEntity.ok(categoriaService.buscarTodasCategorias());
	}
}
