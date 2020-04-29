package br.uniamerica.cis.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.uniamerica.cis.domain.model.Usuario;
import br.uniamerica.cis.domain.repository.UsuarioRespository;
import br.uniamerica.cis.domain.service.UsuarioService;

@RequestMapping("/usuarios")// Busca os usuarios
@RestController // Pq estou utilizando Rest
public class UsuarioController {
	@Autowired // serve pra injecao de idependencia, as classes ficam menos dependentes uma da outro desacoplota
	private UsuarioRespository usuario;
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // ele retorna 201
	private Usuario adicionar(@RequestBody Usuario user) { //Ele add e retorna cliente
		return service.salvar(user);
	}
	@GetMapping
	private List <Usuario> listar(){
		return usuario.findAll();
	}
	@GetMapping("/{usuarioId}")
	private ResponseEntity <Usuario> buscar(@PathVariable Long usuarioId){
		Optional <Usuario> ids = usuario.findById(usuarioId);
		if(!ids.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ids.get());
	}
}
