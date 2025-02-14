package com.example.ApiRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ApiRest.model.Empleado;
import com.example.ApiRest.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
	private final EmpleadoRepository repositorio;

	public EmpleadoService(EmpleadoRepository repositorio) {
		this.repositorio = repositorio;
	}

	public ResponseEntity<?> getAll() {
		List<Empleado> empleados = repositorio.findAll();
		if (empleados.isEmpty())
			return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
		return ResponseEntity.ok(empleados); // 200 OK con la lista de empleados
	}

	public ResponseEntity<?> post(@RequestBody Empleado nuevoEmpleado) {
		Optional<Empleado> existente = repositorio.findByNombreAndRol(nuevoEmpleado.getNombre(),
				nuevoEmpleado.getRol());
		if (existente.isPresent()) {
			// 409 Conflict
			// ResponseEntity.status(HttpStatus.CONFLICT).build();
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el empleado que intentas crear");
		}

		Empleado empleadoGuardado = repositorio.save(nuevoEmpleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado); // 201 Created
	}

	public ResponseEntity<?> get(@PathVariable(name = "id") Long id) {
		/*
		 * El map es una forma elegante de evitar if-else y hacer el código más
		 * funcional.
		 * 
		 * return repositorio.findById(id) .map(ResponseEntity::ok) // Si el empleado
		 * existe, devuelve 200 OK .orElseGet(() -> ResponseEntity.notFound().build());
		 * // Si no existe, devuelve 404 Not Found
		 */

		Optional<Empleado> empleadoOptional = repositorio.findById(id);
		if (empleadoOptional.isPresent())
			return ResponseEntity.ok(empleadoOptional.get()); // 200 OK con el empleado

		// 404 Not Found si el empleado no existe
		// ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado buscado");
	}

	public ResponseEntity<?> put(@RequestBody Empleado nuevoEmpleado, @PathVariable(name = "id") Long id) {
		Optional<Empleado> empleadoOptional = repositorio.findById(id);
		if (empleadoOptional.isPresent()) {
			Empleado empleado = empleadoOptional.get();
			empleado.setNombre(nuevoEmpleado.getNombre());
			empleado.setRol(nuevoEmpleado.getRol());
			Empleado empleadoActualizado = repositorio.save(empleado);
			return ResponseEntity.ok(empleadoActualizado); // 200 OK
		}
		/*
		 * else { nuevoEmpleado.setId(id); // Asigna el ID al nuevo empleado Empleado
		 * empleadoCreado = repositorio.save(nuevoEmpleado); return
		 * ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado); // 201
		 * Created }
		 */

		// 404 Not Found si el empleado no existe
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado que quieres modificar");

		/*
		 * return repositorio.findById(id).map(empleado -> {
		 * empleado.setNombre(nuevoEmpleado.getNombre());
		 * empleado.setRol(nuevoEmpleado.getRol()); return repositorio.save(empleado);
		 * }).orElseGet(() -> { return repositorio.save(nuevoEmpleado); });
		 */
	}

	public ResponseEntity<?> patch(@RequestBody Empleado nuevoEmpleado, @PathVariable(name = "id") Long id) {
		Optional<Empleado> empleadoOptional = repositorio.findById(id);
		if (empleadoOptional.isPresent()) {
			Empleado empleado = empleadoOptional.get();
			// Mirar en casa cómo puedo refactorizarlo para que funcione independietemente
			// del número de atributos (¿Lista?)
			if (nuevoEmpleado.getNombre() != null && !nuevoEmpleado.getNombre().equals(empleado.getNombre()))
				empleado.setNombre(nuevoEmpleado.getNombre());

			if (nuevoEmpleado.getRol() != null && !nuevoEmpleado.getRol().equals(empleado.getRol()))
				empleado.setRol(nuevoEmpleado.getRol());

			Empleado empleadoActualizado = repositorio.save(empleado);
			return ResponseEntity.ok(empleadoActualizado); // 200 OK con el empleado actualizado
		}

		// 404 Not Found si el empleado no existe
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado que quieres modificar");
	}

	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		Optional<Empleado> empleadoOptional = repositorio.findById(id);
		if (empleadoOptional.isPresent()) {
			repositorio.deleteById(id);
			// ResponseEntity.noContent().build();
			return ResponseEntity.status(HttpStatus.OK).body("Empleado eliminado"); // 200 OK
		}

		// 404 Not Found si no se encuentra el empleado
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado que quieres eliminar");
	}
}
