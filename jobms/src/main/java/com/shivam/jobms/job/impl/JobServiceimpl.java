package com.shivam.jobms.job.impl;

import com.shivam.jobms.job.Job;
import com.shivam.jobms.job.JobRepository;
import com.shivam.jobms.job.JobService;
import com.shivam.jobms.job.dto.JobWithCompanyDTO;
import com.shivam.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Service: It makes  the things Available at the runtime and inject it to the Controller.
@Service
public class JobServiceimpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();

//Define Repository Object for Data Persistence

    @Autowired
    RestTemplate restTemplate;

JobRepository jobRepository;
    public JobServiceimpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //jobRepository is a bean that is managed by Spring so because of this constructor
    // it will autowired at the runtime


    @Override
    public List<JobWithCompanyDTO> findAll() {

//        Approach 1 :
//        List<Job> jobs = jobRepository.findAll();
//        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        for ( Job job:
//             jobs) {
//            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//            jobWithCompanyDTO.setJob(job);
//
//            Company company = restTemplate.getForObject("http://localhost:8081/companies" + job.getCompanyId(), Company.class);
//            jobWithCompanyDTO.setCompany(company);
//
//            jobWithCompanyDTOs.add(jobWithCompanyDTO);
//
//        }

//Approach : 2

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());

    }

    private JobWithCompanyDTO convertToDto(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
//        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://company-service:8081/companies/" +  + job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
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
