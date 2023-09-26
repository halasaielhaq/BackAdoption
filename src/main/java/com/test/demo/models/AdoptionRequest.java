package com.test.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "AdoptionRequest")
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String petName;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String age;
    private String reasonsForAdoption;
    private String allergies;
    private int numberOfPets;


    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    private String status;

    public AdoptionRequest(Animal animal, String status) {
        this.animal = animal;
        this.status = status;
    }


}
