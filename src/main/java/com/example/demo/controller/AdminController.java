package com.example.demo.controller;

import com.example.demo.service.DashboardService;
import com.example.demo.service.EducationService;
import com.example.demo.service.DashboardStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class AdminController {

    private final DashboardService dashboardService;
    private final EducationService educationService;

    public AdminController(DashboardService dashboardService,
                           EducationService educationService) {
        this.dashboardService = dashboardService;
        this.educationService = educationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        DashboardStats stats = dashboardService.getDashboardStats();
        model.addAttribute("stats", stats);

        Map<String, Long> eduMap =
                stats.getEducationLevelCounts() != null
                        ? stats.getEducationLevelCounts()
                        : Collections.emptyMap();

        model.addAttribute("educationLevels", eduMap.keySet());
        model.addAttribute("educationCounts", eduMap.values());

        return "dashboard";
    }

    @GetMapping("/education-summary")
    public String educationSummary(Model model) {
        model.addAttribute("stats", dashboardService.getDashboardStats());
        return "education-summary";
    }

    @GetMapping("/education-details")
    public String educationDetails(Model model) {

        model.addAttribute("educationMap", educationService.educationLevelCount());
        model.addAttribute("schoolChildren", educationService.getSchoolChildren());
        model.addAttribute("dropouts", educationService.getDropouts());

        return "education-details";
    }
}
