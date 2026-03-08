package com.example.demo.controller;

import com.example.demo.entity.Family;
import com.example.demo.service.FamilyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/list")
    public String familyList(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "bplApl", required = false) String bplApl,
                             Model model) {

        List<Family> families = familyService.searchFamilies(name, bplApl);
        model.addAttribute("families", families);
        model.addAttribute("name", name);
        model.addAttribute("bplApl", bplApl);

        return "family-list";
    }

    @GetMapping("/details/{id}")
    public String familyDetails(@PathVariable("id") Long id, Model model) {
        Family family = familyService.getFamilyById(id);
        model.addAttribute("family", family);
        return "family-details";
    }

    @GetMapping("/add")
    public String addFamilyForm(Model model) {
        model.addAttribute("family", new Family());
        return "family-form";
    }

    @GetMapping("/edit/{id}")
    public String editFamilyForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("family", familyService.getFamilyById(id));
        return "family-form";
    }

    @PostMapping("/save")
    public String saveFamily(@ModelAttribute Family family) {
        familyService.saveFamily(family);
        return "redirect:/family/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteFamily(@PathVariable("id") Long id) {
        familyService.deleteFamily(id);
        return "redirect:/family/list";
    }
}
