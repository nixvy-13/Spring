package com.example.quiz.repositorys;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz.entitys.ResultadosUsuarios;

@Repository
public interface RepositoryResultadosUsuarios extends CrudRepository<ResultadosUsuarios, Long>{
	
	//Encuentra 5 resultados ordenados de manera ascendente del mismo nombre de usuario
    @Query(value = "select * from resultados_usuarios r join usuario u on r.usuario_id_usuario = u.id_usuario where "
    		+ "u.nombre_usuario = :nombreUsuario order by r.nombre_pasillo asc limit 5", nativeQuery = true)    
    List<ResultadosUsuarios> find5ResultadosUsuariosByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

    @Query(value = "select * from resultados_usuarios where nombre_pasillo = :nombrePasillo order by usuario_id_usuario", nativeQuery = true)
    List<ResultadosUsuarios> findAllResultadosUsuariosByNombrePasillo(@Param("nombrePasillo")String nombrePasillo);
}
