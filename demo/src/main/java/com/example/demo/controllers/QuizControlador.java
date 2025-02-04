package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entities.Akatsuki;
import com.example.demo.entities.Jugador;
import com.example.demo.entities.Puntuacion;
import com.example.demo.repositories.RepositorioPuntuaciones;
import com.example.demo.repositories.RepositoryJugador;

@Controller
@SessionAttributes("akatsuki")
public class QuizControlador {
	
    @Autowired
    private RepositorioPuntuaciones repositorioPuntuaciones;
    @Autowired
    private RepositoryJugador repositorioJugador;
    
    @ModelAttribute("akatsuki")
    public Akatsuki initializeAkatsuki() {
        return new Akatsuki();
    }

    @GetMapping("/")
    public String mostrarInicio(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
    	akatsuki.reset();
        model.addAttribute("akatsuki", akatsuki);
        return "inicio"; 
    }
    @PostMapping("/inicio")
    public String guardarNombre(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("nombre_jugador") String nombre_jugador, Model model) {
    	akatsuki.setNombreJugador(nombre_jugador);
        return "redirect:/hobbies";
    }

    @GetMapping("/hobbies")
    public String mostrarHobbies(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        return "hobbies";
    }

    @PostMapping("/hobbies")
    public String guardarHobbies(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("hobbySeleccionado") String hobbySeleccionado, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(hobbySeleccionado);
        return "redirect:/musica";
    }

    @GetMapping("/musica")
    public String mostrarMusica(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        model.addAttribute("akatsuki", akatsuki);
        return "musica";
    }

    @PostMapping("/musica")
    public String procesarMusica(@ModelAttribute("akatsuki") Akatsuki akatsuki, 
                                  @RequestParam(value = "musicaSeleccionada", required = false) String[] musicaSeleccionada, 
                                  Model model) {
        if (musicaSeleccionada != null) {
            for (String musica : musicaSeleccionada) {
                Akatsuki.Personajes.incrementarPuntuacionPorNombre(musica);
            }
        }
        return "redirect:/comida";
    }
    @GetMapping("/comida")
    public String mostrarComida(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        return "comida";
    }

    @PostMapping("/comida")
    public String guardarComida(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("comidaSeleccionada") String comidaSeleccionada, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(comidaSeleccionada);
        return "redirect:/arte";
    }

    @GetMapping("/arte")
    public String mostrarArte(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        return "arte";
    }

    @PostMapping("/arte")
    public String guardarArte(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("arteSeleccionado") String arteSeleccionado, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(arteSeleccionado);
        return "redirect:/arma";
    }

    @GetMapping("/arma")
    public String mostrarArma(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        return "arma";
    }

    @PostMapping("/arma")
    public String guardarArma(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("armaSeleccionada") String armaSeleccionada, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(armaSeleccionada);
        return "redirect:/sabPod";
    }

    @GetMapping("/sabPod")
    public String mostrarSabPod(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        return "sabPod";
    }
    @PostMapping("/sabPod")
    public String guardarSabPod(@ModelAttribute("akatsuki") Akatsuki akatsuki, 
                                 @RequestParam("sabPod") boolean sabPod, 
                                 Model model) {
        akatsuki.sab_pod = sabPod;

        if (sabPod) {
            Akatsuki.Personajes.TOBI.incrementarPuntuacion();
            Akatsuki.Personajes.DEIDARA.incrementarPuntuacion();
            Akatsuki.Personajes.SASORI.incrementarPuntuacion();
        } else if(!sabPod){
            Akatsuki.Personajes.ITACHI.incrementarPuntuacion();
            Akatsuki.Personajes.OROCHIMARU.incrementarPuntuacion();
        }

        return "redirect:/resultados";
    }

    @GetMapping("/resultados")
    public String mostrarResultados(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        Akatsuki.Personajes personajeConMayorPuntuacion = akatsuki.obtenerPersonajeConMayorPuntuacion();
        LocalDateTime fecha = LocalDateTime.now();

        List<Jugador> jugadoresExistentes = repositorioJugador.findByNombre(akatsuki.getNombreJugador());
        Jugador jugador;
        if (!jugadoresExistentes.isEmpty()) {
            jugador = jugadoresExistentes.get(0);
        } else {
            jugador = new Jugador(akatsuki.getNombreJugador());
        }

        Puntuacion puntuacion = new Puntuacion(personajeConMayorPuntuacion.name());
        puntuacion.setFecha(fecha);
        jugador.addPuntuacion(puntuacion);
        puntuacion.setJugador(jugador);

        repositorioJugador.save(jugador);

        List<Puntuacion> puntuaciones = (List<Puntuacion>) repositorioPuntuaciones.findAll();
        
        model.addAttribute("personajeConMayorPuntuacion", personajeConMayorPuntuacion);
        model.addAttribute("puntuaciones", puntuaciones);

        return "resultados";
    }
    
    @PostMapping("/resultados")
    public String procesarRanking(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("nombre_ranking") String nombre_ranking, Model model) {
    	akatsuki.setNombreRanking(nombre_ranking);
    	return "redirect:/ranking";
    }
    
    @GetMapping("/ranking")
    public String mostrarRanking(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
    	
    	String nombre_ranking = akatsuki.nombre_ranking;
    	List<Puntuacion> punt_ranking = (List<Puntuacion>)repositorioPuntuaciones.findByJugadorNombreOrderByFechaDesc(nombre_ranking);
    			
    	model.addAttribute("punt_ranking", punt_ranking);
    	model.addAttribute("nombre_ranking", nombre_ranking);
    	return "ranking";
    }
    
}
