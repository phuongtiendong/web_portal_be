package com.dong.do_an.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTime {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer subjectId;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private String teacherName;

    @Column(nullable = false)
    private String className;

    // Tuần: chẵn, lẻ
    @Column(nullable = false)
    private Integer week;

    // Ngày trong tuần: Thứ 2 -> Thứ 7
    @Column(nullable = false)
    private Integer weekday;

    // Ca học
    @Column(nullable = false)
    private Integer period;
}
