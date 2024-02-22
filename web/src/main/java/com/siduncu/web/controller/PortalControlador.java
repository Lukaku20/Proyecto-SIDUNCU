package com.siduncu.web.controller;

import com.siduncu.web.enums.Categoria;
import com.siduncu.web.model.Noticia;
import com.siduncu.web.service.ServicioNoticia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author siduncuNotebook
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ServicioNoticia notiService;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    
    @GetMapping("/panel")
    public String verPanel() {
        return "panel";
    }

    @GetMapping("/formulario")
    public String entrarFormulario() {
        return "formulario";
    }

    @PostMapping("/crear")
    public String crearNoticia(MultipartFile archivo, @RequestParam String titulo, @RequestParam String antetitulo,
            String bajada, String contenido, Categoria categoria) {
        try {
            notiService.crearNoticia(antetitulo, titulo, bajada, contenido, categoria, archivo);
            return "redirect:/";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "formulario";
        }
    }

    @GetMapping("/noticias")
    public String listarNoticias(ModelMap model) {
        List<Noticia> noticias = notiService.listarNoticias();
        model.addAttribute("noticias", noticias);
        return "noticias";
    }

    @PostMapping("/eliminarNoticia/{id}")
    public String eliminarNoticia(@PathVariable String id) {
        notiService.eliminarNoticia(id);
        return "noticias";
    }

    @GetMapping("/noticia/{id}")
    public String abrirNoticia(@PathVariable String id, ModelMap model) {
        Noticia noticia = notiService.getOneWithImage(id);
        model.addAttribute("noticia", noticia);
        return "desplegar";
    }
}

