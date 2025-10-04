package com.example.medication.dto;

import com.example.medication.model.RecSchedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public class RecScheduleDTO {
    private Long id;
    
    @NotBlank(message = "Дозировка обязательна")
    private String dosage;
    
    @NotNull(message = "Время приёма обязательно")
    private LocalTime time;
    
    private boolean taken;
    
    private Long preparationId;
    
    public Long getId() { return id; }
    public String getDosage() { return dosage; }
    public LocalTime getTime() { return time; }
    public boolean isTaken() { return taken; }
    public Long getPreparationId() { return preparationId; }
    
    public void setId(Long id) { this.id = id; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setTaken(boolean taken) { this.taken = taken; }
    public void setPreparationId(Long preparationId) { this.preparationId = preparationId; }
    
    public static RecScheduleDTO fromEntity(RecSchedule schedule) {
        RecScheduleDTO dto = new RecScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDosage(schedule.getDosage());
        dto.setTime(schedule.getTime());
        dto.setTaken(schedule.isTaken());
        
        if (schedule.getPreparation() != null) {
            dto.setPreparationId(schedule.getPreparation().getId());
        }
        
        return dto;
    }
    
    public RecSchedule toEntity() {
        RecSchedule schedule = new RecSchedule();
        schedule.setDosage(this.dosage);
        schedule.setTime(this.time);
        schedule.setTaken(this.taken);
        return schedule;
    }
}