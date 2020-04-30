package br.uniamerica.cis.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uniamerica.cis.domain.model.Usuario;
import br.uniamerica.cis.domain.model.enumeration.StatusUsuario;
import br.uniamerica.cis.domain.repository.UsuarioRespository;

@Service
public class UsuarioService  {
	
	@Autowired
	private UsuarioRespository usuario;
	
	public Usuario salvar(Usuario user) {

		user.setStatus(StatusUsuario.ATIVO);		
		return usuario.save(user);
	}
}
