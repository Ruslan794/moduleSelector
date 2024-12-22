package org.rr.moduleselector.survey.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class SurveyData {
    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name = "";

    @NotBlank(message = "Surname is required")
    @Size(min = 2, message = "Surname must be at least 2 characters")
    private String surname = "";

    @NotNull(message = "Birth date is required")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String matriculation = "";

    @NotBlank(message = "Country is required")
    private String country = "";

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email = "";
}