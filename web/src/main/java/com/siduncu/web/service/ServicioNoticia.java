package com.siduncu.web.service;

import com.siduncu.web.comparators.NoticiaComparator;
import com.siduncu.web.enums.Categoria;
import com.siduncu.web.exceptions.MyException;
import com.siduncu.web.model.Image;
import com.siduncu.web.model.Noticia;
import com.siduncu.web.repositories.NoticiaRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author siduncuNotebook
 */
@Service
public class ServicioNoticia {

    @Autowired
    private NoticiaRepository repoNoti;

    @Autowired
    private ServicioImage servicioImagen;

    public Noticia getOne(String id) {
        return repoNoti.getOne(id);
    }

    public void validar(String antetitulo, String titulo, String bajada, String contenido, Categoria categoria) throws MyException {

        if (antetitulo.isEmpty() || antetitulo == null) {
            throw new MyException("La epigrafe no puede quedar nulo.");
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MyException("El titulo no puede quedar vacio.");
        }
        if (bajada.isEmpty() || bajada == null) {
            throw new MyException("La noticia debe tener una bajada o entrada.");
        }
        if (contenido.isEmpty() || contenido == null) {
            throw new MyException("El contenido no debe quedar vacio.");
        }
        if (categoria == null || categoria.equals(" ")) {
            throw new MyException("Debe seleccionar una categoria obligatoriamente.");
        }

    }

    @Transactional
    public void crearNoticia(String antetitulo, String titulo, String bajada, String contenido, Categoria categoria, MultipartFile archivo) throws MyException {

        validar(antetitulo, titulo, bajada, contenido, categoria);
        Noticia nueva = new Noticia();
        nueva.setBajada(bajada);
        nueva.setAntetitulo(antetitulo);
        nueva.setCategoria(categoria);
        nueva.setContenido(contenido);
        nueva.setTitulo(titulo);
        Image imagen = servicioImagen.guardar(archivo);
        nueva.setImagen(imagen);
        repoNoti.save(nueva);

    }

    @Transactional
    public void eliminarNoticia(String id) {
        Optional<Noticia> respuesta = repoNoti.findById(id);
        Noticia noticia = respuesta.get();
        if (respuesta.isPresent()) {
            servicioImagen.borrar(noticia.getImagen().getId());
            repoNoti.delete(noticia);
            System.out.println("Fue eliminada con éxito");
        }
    }

    public List<Noticia> listarNoticias() {
        List<Noticia> noticias = new ArrayList<>();
        noticias = repoNoti.findAll();
        return noticias;
    }

    @Transactional
    public void actualizarNoticia() {

    }

    public List<Noticia> ordenarNoticias() {
        List<Noticia> noticias = repoNoti.findAll();
        noticias.sort(new NoticiaComparator());
        return noticias;
    }
    // En tu servicio de noticias

    public List<Noticia> obtenerNoticiasConImagenes() {
        // Aquí, carga las noticias con imágenes utilizando un método personalizado de tu repositorio
        return repoNoti.findAllWithImages();
    }
    //noticia con imagen
    public List<Noticia> obtenetTop(){
        //Metodo para llamar repo funcion top
        return repoNoti.findTop2();
    }
    public Noticia getOneWithImage(String id) {
        Noticia noticia = repoNoti.findById(id).orElse(null);
        if (noticia != null) {
            // Forzar la carga de la imagen
            noticia.getImagen().getId(); // O cualquier otra propiedad de la imagen que necesites
        }
        return noticia;
    }
    public Noticia enviarUltimaNoticia() {
        Noticia noticia = repoNoti.findTop();
        return noticia;
    }
//    public Noticia enviarReciente(){
//        List<Noticia> noticias = repoNoti.findAllWithImages();
//        for (Noticia noticia : noticias) {
//            
//            
//        }
//    }
}
