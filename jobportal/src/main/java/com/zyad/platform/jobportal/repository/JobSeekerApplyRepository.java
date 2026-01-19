package com.zyad.platform.jobportal.repository;

import com.zyad.platform.jobportal.entity.JobPostActivity;
import com.zyad.platform.jobportal.entity.JobSeekerApply;
import com.zyad.platform.jobportal.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);
    List<JobSeekerApply> findByJob(JobPostActivity Job);

    @Modifying
    @Transactional
    void deleteByJob(JobPostActivity job);
}
