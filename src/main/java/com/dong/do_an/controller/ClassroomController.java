package com.dong.do_an.controller;

import com.dong.do_an.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomRepository repository;

    @PostMapping("list")
    public ResponseEntity getListClassroom() {
        return ResponseEntity
                .ok()
                .body(repository.findAll());
    }
}
