package com.dong.do_an.controller;

import com.dong.do_an.constants.StatusCode;
import com.dong.do_an.dto.SearchPointDTO;
import com.dong.do_an.dto.UpdatePointDTO;
import com.dong.do_an.entity.*;
import com.dong.do_an.model.BaseResponse;
import com.dong.do_an.model.SemesterUser;
import com.dong.do_an.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("point")
@RequiredArgsConstructor
public class PointController {

    private final UserRepository userRepository;
    private final SemesterPointRepository semesterPointRepository;
    private final SubjectPointRepository subjectPointRepository;

    @PostMapping("admin")
    @Transactional
    @PreAuthorize("hasAuthority(T(com.dong.do_an.entity.Role).ADMIN)")
    public ResponseEntity getPointAdmin(Authentication authentication) {
        authentication.getAuthorities();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            final List<SystemUser> listUser = userRepository.getUserByRole(Role.USER);

            final List<SemesterUser> listSemesterUser = listUser.stream().map(item -> {
                final SemesterUser semesterUser = new SemesterUser();
                semesterUser.setEmail(item.getEmail());
                semesterUser.setName(item.getName());
                semesterUser.setListSemesterPoint(semesterPointRepository.getSemesterPointByEmail(item.getEmail()));
                return semesterUser;
            }).toList();

            return ResponseEntity
                    .ok()
                    .body(
                            BaseResponse
                                    .builder()
                                    .code(StatusCode.SUCCESS)
                                    .data(listSemesterUser)
                                    .build()
                    );

        }


        return ResponseEntity
                .ok()
                .body(
                        BaseResponse
                                .builder()
                                .code(StatusCode.NOT_FOUND)
                                .build()
                );
    }

    @PostMapping("user")
    @Transactional
    @PreAuthorize("hasAuthority(T(com.dong.do_an.entity.Role).USER)")
    public ResponseEntity getPointUser(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(Role.USER.name()))) {
            return ResponseEntity
                        .ok()
                        .body(
                                BaseResponse
                                        .builder()
                                        .code(StatusCode.SUCCESS)
                                        .data(semesterPointRepository.getSemesterPointByEmail(authentication.getName()))
                                        .build()
                        );
        }

        return ResponseEntity
                .ok()
                .body(
                        BaseResponse
                                .builder()
                                .code(StatusCode.NOT_FOUND)
                                .build()
                );
    }

    @PostMapping("update")
    @Transactional
    @PreAuthorize("hasAuthority(T(com.dong.do_an.entity.Role).ADMIN)")
    public ResponseEntity updatePoint(@RequestBody List<UpdatePointDTO> listUpdatePointDTO) {
        final List<SubjectPoint> listSubjectPoint = new ArrayList<>();
        for (UpdatePointDTO item: listUpdatePointDTO) {
            final Optional<SubjectPoint> optionalSubjectPoint = subjectPointRepository.findById(item.getId());
            if (optionalSubjectPoint.isEmpty()) {
                return ResponseEntity
                        .ok()
                        .body(
                                BaseResponse
                                        .builder()
                                        .code(StatusCode.NOT_FOUND)
                                        .build()
                        );
            }

            final SubjectPoint subjectPoint = optionalSubjectPoint.get();
            subjectPoint.setPoint(item.getPoint());
            listSubjectPoint.add(optionalSubjectPoint.get());
        }

        subjectPointRepository.saveAll(listSubjectPoint);

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
