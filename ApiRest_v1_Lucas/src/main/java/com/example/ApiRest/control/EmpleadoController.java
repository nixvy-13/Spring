package com.example.ApiRest.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRest.model.Empleado;
import com.example.ApiRest.service.EmpleadoService;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {
	private final EmpleadoService empleadoService;

	public EmpleadoController(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return empleadoService.getAll();
	}

	@PostMapping
	public ResponseEntity<?> insertarEmpleado(@RequestBody Empleado nuevoEmpleado) {
		return empleadoService.post(nuevoEmpleado);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> buscarEmpleado(@PathVariable(name = "id") Long id) {
		return empleadoService.get(id);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> reemplazarEmpleado(@RequestBody Empleado nuevoEmpleado,
			@PathVariable(name = "id") Long id) {
		return empleadoService.put(nuevoEmpleado, id);
	}

	@PatchMapping("{id}")
	public ResponseEntity<?> editarEmpleado(@RequestBody Empleado nuevoEmpleado, @PathVariable(name = "id") Long id) {
		return empleadoService.patch(nuevoEmpleado, id);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable(name = "id") Long id) {
		return empleadoService.delete(id);
	}
}
