package com.dong.do_an.repository;

import com.dong.do_an.entity.SubjectPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectPointRepository extends JpaRepository<SubjectPoint, Integer> {
}
