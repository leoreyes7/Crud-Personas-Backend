package com.pr7.crudpersonas.service;

import com.pr7.crudpersonas.entity.Persona;
import com.pr7.crudpersonas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> findAll(){
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona save(Persona persona){
        return personaRepository.save(persona);
    }

    public void delete(Persona persona) {
        personaRepository.delete(persona);
    }

    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }
}
