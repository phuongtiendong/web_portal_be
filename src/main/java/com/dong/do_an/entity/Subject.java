package com.dong.do_an.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer semesterId;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "subjectId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SubjectTime> listSubjectTime;
}
