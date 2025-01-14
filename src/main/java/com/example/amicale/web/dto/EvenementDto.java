package com.example.amicale.web.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EvenementDto {

    private Long id;
    private String title ;
    private String Lieu;
    private String Description;
    private LocalDate datePublication;
}
