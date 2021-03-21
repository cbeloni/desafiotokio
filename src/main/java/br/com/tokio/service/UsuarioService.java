package br.com.tokio.service;

import br.com.tokio.entities.Usuario;
import br.com.tokio.exceptions.NotFoundException;
import br.com.tokio.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);


	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario buscaPorId(Long id) {
		logger.info("Buscando por id: " + id);
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Erro ao procurar id: " + id));
	}

	public Usuario buscaPorCpf(Long cpf){
		try {
			logger.info("Buscando por cpf: " + cpf);
			List<Usuario> usuarioList = usuarioRepository.buscaPorCpf(cpf);

			if (usuarioList.size() > 1) {
				throw new RuntimeException("Encontrado mais de um usuário com mesmo CPF");
			}

			return usuarioList.get(0);
		} catch (RuntimeException re) {
			logger.error("Erro ao buscar por cpf: " + cpf, re);
			throw new RuntimeException(re);
		} catch (Exception ex){
			logger.error("Erro inesperado ao por cpf: " + cpf);
			throw new RuntimeException("Erro ao buscar por CPF");
		}


	}
	
	

}
