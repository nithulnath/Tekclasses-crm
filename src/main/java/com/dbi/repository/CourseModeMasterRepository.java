package com.dbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbi.model.CourseModeMaster;

@Repository
public interface CourseModeMasterRepository extends JpaRepository<CourseModeMaster, Long> {

}
