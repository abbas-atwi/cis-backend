package br.uniamerica.cis.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.uniamerica.cis.api.dto.UsuarioDTO;
import br.uniamerica.cis.api.dto.input.UsuarioInput;
import br.uniamerica.cis.domain.model.Usuario;
import br.uniamerica.cis.domain.repository.UsuarioRespository;
import br.uniamerica.cis.domain.service.UsuarioService;

@RequestMapping("/usuarios")// Busca os usuarios
@RestController // Pq estou utilizando Rest
public class UsuarioController {
	
	@Autowired // serve pra injecao de idependencia, as classes ficam menos dependentes uma da outro desacoplota
	private UsuarioRespository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // ele retorna 201
	private Usuario adicionar(@Valid @RequestBody UsuarioInput user) { //Ele add e retorna cliente
		Usuario novoUsuario = toEntity(user);
		return usuarioService.salvar(novoUsuario);
	}

	@GetMapping
	private List <Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{usuarioId}")
	private ResponseEntity <UsuarioDTO> buscar(@PathVariable Long usuarioId){
		
		Optional <Usuario> user = usuarioRepository.findById(usuarioId);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(toModel(user.get()));
	}
	
	//converte uma objeto entidade para um modelo representacional
	private UsuarioDTO toModel(Usuario user) {
		return modelMapper.map(user, UsuarioDTO.class);
	}
	
	//converte um modelo representacional para um objeto entitade
	private Usuario toEntity(UsuarioInput user) {
		return modelMapper.map(user, Usuario.class);
	}
}
