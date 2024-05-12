package com.dong.do_an.model;

import com.dong.do_an.entity.Classroom;
import com.dong.do_an.entity.SemesterPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterUser {
    private String email;
    private String name;
    private List<SemesterPoint> listSemesterPoint;
}
