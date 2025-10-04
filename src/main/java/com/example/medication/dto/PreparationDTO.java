package com.example.medication.dto;

import com.example.medication.model.Preparation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PreparationDTO {
    private Long id;
    
    @NotBlank(message = "Название лекарства обязательно")
    private String name;
    
    private String description;
    
    @NotNull(message = "Дата начала обязательна")
    private LocalDate startDate;
    
    @NotNull(message = "Дата окончания обязательна")
    private LocalDate endDate;
    
    private List<RecScheduleDTO> schedules = new ArrayList<>();
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<RecScheduleDTO> getSchedules() { return schedules; }
    
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setSchedules(List<RecScheduleDTO> schedules) { this.schedules = schedules; }
    
    public static PreparationDTO fromEntity(Preparation preparation) {
        PreparationDTO dto = new PreparationDTO();
        dto.setId(preparation.getId());
        dto.setName(preparation.getName());
        dto.setDescription(preparation.getDescription());
        dto.setStartDate(preparation.getStartDate());
        dto.setEndDate(preparation.getEndDate());
        return dto;
    }
    
    public Preparation toEntity() {
        Preparation preparation = new Preparation();
        preparation.setName(this.name);
        preparation.setDescription(this.description);
        preparation.setStartDate(this.startDate);
        preparation.setEndDate(this.endDate);
        return preparation;
    }
}