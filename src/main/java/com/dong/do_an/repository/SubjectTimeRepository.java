package com.dong.do_an.repository;

import com.dong.do_an.entity.SubjectTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTimeRepository extends JpaRepository<SubjectTime, Integer> {
}
