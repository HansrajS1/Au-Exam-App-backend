package com.AU.Exam.App.Repository;

import com.AU.Exam.App.Model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findBySubjectContainingIgnoreCase(String subject);
}
