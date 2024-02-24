package com.siduncu.web.repositories;

import com.siduncu.web.model.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author siduncuNotebook
 */
public interface NoticiaRepository extends JpaRepository<Noticia, String> {

    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo ")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
    // Otra lógica del repositorio...

    @Query("SELECT n FROM Noticia n LEFT JOIN FETCH n.imagen")
    List<Noticia> findAllWithImages();

    @Query(value = "SELECT * FROM noticia LIMIT 2", nativeQuery = true)
    List<Noticia> findTop2();
    @Query (value = "SELECT * FROM noticia LIMIT 1", nativeQuery = true)
    Noticia findTop();

}//the end
