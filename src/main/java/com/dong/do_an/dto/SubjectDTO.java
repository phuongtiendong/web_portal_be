package com.dong.do_an.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private String name;

    private List<SubjectTimeDTO> listSubjectTime;
}
