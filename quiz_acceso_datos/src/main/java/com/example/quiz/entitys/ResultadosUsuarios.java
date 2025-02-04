package com.example.quiz.entitys;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resultados_usuarios")
public class ResultadosUsuarios {
	
	public ResultadosUsuarios() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResultado;
	
	private String nombrePasillo;
	
	@ManyToOne
	private Usuario usuario;
	
	private Date fecha;
	
	
	public ResultadosUsuarios(String nombrePasillo, Usuario usuario, Date fecha) {
		super();
		this.nombrePasillo = nombrePasillo;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	public ResultadosUsuarios(String nombrePasillo) {
		this.nombrePasillo = nombrePasillo;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public String getNombrePasillo() {
		return nombrePasillo;
	}

	public void setNombrePasillo(String nombrePasillo) {
		this.nombrePasillo = nombrePasillo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
