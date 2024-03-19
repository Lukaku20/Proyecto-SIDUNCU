
package com.siduncu.web.model;

import com.siduncu.web.enums.Categoria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author siduncuNotebook
 */
@Getter
@Setter
@Entity
public class Noticia {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image imagen;
    private String antetitulo;
    private String titulo;
    private String bajada;
    private String contenido;
    private LocalDate fecha;

    public Noticia() {
       
    }
    
    
    
}//the end
