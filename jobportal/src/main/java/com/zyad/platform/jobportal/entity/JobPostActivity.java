package com.zyad.platform.jobportal.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "job_post_activity")
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostId;

    @ManyToOne
    //                               referencedColumnName references the user_id inside the users table
    @JoinColumn(name = "postedById", referencedColumnName = "userId")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    //                                  referencedColumnName references the id inside the job_location table
    @JoinColumn(name = "jobLocationId", referencedColumnName = "Id")
    private JobLocation jobLocation;

    @ManyToOne(cascade = CascadeType.ALL)
    //                                 referencedColumnName references the id inside the job_company table
    @JoinColumn(name = "jobCompanyId", referencedColumnName = "Id")
    private JobCompany jobCompanyId;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isSaved;

    @Length(max = 10000)
    private String descriptionOfJob;

    private String jobTitle;
    private String jobType;
    private String remote;
    private String salary;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date postedDate;

    public JobPostActivity() {
    }

    public JobPostActivity(Integer jobPostId, Date postedDate, String salary, String remote, String jobType, String jobTitle, String descriptionOfJob, Boolean isSaved, Boolean isActive, JobCompany jobCompanyId, JobLocation jobLocation, Users postedById) {
        this.jobPostId = jobPostId;
        this.postedDate = postedDate;
        this.salary = salary;
        this.remote = remote;
        this.jobType = jobType;
        this.jobTitle = jobTitle;
        this.descriptionOfJob = descriptionOfJob;
        this.isSaved = isSaved;
        this.isActive = isActive;
        this.jobCompanyId = jobCompanyId;
        this.jobLocation = jobLocation;
        this.postedById = postedById;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Users getPostedById() {
        return postedById;
    }

    public void setPostedById(Users postedById) {
        this.postedById = postedById;
    }

    public JobLocation getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(JobLocation jobLocation) {
        this.jobLocation = jobLocation;
    }

    public JobCompany getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(JobCompany jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }

    public @Length(max = 10000) String getDescriptionOfJob() {
        return descriptionOfJob;
    }

    public void setDescriptionOfJob(@Length(max = 10000) String descriptionOfJob) {
        this.descriptionOfJob = descriptionOfJob;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "JobPostActivity{" +
                "jobPostId=" + jobPostId +
                ", postedById=" + postedById +
                ", jobLocation=" + jobLocation +
                ", jobCompanyId=" + jobCompanyId +
                ", isActive=" + isActive +
                ", isSaved=" + isSaved +
                ", descriptionOfJob='" + descriptionOfJob + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobType='" + jobType + '\'' +
                ", remote='" + remote + '\'' +
                ", salary='" + salary + '\'' +
                ", postedDate=" + postedDate +
                '}';
    }
}
