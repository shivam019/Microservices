package com.shivam.jobms.job;

import com.shivam.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

//for loose coupling and modularity we have use Interface.
public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    JobWithCompanyDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);



}
