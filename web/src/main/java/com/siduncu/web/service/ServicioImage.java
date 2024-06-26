
package com.siduncu.web.service;

import com.siduncu.web.exceptions.MyException;
import com.siduncu.web.model.Image;
import com.siduncu.web.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author siduncuNotebook
 */
@Service
public class ServicioImage {
    
    @Autowired
    private ImageRepository repoImagen;
    
    @Transactional
    public Image guardar(MultipartFile archivo) throws MyException{
        if(archivo != null){
            try {
                Image imagen = new Image();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return repoImagen.save(imagen);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    return null;
    }
    
    @Transactional
    public void borrar(String imageId){
        if(imageId != null){
            Optional<Image> imagen = repoImagen.findById(imageId);
            Image image = imagen.get();
            repoImagen.delete(image);
            System.out.println("Eliminado con exito");
        } 
        
    }
    
    
    
    //falta metodo para actualizar imagen
    
    
}//the end