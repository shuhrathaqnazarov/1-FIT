package com.example.onefit.location.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateDto {

    @NotBlank
    private String name;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;
}
