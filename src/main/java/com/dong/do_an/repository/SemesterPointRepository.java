package com.dong.do_an.repository;

import com.dong.do_an.entity.SemesterPoint;
import com.dong.do_an.entity.SemesterPointId;
import com.dong.do_an.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterPointRepository extends JpaRepository<SemesterPoint, SemesterPointId> {
    @Query("SELECT e FROM SemesterPoint e WHERE e.semesterPointId.userEmail = :email")
    List<SemesterPoint> getSemesterPointByEmail(@Param("email") String email);
}
