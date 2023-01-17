/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.repositorios;

import com.edgar.biblioteca.entidad.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author EANDRADA
 */
@Repository //MARCO QUE ES UN REPOSITORIO
public interface LibroRepositorio extends JpaRepository<Libro, Long>{ //INDICAMOS QUE MANEJA ENTIDAD  LIBRO CON ID LONG EXTENDIENDO DE JPAREPOSITORY
    
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo") //COLOCAMOS LA QUERY QUE NECESITAMOS
    public Libro buscarPorTitulo(@Param("titulo")String titulo); // EL PARAM SE REFIERE A LA COLUMNA TITULO MIENTRAS QUE EL STRING TITULO REFIERE AL :
    
    
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre= :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);
}
