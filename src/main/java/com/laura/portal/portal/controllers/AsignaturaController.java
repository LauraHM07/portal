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
import org.springframework.web.servlet.ModelAndView;

import com.laura.portal.portal.model.Asignatura;
import com.laura.portal.portal.services.AsignaturasService;

@Controller
@RequestMapping("/asignaturas")
public class AsignaturaController {
    
    @Autowired
    AsignaturasService asignaturasService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Asignatura> asignaturas = asignaturasService.findAll();

        ModelAndView modelAndView = new ModelAndView("asignaturas/list");
        modelAndView.addObject("asignaturas", asignaturas);

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Asignatura asignatura) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("asignatura", new Asignatura());
        modelAndView.setViewName("asignaturas/new");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Asignatura asignatura) throws IOException {

        Asignatura asignatura2 = asignaturasService.insert(asignatura);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + asignatura2.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) String codigo) {

        Asignatura asignatura = asignaturasService.findByID(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("asignatura", asignatura);
        modelAndView.setViewName("asignaturas/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Asignatura asignatura) {

        asignaturasService.update(asignatura);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + asignatura.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) String codigo) {

        asignaturasService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/asignaturas/list");

        return modelAndView;
    }
}
