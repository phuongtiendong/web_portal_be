package com.dong.do_an.controller;

import com.dong.do_an.constants.StatusCode;
import com.dong.do_an.dto.DetailDTO;
import com.dong.do_an.dto.SemesterDTO;
import com.dong.do_an.entity.*;
import com.dong.do_an.model.BaseResponse;
import com.dong.do_an.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("semester")
@RequiredArgsConstructor
public class SemesterController {

    final private UserRepository userRepository;
    final private SemesterRepository semesterRepository;
    final private SubjectRepository subjectRepository;
    final private SubjectTimeRepository subjectTimeRepository;
    final private SemesterPointRepository semesterPointRepository;
    final private SubjectPointRepository subjectPointRepository;

    @PostMapping("list")
    public ResponseEntity getList(Authentication authentication) {
        final Optional<SystemUser> optionalSystemUser = userRepository.findById(authentication.getName());
        if (optionalSystemUser.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(
                            BaseResponse
                                    .builder()
                                    .code(StatusCode.NOT_FOUND)
                                    .build()
                    );
        }

        final SystemUser systemUser = optionalSystemUser.get();
        if (Role.ADMIN.equals(systemUser.getRole())) {
            return ResponseEntity
                    .ok()
                    .body(
                            BaseResponse
                                    .builder()
                                    .code(StatusCode.SUCCESS)
                                    .data(semesterRepository.findAll())
                                    .build()
                    );
        } else {
            return ResponseEntity
                    .ok()
                    .body(
                            BaseResponse
                                    .builder()
                                    .code(StatusCode.SUCCESS)
                                    .data(systemUser.getClassroom().getListSemester())
                                    .build()
                    );
        }
    }

    @PostMapping("detail")
    public ResponseEntity getDetailSemester(@RequestBody DetailDTO detailDTO) {
        return ResponseEntity
                .ok()
                .body(
                        BaseResponse
                                .builder()
                                .code(StatusCode.SUCCESS)
                                .data(semesterRepository.findById(detailDTO.getId()))
                                .build()
                );
    }

    @PostMapping("create")
    @Transactional
    public ResponseEntity createSemester(@RequestBody SemesterDTO semesterDTO) {
        final Semester semester = new Semester();
        semester.setClassroomId(semesterDTO.getClassroomId());
        semester.setStartDate(semesterDTO.getStartDate());
        semester.setEndDate(semesterDTO.getEndDate());
        final Semester createdSemester = semesterRepository.save(semester);

        semesterDTO
                .getListSubject()
                .forEach(item -> {
                    final Subject subject = new Subject();
                    subject.setSemesterId(createdSemester.getId());
                    subject.setName(item.getName());

                    final Subject newSubject = subjectRepository.save(subject);
                    item.getListSubjectTime().forEach(subItem -> {
                        final SubjectTime subjectTime = new SubjectTime();
                        subjectTime.setSubjectId(newSubject.getId());
                        subjectTime.setType(subItem.getType());
                        subjectTime.setTeacherName(subItem.getTeacherName());
                        subjectTime.setClassName(subItem.getClassName());
                        subjectTime.setWeek(subItem.getWeek());
                        subjectTime.setWeekday(subItem.getWeekday());
                        subjectTime.setPeriod(subItem.getPeriod());
                        subjectTimeRepository.save(subjectTime);
                    });
                });

        final List<SystemUser> listUser = userRepository.getUserByClassroomId(semester.getClassroomId());
        listUser.forEach(item -> {
            final SemesterPointId semesterPointId = new SemesterPointId();
            semesterPointId.setSemesterId(createdSemester.getId());
            semesterPointId.setUserEmail(item.getEmail());

            final SemesterPoint semesterPoint = new SemesterPoint();
            semesterPoint.setSemesterPointId(semesterPointId);
            semesterPoint.setStartDate(createdSemester.getStartDate());
            semesterPoint.setEndDate(createdSemester.getEndDate());

            semesterPointRepository.save(semesterPoint);
            final List<Subject> listSubject = subjectRepository.getSubjectBySemesterId(createdSemester.getId());
            listSubject.forEach(subItem -> {
                final SubjectPoint subjectPoint = new SubjectPoint();
                subjectPoint.setSemesterPointId(semesterPointId);
                subjectPoint.setSubjectName(subItem.getName());
                subjectPointRepository.save(subjectPoint);
            });

        });

        return ResponseEntity
                .ok()
                .body(
                        BaseResponse
                                .builder()
                                .code(StatusCode.SUCCESS)
                                .build()
                );
    }
}
