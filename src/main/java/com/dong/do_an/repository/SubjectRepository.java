package com.dong.do_an.repository;

import com.dong.do_an.entity.Subject;
import com.dong.do_an.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("SELECT e FROM Subject e WHERE e.semesterId = :semesterId")
    List<Subject> getSubjectBySemesterId(@Param("semesterId") Integer semesterId);
}
