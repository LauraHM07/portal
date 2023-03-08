package com.laura.portal.portal.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.laura.portal.portal.model.Asignatura;
import com.laura.portal.portal.services.AsignaturasService;

@Service
public class AsignaturasServiceImpl implements AsignaturasService {
    @Value("${url.matriculacion.rest.service}")
    String urlMatriculacion;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Asignatura> findAll() {
        
        Asignatura[] asignatura = restTemplate.getForObject(urlMatriculacion + "asignaturas", Asignatura[].class);
        List<Asignatura> asignaturas = Arrays.asList(asignatura);

        return asignaturas;
    }

    @Override
    public Asignatura findByID(String codigo) {
        Asignatura asignatura = restTemplate.getForObject(urlMatriculacion + "asignaturas/" + codigo, Asignatura.class);

        return asignatura;
    }

    @Override
    public Asignatura insert(Asignatura asignatura) {
        Asignatura asignatura2 = restTemplate.postForObject(urlMatriculacion + "asignaturas", asignatura, Asignatura.class);

        return asignatura2;
    }

    @Override
    public void update(Asignatura asignatura) {
        restTemplate.put(urlMatriculacion + "asignaturas/" + asignatura.getCodigo(), asignatura);
    }

    @Override
    public void delete(String codigo) {
        restTemplate.delete(urlMatriculacion + "asignaturas/" + codigo);
        
    }
}
