/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.contraloderes;

import com.edgar.biblioteca.excepciones.MiException;
import com.edgar.biblioteca.servicio.AutorServicio;
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
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorservicio;
    
    @GetMapping("/registrar") //CUANDO INGRESAMOS A LA URL SE EJECUTA EL BLOQUE DE CODIGO A CONTINUACION
    public String registrar(){
        return "autorformulario.html"; //VISTA
        
    }
    
    @PostMapping("/registro")//VIENE DEL ARCHIVO HTML EN EL FORMULARIO
    
    public String registro(@RequestParam String nombre, ModelMap modelo) throws MiException{ //Recibe el nombre del atributo name del input del formulario e indica que es requerido
        try {
            autorservicio.crearAutor(nombre);
            modelo.put("exito", "El autor se ingreso correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autorformulario.html";
        }
        
        return "index.html";
    }
    
}
