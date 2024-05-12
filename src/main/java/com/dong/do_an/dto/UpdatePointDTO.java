package com.dong.do_an.dto;

import com.dong.do_an.entity.SubjectPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePointDTO {
    private Integer id;
    private Integer point;
}
