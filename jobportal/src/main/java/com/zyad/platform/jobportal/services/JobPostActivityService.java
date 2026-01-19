package com.zyad.platform.jobportal.services;

import com.zyad.platform.jobportal.entity.*;
import com.zyad.platform.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;

    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository,
                                 JobSeekerApplyService jobSeekerApplyService,
                                 JobSeekerSaveService jobSeekerSaveService) {
        this.jobPostActivityRepository = jobPostActivityRepository;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        return Objects.isNull(searchDate) ? jobPostActivityRepository.searchWithoutDate(job, location, remote, type):
                jobPostActivityRepository.search(job,location,remote,type,searchDate);
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
        List<IRecruiterJob> recruiterJobsDto = jobPostActivityRepository.getRecruiterJobs(recruiter);
        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
        for (IRecruiterJob rec: recruiterJobsDto){
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDtoList;
    }

    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }


    @Transactional
    public void deleteJobPost(JobPostActivity jobPostActivity){
        // Delete dependent rows first to avoid FK issues
        jobSeekerApplyService.removeAllApplicationsForJob(jobPostActivity);
        jobSeekerSaveService.removeAllSavedForJob(jobPostActivity);
        jobPostActivityRepository.delete(jobPostActivity);
    }


    /**
     * Deletes a job post owned by the given recruiter.
     * Also deletes dependent JobSeekerApply and JobSeekerSave rows to avoid FK constraint issues.
     */
    @Transactional
    public void deleteJobAsRecruiter(int jobPostId, int recruiterUserId) {
        JobPostActivity job = getOne(jobPostId);
        if (job.getPostedById() == null || job.getPostedById().getUserId() != recruiterUserId) {
            throw new RuntimeException("You are not authorized to delete this job");
        }

        // Remove dependent rows first
        jobSeekerApplyService.removeAllApplicationsForJob(job);
        jobSeekerSaveService.removeAllSavedForJob(job);

        jobPostActivityRepository.delete(job);
    }

}
