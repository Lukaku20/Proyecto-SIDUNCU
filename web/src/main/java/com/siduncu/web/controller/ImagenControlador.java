/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siduncu.web.controller;

import com.siduncu.web.model.Noticia;
import com.siduncu.web.service.ServicioNoticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author siduncuNotebook
 */
@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    ServicioNoticia noticiaService;

    @GetMapping("/noticia/{id}")
    public ResponseEntity<byte[]> imagen(@PathVariable String id) {
        Noticia noti = noticiaService.getOneWithImage(id);

        if (noti != null && noti.getImagen() != null && noti.getImagen().getContenido() != null) {
            byte[] imagen = noti.getImagen().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no hay imagen asociada
        }
    }
}
