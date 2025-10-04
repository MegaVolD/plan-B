package com.example.medication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "rec_schedules")
public class RecSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Дозировка обязательна")
    @Column(nullable = false)
    private String dosage;
    
    @NotNull(message = "Время приёма обязательно")
    @Column(nullable = false)
    private LocalTime time;
    
    @Column(name = "is_taken")
    private boolean taken = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preparation_id", nullable = false)
    private Preparation preparation;
    
    public RecSchedule() {}
    
    public RecSchedule(String dosage, LocalTime time, Preparation preparation) {
        this.dosage = dosage;
        this.time = time;
        this.preparation = preparation;
    }
    
    // Геттеры
    public Long getId() { return id; }
    public String getDosage() { return dosage; }
    public LocalTime getTime() { return time; }
    public boolean isTaken() { return taken; }
    public Preparation getPreparation() { return preparation; }
    
    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setTaken(boolean taken) { this.taken = taken; }
    public void setPreparation(Preparation preparation) { this.preparation = preparation; }
}