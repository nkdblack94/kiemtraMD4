package com.controller;

import com.model.City;
import com.model.Nation;
import com.repository.NationRepository;
import com.sevice.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    NationRepository nationRepository;

    @ModelAttribute(name = "nation")
    public Page<Nation> nations(Pageable pageable) {
        return nationRepository.findAll(pageable);
    }

    @GetMapping("/")
    public ModelAndView showCityManager(@RequestParam(required = false) Integer page) {
        int size = 5;
        Pageable pageCity = null;
        if (page == null || page < 1) {
            pageCity = PageRequest.of(0, size, Sort.by("id").ascending());
        } else {
            --page;
            pageCity = PageRequest.of(page, size, Sort.by("id").ascending());
        }
        Page<City> cities = cityService.findAll(pageCity);
        ModelAndView modelAndView = new ModelAndView("/city/city-manager");
        modelAndView.addObject("citys", cities);
        return modelAndView;
    }

    @GetMapping("create-city")
    public ModelAndView showCreateCity(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("city/create-city");
        Page<Nation> nations = nationRepository.findAll(pageable);
        modelAndView.addObject("city", new City());
        modelAndView.addObject("nations", nations);
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView createCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("city/create-city");
            modelAndView.addObject("nations", nationRepository.findAll());
            return modelAndView;
        } else {
            cityService.save(city);
            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("edit-city/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Optional<City> cities = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/edit-city");
        modelAndView.addObject("city", cities.get());
        return modelAndView;
    }

    @PostMapping("edit-city")
    public ModelAndView editCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("city/edit-city");
            modelAndView.addObject("nation", nationRepository.findAll());
            return modelAndView;
        } else {
            cityService.save(city);
            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("delete-city/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/delete-city");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("delete-city")
    public String deleteCity(@ModelAttribute("city") City city) {
        cityService.delete(city);
        return "redirect:/";
    }

    @GetMapping("view-city/{id}")
    public ModelAndView viewCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/view");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
}
