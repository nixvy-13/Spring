package com.example.quiz.entitys;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	public Usuario() {
		super();
	}

	public Usuario(String nombreUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
	}

	public Usuario(String nombreUsuario, Set<ResultadosUsuarios> resultadosUsuarios) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.resultadosUsuarios = resultadosUsuarios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	private String nombreUsuario;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<ResultadosUsuarios> resultadosUsuarios = new HashSet<ResultadosUsuarios>();

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void addResultadosUsuarios(ResultadosUsuarios resultadosUsuarios) {
		resultadosUsuarios.setUsuario(this);
		this.resultadosUsuarios.add(resultadosUsuarios);
	}

	public void removeResultadosUsuarios(ResultadosUsuarios resultadosUsuarios) {
		this.resultadosUsuarios.remove(resultadosUsuarios);
	}

	public Set<ResultadosUsuarios> getResultadosUsuarios() {
		return resultadosUsuarios;
	}

	public void setResultadosUsuarios(Set<ResultadosUsuarios> resultadosUsuarios) {
		this.resultadosUsuarios = resultadosUsuarios;
	}
	
	
	
}
