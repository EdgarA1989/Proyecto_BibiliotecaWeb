/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.contraloderes;

import com.edgar.biblioteca.entidad.Autor;
import com.edgar.biblioteca.entidad.Editorial;
import com.edgar.biblioteca.excepciones.MiException;
import com.edgar.biblioteca.servicio.AutorServicio;
import com.edgar.biblioteca.servicio.EditorialServicio;
import com.edgar.biblioteca.servicio.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author EANDRADA
 */
@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private AutorServicio autorservicio;
    @Autowired
    private EditorialServicio editorialservicio;
    @Autowired
    private LibroServicio libroservicio;
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorservicio.listarAutores();
        List<Editorial> editoriales = editorialservicio.listarEditoriales();
        
        modelo.addAttribute("autores", autores); //COLOCO NOMBRE DE LLAVE Y LE DOY EL VALOR DEL LISTADO DE AUTORES
        modelo.addAttribute("editoriales", editoriales);
        
        autorservicio.listarAutores();
        return "libroformulario.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) Long ISBN,@RequestParam String titulo,
            @RequestParam(required=false) Integer ejemplares,@RequestParam Integer idAutor,@RequestParam Integer idEditorial
    , ModelMap modelo) throws MiException{ //UTILIZO PARA MOSTRAR EN LA VISTA LOS ERRORES
        try {
           libroservicio.crearLibro(ISBN, titulo, ejemplares, idAutor, idEditorial);
           modelo.put("exito", "El libro fue cargado corretamente"); //INYECTO UN MENSAJE DE ERROR GRACIAS A AL METODO PUT DE MODELMAP
           
           
        } catch (MiException ex) {
            System.out.println("Problema en el ingreso de datos");
            modelo.put("error", "ERROR AL INGRESAR EL LIBRO"); //muestor de la variable ex el mensaje de error de mi excepcion
            return "libroformulario.html";
        }
        return "index.html";
    }
    
       
}
