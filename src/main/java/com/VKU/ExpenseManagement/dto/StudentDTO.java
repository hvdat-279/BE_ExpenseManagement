package com.VKU.ExpenseManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Min(value = 0, message = "Tuổi không được âm")
    private String age;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
}

