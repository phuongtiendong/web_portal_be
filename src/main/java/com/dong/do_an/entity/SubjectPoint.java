package com.dong.do_an.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPoint {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @Embedded
    @JsonIgnore
    private SemesterPointId semesterPointId;

    @Column(nullable = false)
    private String subjectName;

    private Integer point;
}
