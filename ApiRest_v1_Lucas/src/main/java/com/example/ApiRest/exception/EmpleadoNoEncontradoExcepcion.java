package com.example.ApiRest.exception;

public class EmpleadoNoEncontradoExcepcion extends RuntimeException {

	public EmpleadoNoEncontradoExcepcion(Long id) {
		super(String.format("No se ha podido encontrar el empleado %d", id));
	}

}
