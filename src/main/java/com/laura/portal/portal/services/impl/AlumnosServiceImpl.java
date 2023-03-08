package com.laura.portal.portal.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.laura.portal.portal.model.Alumno;
import com.laura.portal.portal.services.AlumnosService;

@Service
public class AlumnosServiceImpl implements AlumnosService {

    @Value("${url.matriculacion.rest.service}")
    String urlMatriculacion;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Alumno> findAll() {
        
        Alumno[] alumno = restTemplate.getForObject(urlMatriculacion + "alumnos", Alumno[].class);
        List<Alumno> alumnos = Arrays.asList(alumno);

        return alumnos;
    }

    @Override
    public Alumno findByID(int codigo) {
        Alumno alumno = restTemplate.getForObject(urlMatriculacion + "alumnos/" + codigo, Alumno.class);

        return alumno;
    }

    @Override
    public Alumno insert(Alumno alumno) {
        Alumno alumno2 = restTemplate.postForObject(urlMatriculacion + "alumnos", alumno, Alumno.class);

        return alumno2;
    }

    @Override
    public void update(Alumno alumno) {
        restTemplate.put(urlMatriculacion + "alumnos/" + alumno.getCodigo(), alumno);
    }

    @Override
    public void delete(int codigo) {
        restTemplate.delete(urlMatriculacion + "alumnos/" + codigo);
        
    }
}
