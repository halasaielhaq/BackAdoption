package com.test.demo.Dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Species cannot be empty")
    private String species;

    @PositiveOrZero(message = "Age must be a positive number or zero")
    private int age;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 255, message = "Description must be at most 255 characters long")
    private String description;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @Lob
    private byte[] photo;
}
