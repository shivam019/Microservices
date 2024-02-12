package com.shivam.jobms.job;
import com.shivam.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobservice;

    public JobController(JobService jobservice ) {
        this.jobservice = jobservice;
    }


    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
        System.out.println("Company" + company.getName());
        System.out.println("Company" + company.getId());
        System.out.println("Company" + company.getDescription());

        return ResponseEntity.ok(jobservice.findAll());


    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobservice.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable  Long id){

        Job job = jobservice.getJobById(id);
        if(job != null) return new ResponseEntity<>(job, HttpStatus.OK);

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){

        boolean deleted = jobservice.deleteJobById(id);

        if(deleted) return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);

        return new ResponseEntity<>("User Not found or Server Error", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobservice.updateJob(id, updatedJob);

        if (updated) return  new ResponseEntity<>("User Updated Successfully", HttpStatus.OK);

        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
}
