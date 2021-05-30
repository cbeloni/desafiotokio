package br.com.tokio.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokio.dto.UsuarioDto;
import br.com.tokio.entities.Usuario;
import br.com.tokio.service.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaPorId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscaPorId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Usuario> buscaPorCpf(@PathVariable Long cpf){
		Usuario usuario = usuarioService.buscaPorCpf(cpf);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioDto>> buscaPorNome(@PathVariable String nome){
		Query queryNamed = entityManager.createNamedQuery("Usuario.findByNome");
		queryNamed.setParameter("nome", "%" + nome + "%");
		List<UsuarioDto> usuarioDtomapping = (List<UsuarioDto>) queryNamed.getResultList();
		
		return new ResponseEntity<List<UsuarioDto>>(usuarioDtomapping, HttpStatus.OK);
	}

}
