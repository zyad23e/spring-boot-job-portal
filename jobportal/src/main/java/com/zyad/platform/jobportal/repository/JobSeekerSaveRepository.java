package com.zyad.platform.jobportal.repository;

import com.zyad.platform.jobportal.entity.JobPostActivity;
import com.zyad.platform.jobportal.entity.JobSeekerProfile;
import com.zyad.platform.jobportal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
    List<JobSeekerSave> findByJob(JobPostActivity job);

    @Modifying
    @Transactional
    void deleteByUserIdAndJob(JobSeekerProfile userAccountId, JobPostActivity job);

    @Modifying
    @Transactional
    void deleteByJob(JobPostActivity job);
}

