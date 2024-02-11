package com.shivam.jobms.job.impl;


import com.shivam.jobms.job.Job;
import com.shivam.jobms.job.JobRepository;
import com.shivam.jobms.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Service: It makes  the things Available at the runtime and inject it to the Controller.
@Service
public class JobServiceimpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();

//Define Repository Object for Data Persistence

JobRepository jobRepository;
    public JobServiceimpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //jobRepository is a bean that is managed by Spring so because of this constructor
    // it will autowired at the runtime


    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
       jobRepository.save(job);
    }


    //Get user by id:
    @Override
    public Job getJobById(Long id) {

//        for(Job job : jobs) {
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;

        return  jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if (job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent())
        {
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setLocation(updatedJob.getLocation()); // Corrected method call to setLocation
                job.setMaxSalary(updatedJob.getMaxSalary()); // Corrected method call to setMaxSalary
                jobRepository.save(job);
                return true;

        }
        return false;

    }

}
