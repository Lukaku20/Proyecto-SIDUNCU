/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siduncu.web.comparators;

import com.siduncu.web.enums.Categoria;
import com.siduncu.web.model.Noticia;
import java.util.Comparator;

/**
 *
 * @author siduncuNotebook
 */
public class NoticiaComparator implements Comparator<Noticia>{

    @Override
    public int compare(Noticia o1, Noticia o2) {
        //obtener las categorias
        Categoria cat1 = o1.getCategoria();
        Categoria cat2 = o2.getCategoria();
        //compara
        return Integer.compare(cat2.ordinal(), cat1.ordinal());

    }
    
}
