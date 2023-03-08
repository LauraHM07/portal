package com.laura.portal.portal.services;

import java.util.List;

import com.laura.portal.portal.model.Alumno;

public interface AlumnosService {
    
    public List<Alumno> findAll();

    public Alumno findByID(int codigo);

    public Alumno insert(Alumno alumno);

    public void update(Alumno alumno);

    public void delete(int codigo);
}
