package com.example.medication.controller;

import com.example.medication.dto.PreparationDTO;
import com.example.medication.dto.RecScheduleDTO;
import com.example.medication.model.Preparation;
import com.example.medication.model.RecSchedule;
import com.example.medication.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medications")
@CrossOrigin(origins = "*")
public class MedicationRestController {
    
    private final MedicationService medicationService;
    
    @Autowired
    public MedicationRestController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }
    
    @GetMapping
    public ResponseEntity<List<PreparationDTO>> getAllPreparations() {
        List<PreparationDTO> preparations = medicationService.getAllPreparations().stream()
                .map(PreparationDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(preparations);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PreparationDTO> getPreparationById(@PathVariable Long id) {
        Preparation preparation = medicationService.getPreparationById(id);
        return ResponseEntity.ok(PreparationDTO.fromEntity(preparation));
    }
    
    @PostMapping
    public ResponseEntity<PreparationDTO> createPreparation(@Valid @RequestBody PreparationDTO preparationDTO) {
        Preparation preparation = preparationDTO.toEntity();
        Preparation saved = medicationService.createPreparation(preparation);
        return ResponseEntity.ok(PreparationDTO.fromEntity(saved));
    }
    
    @GetMapping("/today")
    public ResponseEntity<List<PreparationDTO>> getTodayPreparations() {
        List<PreparationDTO> preparations = medicationService.getTodayPreparations().stream()
                .map(PreparationDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(preparations);
    }
    
    @PostMapping("/{id}/schedules")
    public ResponseEntity<RecScheduleDTO> addSchedule(
            @PathVariable Long id, 
            @Valid @RequestBody RecScheduleDTO scheduleDTO) {
        
        RecSchedule schedule = scheduleDTO.toEntity();
        RecSchedule saved = medicationService.addScheduleToPreparation(id, schedule);
        return ResponseEntity.ok(RecScheduleDTO.fromEntity(saved));
    }
    
    @PostMapping("/schedules/{scheduleId}/mark-taken")
    public ResponseEntity<RecScheduleDTO> markScheduleAsTaken(@PathVariable Long scheduleId) {
        RecSchedule schedule = medicationService.markScheduleAsTaken(scheduleId);
        return ResponseEntity.ok(RecScheduleDTO.fromEntity(schedule));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PreparationDTO>> searchPreparations(@RequestParam String query) {
        List<PreparationDTO> preparations = medicationService.searchPreparations(query).stream()
                .map(PreparationDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(preparations);
    }
}