package com.example.quiz.controller;

import org.springframework.web.bind.support.SessionStatus;

import com.example.quiz.entitys.ResultadosUsuarios;
import com.example.quiz.entitys.Usuario;
import com.example.quiz.repositorys.RepositoryResultadosUsuarios;
import com.example.quiz.repositorys.RepositoryUsuario;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"pasillos", "nombreUsuario"}) // Guardamos el objeto Pasillos y el nombre en la sesión
public class QuizAccesoDatosController {
	
	@Autowired
	private RepositoryResultadosUsuarios repositoryResultados;
	@Autowired
	private RepositoryUsuario repositoryUsuario;

    @ModelAttribute("pasillos")
    public Pasillos[] inicializarPasillos() {
        Pasillos.reiniciarPuntuaciones(); //Reiniciar puntuaciones a 0
        return Pasillos.values(); //Inicializamos el array con los pasillos
    }

    @ModelAttribute("nombreUsuario")
    public String inicializarNombreUsuario() {
        return ""; //Inicializamos el nombre del usuario vacío
    }

    @GetMapping("/")
    public String mostrarInicio() {
        return "inicio"; //Página de inicio donde se pide el nombre
    }

    @PostMapping("/nombre")
    public String guardarNombre(@RequestParam("nombreUsuario") String nombreUsuario, Model model) {
    	//if(nombreUsuario.findBy)
        model.addAttribute("nombreUsuario", nombreUsuario); //Guardamos el nombre del usuario en la sesión
        return "redirect:/pregunta1";
    }

    @GetMapping("/pregunta1")
    public String mostrarPregunta1(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("nombreUsuario", nombreUsuario);
        return "pregunta1"; //Mostrar la primera pregunta
    }
    @PostMapping("/pregunta1")
    public String procesarPregunta1(@RequestParam("identificado") String respuesta, Model model) {
        Pasillos.incrementarPuntuacionPorNombre(respuesta); //Aumentamos la puntuación del pasillo correspondiente
        return "pregunta2"; //Siguiente pregunta
    }

    @PostMapping("/pregunta2")
    public String respuestaPregunta2(@RequestParam("actividad") String respuesta, Model model) {
        //Incrementar la puntuación del pasillo basado en la respuesta
        Pasillos.incrementarPuntuacionPorNombre(respuesta);
        return "pregunta3";
    }
    @PostMapping("/pregunta3")
    public String respuestaPregunta3(@RequestParam("regalo") String respuesta, Model model) {
        Pasillos.incrementarPuntuacionPorNombre(respuesta);
        return "pregunta4";
    }

    @PostMapping("/pregunta4")
    public String respuestaPregunta4(@RequestParam("recetas") String respuesta, Model model) {
        Pasillos.incrementarPuntuacionPorNombre(respuesta);
        return "pregunta5";
    }

    @PostMapping("/pregunta5")
    public String respuestaPregunta5(@RequestParam("estilo_vida") String respuesta, Model model) {
        Pasillos.incrementarPuntuacionPorNombre(respuesta);
        return "pregunta6"; // O la siguiente ruta
    }


    @PostMapping("/pregunta6")
    public String respuestaPregunta6(@RequestParam("picnic") String respuesta, Model model) {
        Pasillos.incrementarPuntuacionPorNombre(respuesta);
        return "pregunta7";
    }

    @PostMapping("/pregunta7")
    public String respuestaPregunta7(@RequestParam("pasillo_supermercado") String respuesta, Model model) {
        Pasillos.incrementarPuntuacionPorNombre(respuesta);

        //Obtenemos el resultado
        Pasillos pasilloConMayorPuntuacion = Pasillos.obtenerPasilloConMayorPuntuacion();

        //Pasamos el nombre del usuario y el resultado al modelo
        model.addAttribute("resultado", pasilloConMayorPuntuacion);
        model.addAttribute("nombreUsuario", model.getAttribute("nombreUsuario"));
        //Paso la ruta de la foto corresponiente al resultado
        model.addAttribute("imagen", "/images/"+ pasilloConMayorPuntuacion + ".jpg");

        //Creo un usuario por su nombre si no existe, si existe no
        Usuario usuario = comprobarSiExisteUsuario((String)model.getAttribute("nombreUsuario"));
        //Creo una puntuación relacionada con un usuario
        ResultadosUsuarios resultados = new ResultadosUsuarios(pasilloConMayorPuntuacion.getNombrePasillo(), usuario, new Date());
        //Al usuario le añadimos el resultado
        usuario.addResultadosUsuarios(resultados);
        repositoryUsuario.save(usuario);        

        
        Pasillos[] pasillos = Pasillos.values();  // Obtenemos todos los pasillos

        // Imprimir las puntuaciones de cada pasillo en la consola
        System.out.println("Puntuaciones de los pasillos:");
        for (Pasillos pasillo : pasillos) {
            System.out.println(pasillo.name() + ": " + pasillo.getPuntuacion());
        }

        return "result";
    }
    
    public Usuario comprobarSiExisteUsuario(String nombre) {
    	Usuario usuario;
    	if(repositoryUsuario.existsByNombreUsuario(nombre)) {
    		usuario = repositoryUsuario.findByNombreUsuario(nombre);
    	}else {
    		usuario = new Usuario(nombre);
    	}
    	return usuario;
    }

    @RequestMapping("/reiniciar")
    public String reiniciarQuiz(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete(); //Elimina todos los atributos de la sesión

        return "redirect:/"; //Redirigimos de vuelta al inicio
    }
    
    @PostMapping("/ranking")
    public String mostrarRanking(@RequestParam("nombreJugador") String nombreJugador, Model model) {
    	List<ResultadosUsuarios> resultadosRanking1 = repositoryResultados.find5ResultadosUsuariosByNombreUsuario(nombreJugador);
        model.addAttribute("resultadosRanking1", resultadosRanking1);
        
        List<ResultadosUsuarios> resultadosRanking2 = repositoryResultados.findAllResultadosUsuariosByNombrePasillo(Pasillos.obtenerPasilloConMayorPuntuacion().getNombrePasillo());
        model.addAttribute("resultadosRanking1", resultadosRanking2);

        return "ranking";
    }
}