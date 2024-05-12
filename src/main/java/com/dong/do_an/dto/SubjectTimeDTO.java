package com.dong.do_an.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTimeDTO {
    private Integer type;

    private String teacherName;

    private String className;

    private Integer week;

    private Integer weekday;

    private Integer period;
}
