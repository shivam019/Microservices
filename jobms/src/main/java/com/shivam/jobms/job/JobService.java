package com.shivam.jobms.job;

import java.util.List;

//for loose coupling and modularity we have use Interface.
public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);



}
