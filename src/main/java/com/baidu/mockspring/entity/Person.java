package com.baidu.mockspring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {

    private String name;

    private Integer age;
    @JsonIgnore
    private Boolean sex;
    @JsonFormat(pattern = "yyyy -> MM^_^dd")
    private LocalDate localDate= LocalDate.now();


}
