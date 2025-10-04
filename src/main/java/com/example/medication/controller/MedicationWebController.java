package com.example.medication.controller;

import com.example.medication.model.Preparation;
import com.example.medication.model.RecSchedule;
import com.example.medication.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/medications")
public class MedicationWebController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationWebController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public String showMedications(Model model) {
        model.addAttribute("todayPreparations", medicationService.getTodayPreparations());
        model.addAttribute("allPreparations", medicationService.getAllPreparations());
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("preparation", new Preparation());
        return "medications";
    }

    @PostMapping
    public String addPreparation(@ModelAttribute Preparation preparation) {
        medicationService.createPreparation(preparation);
        return "redirect:/medications";
    }

    @GetMapping("/{id}")
    public String showPreparationDetails(@PathVariable Long id, Model model) {
        model.addAttribute("preparation", medicationService.getPreparationById(id));
        model.addAttribute("schedule", new RecSchedule());
        return "preparation-details";
    }

    @PostMapping("/{id}/schedules")
    public String addSchedule(@PathVariable Long id, @ModelAttribute RecSchedule schedule) {
        medicationService.addScheduleToPreparation(id, schedule);
        return "redirect:/medications/" + id;
    }

    @PostMapping("/schedules/{scheduleId}/mark-taken")
    public String markScheduleAsTaken(@PathVariable Long scheduleId) {
        medicationService.markScheduleAsTaken(scheduleId);
        return "redirect:/medications";
    }

    @PostMapping("/schedules/{scheduleId}/mark-pending")
    public String markScheduleAsPending(@PathVariable Long scheduleId) {
        medicationService.markScheduleAsPending(scheduleId);
        return "redirect:/medications";
    }

    @PostMapping("/{id}/delete")
    public String deletePreparation(@PathVariable Long id) {
        medicationService.deletePreparation(id);
        return "redirect:/medications";
    }

    @GetMapping("/search")
    public String searchPreparations(@RequestParam String query, Model model) {
        model.addAttribute("searchResults", medicationService.searchPreparations(query));
        model.addAttribute("query", query);
        return "search-results";
    }
}