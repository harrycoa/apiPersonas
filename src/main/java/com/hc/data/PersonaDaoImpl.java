/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hc.data;

import com.hc.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author rodol
 */
@Stateless
public class PersonaDaoImpl implements PersonaDao {

    @PersistenceContext (unitName = "PersonaPU")    
    EntityManager em;
    
    
    @Override
    public List<Persona> encontrarPersonas() {
        return em.createNamedQuery("Persona.encontrarPersonas").getResultList();
    }

    @Override
    public Persona encontrarPersona(Persona persona) {
        return em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public void insertarPersona(Persona persona) {
        em.persist(persona);
        em.flush(); // retornar llave primaria
    }

    @Override
    public void actualizarPersona(Persona persona) {
        em.merge(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
        em.remove(em.merge(persona));
    }
    
}
