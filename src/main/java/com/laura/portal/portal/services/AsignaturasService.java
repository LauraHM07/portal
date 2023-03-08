package com.laura.portal.portal.services;

import java.util.List;

import com.laura.portal.portal.model.Asignatura;

public interface AsignaturasService {
    
    public List<Asignatura> findAll();

    public Asignatura findByID(String codigo);

    public Asignatura insert(Asignatura asignatura);

    public void update(Asignatura asignatura);

    public void delete(String codigo);
}
