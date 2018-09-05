package com.baidu.mockspring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {

    private String name="unknown";

    private Integer age=10;
   // @JsonIgnore
    private Boolean sex= true;
    @JsonFormat(pattern = "yyyy -> MM^_^dd")
    private LocalDate localDate= LocalDate.now();

 /*   public void testLocalDate(){
        LocalDate l1 = localDate.minusDays(3);
        LocalDate l2 = localDate.plusMonths(2).minusDays(4);
    }*/


}
