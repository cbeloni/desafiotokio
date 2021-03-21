package br.com.tokio.repository;

import br.com.tokio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.cpf = :cpf")
    List<Usuario> buscaPorCpf(Long cpf);

}
