package com.laura.portal.portal.controllers;

import java.io.IOException;
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
            @PathVariable(name = "codigo", required = true) int codigo, @RequestParam("alu") String[] asignatura) {

        Alumno alumno = alumnosService.findByID(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alumno", alumno);
        modelAndView.setViewName("alumnos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Alumno alumno, @RequestParam("alu") String[] checkboxValue) {

        alumnosService.findByID(alumno.getCodigo());

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
