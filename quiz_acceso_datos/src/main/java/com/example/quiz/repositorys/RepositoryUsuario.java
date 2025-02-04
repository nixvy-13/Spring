package com.example.quiz.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entitys.Usuario;

@Repository
public interface RepositoryUsuario extends CrudRepository<Usuario, Long>{
	boolean existsByNombreUsuario(String nombreUsuario);
	Usuario findByNombreUsuario(String nombreUsuario);
}
