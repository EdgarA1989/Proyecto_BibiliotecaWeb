/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.contraloderes;

import com.edgar.biblioteca.excepciones.MiException;
import com.edgar.biblioteca.servicio.EditorialServicio;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialservicio;
     
    @GetMapping("/registrar")
    public String registrar(){
    return "editorialformulario.html";
}
    
    @PostMapping("/registro")
      public String registro(@RequestParam String nombre, ModelMap modelo) throws MiException{ //Recibe el nombre del atributo name del input del formulario e indica que es requerido
        try {
          editorialservicio.crearEditorial(nombre);
          modelo.put("exito", "La Editorial fue ingresada!!!!");
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());
            return "editorialformulario.html";
            
        }
        
        return "index.html";
    }
    
}
