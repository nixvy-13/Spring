package com.example.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("akatsuki") // Guardar el objeto Akatsuki en la sesión
public class QuizControlador {
	
   @Autowired
    private RepositorioPuntuaciones repositorioPuntuaciones;
    
    @ModelAttribute("akatsuki")
    public Akatsuki initializeAkatsuki() {
        return new Akatsuki();
    }

    @GetMapping("/")
    public String mostrarInicio(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
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
        model.addAttribute("hobbys", akatsuki.hobbys);
        return "hobbies";
    }

    @PostMapping("/hobbies")
    public String guardarHobbies(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("hobbySeleccionado") String hobbySeleccionado, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(hobbySeleccionado);
        return "redirect:/musica";
    }

    @GetMapping("/musica")
    public String mostrarMusica(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        model.addAttribute("akatsuki", akatsuki); // Pasar el objeto Akatsuki
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
        model.addAttribute("comida", akatsuki.comida);
        return "comida";
    }

    @PostMapping("/comida")
    public String guardarComida(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("comidaSeleccionada") String comidaSeleccionada, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(comidaSeleccionada);
        return "redirect:/arte";
    }

    @GetMapping("/arte")
    public String mostrarArte(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        model.addAttribute("arte", akatsuki.arte);
        return "arte";
    }

    @PostMapping("/arte")
    public String guardarArte(@ModelAttribute("akatsuki") Akatsuki akatsuki, @RequestParam("arteSeleccionado") String arteSeleccionado, Model model) {
        Akatsuki.Personajes.incrementarPuntuacionPorNombre(arteSeleccionado);
        return "redirect:/arma";
    }

    @GetMapping("/arma")
    public String mostrarArma(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        model.addAttribute("arma", akatsuki.arma);
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
            // Si sab_pod es true (Poder), sumamos puntos a todos los personajes excepto ITACHI y OROCHIMARU
            Akatsuki.Personajes.TOBI.incrementarPuntuacion();
            Akatsuki.Personajes.DEIDARA.incrementarPuntuacion();
            Akatsuki.Personajes.SASORI.incrementarPuntuacion();
        } else if(!sabPod){
            // Si sab_pod es false (Sabiduría), sumamos puntos a ITACHI y OROCHIMARU
            Akatsuki.Personajes.ITACHI.incrementarPuntuacion();
            Akatsuki.Personajes.OROCHIMARU.incrementarPuntuacion();
        }

        return "redirect:/resultados"; // Redirige a la página de resultados finales
    }

    @GetMapping("/resultados")
    public String mostrarResultados(@ModelAttribute("akatsuki") Akatsuki akatsuki, Model model) {
        Akatsuki.Personajes personajeConMayorPuntuacion = akatsuki.obtenerPersonajeConMayorPuntuacion();

        Puntuacion puntuacion = new Puntuacion(akatsuki.getNombreJugador(), personajeConMayorPuntuacion.name());
        repositorioPuntuaciones.save(puntuacion);

        List<Puntuacion> puntuaciones = (List<Puntuacion>) repositorioPuntuaciones.findAll();
        
        model.addAttribute("personajeConMayorPuntuacion", personajeConMayorPuntuacion);
        model.addAttribute("puntuaciones", puntuaciones);

        return "resultados";
    }


}
