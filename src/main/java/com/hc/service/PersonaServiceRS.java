/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hc.service;

import com.hc.data.PersonaDao;
import com.hc.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author rodol
 */
@Stateless
@Path("/personas")
public class PersonaServiceRS {
    
    @Inject
    private PersonaDao personaDao;
    
    @GET
    @Produces (value = MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas(){
        List<Persona> personas = personaDao.encontrarPersonas();
        System.out.println("Personas encontradas: " +personas);
        return personas;
    }
    
    @GET
    @Produces (value = MediaType.APPLICATION_JSON)
    @Path("{id}") // hace referencia al path personas/id
    public Persona listarPersona(@PathParam("id") int id){
        Persona persona = personaDao.encontrarPersona(new Persona(id));
        System.out.println("Persona encontrada: " +persona);
        return persona;
    }
    
    @POST
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces (value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona){
        personaDao.insertarPersona(persona);
        System.out.println("Persona agregada: " +persona);
        return persona;
    }
    
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces (value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        Persona persona = personaDao.encontrarPersona(new Persona(id));
        
        if (persona != null){
            personaDao.actualizarPersona(personaModificada);
            System.out.println("Persona modificada: " +personaModificada);
            return Response.ok().entity(personaModificada).build();
        }
        else { 
            return Response.status(Response.Status.NOT_FOUND).build();
        }           
    } 
    
    @DELETE
    @Produces (value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response borrarPersona(@PathParam("id") int id){    
        personaDao.eliminarPersona(new Persona(id));
        System.out.println("Persona elimada con el id: " +id);
        return Response.ok().build();        
    }      
}
