package com.shivam.jobms.job.impl;

import com.shivam.jobms.job.Job;
import com.shivam.jobms.job.JobRepository;
import com.shivam.jobms.job.JobService;
import com.shivam.jobms.job.dto.JobWithCompanyDTO;
import com.shivam.jobms.job.external.Company;
import com.shivam.jobms.job.external.Review;
import com.shivam.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
// jobRepository is a bean that is managed by Spring so because of this constructor
// it will autowired at the runtime

    JobRepository jobRepository;
    public JobServiceimpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Autowired
    RestTemplate restTemplate;



    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());

    }

    private JobWithCompanyDTO convertToDto(Job job){
        Company company = restTemplate.getForObject("http://company-service:8081/companies/" +  + job.getCompanyId(), Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://review-service:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {}
        );

        List<Review> reviews = reviewResponse.getBody();



        JobWithCompanyDTO jobWithCompanyDTO = JobMapper.jobDTO(job,company, reviews );

        return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
       jobRepository.save(job);
    }


    //Get user by id:
    @Override
    public JobWithCompanyDTO getJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);

        return convertToDto(job);

    }

    @Override
    public boolean deleteJobById(Long id) {
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
