package br.uniamerica.cis.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uniamerica.cis.domain.model.Usuario;
import br.uniamerica.cis.domain.repository.UsuarioRespository;

@Service
public class UsuarioService  {
	@Autowired
	private UsuarioRespository usuario;
	
	public Usuario salvar(Usuario user) {
		user.setDataNascimento(OffsetDateTime.now());
		user.setStatus(Usuario.StatusUsuario.ATIVO);
		return usuario.save(user);
	}
	
	
	
}
