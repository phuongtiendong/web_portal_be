package com.dong.do_an.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
    private Integer classroomId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    private List<SubjectDTO> listSubject;
}
