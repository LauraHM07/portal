package com.laura.portal.portal.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laura.portal.portal.model.Alumno;
import com.laura.portal.portal.model.Asignatura;
import com.laura.portal.portal.services.AlumnosService;
import com.laura.portal.portal.services.AsignaturasService;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
    
    @Autowired
    AlumnosService alumnosService;

    @Autowired
    AsignaturasService asignaturasService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Alumno> alumnos = alumnosService.findAll();

        ModelAndView modelAndView = new ModelAndView("alumnos/list");
        modelAndView.addObject("alumnos", alumnos);

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Alumno alumno) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alumno", new Alumno());
        modelAndView.setViewName("alumnos/new");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Alumno alumno) throws IOException {

        alumnosService.insert(alumno);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + alumno.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Alumno alumno = alumnosService.findByID(codigo);
        List<Asignatura> asignaturas = asignaturasService.findAll();
        List <Asignatura> asignaturasAlumno = alumno.getAsignaturas();

        for(Asignatura asignatura : asignaturas) {
            for(Asignatura asigAlu : asignaturasAlumno) {
                if(asignatura.getCodigo() == asigAlu.getCodigo()) {
                    asignatura.setAlumno(true);

                    break;
                } else {
                    asignatura.setAlumno(false);
                }
            }
        }

        alumno.setAsignaturas(asignaturasAlumno);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alumno", alumno);
        modelAndView.addObject("asignaturas", asignaturas);
        modelAndView.setViewName("alumnos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Alumno alumno, @RequestParam("alu") String[] checkboxValue) {

        alumnosService.findByID(alumno.getCodigo());

        List<Asignatura> asignaturas = asignaturasService.findAll();
        List<Asignatura> asignaturasAlu = new ArrayList<Asignatura>();

        for (Asignatura asignatura : asignaturas){
            for (int i = 0; i < checkboxValue.length; i++){
                String valor = checkboxValue[i];

                if (asignatura.getCodigo() == valor){
                    asignaturasAlu.add(asignatura);
                    
                    break;
                }
            }
        }

        alumno.setAsignaturas(asignaturasAlu);
        alumnosService.update(alumno);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + alumno.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        alumnosService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/alumnos/list");

        return modelAndView;
    }
}
