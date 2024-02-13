package com.shivam.jobms.job.mapper;

import com.shivam.jobms.job.Job;
import com.shivam.jobms.job.dto.JobWithCompanyDTO;
import com.shivam.jobms.job.external.Company;
import com.shivam.jobms.job.external.Review;
import java.util.*;

//Mapper
public class JobMapper {

    public static JobWithCompanyDTO jobDTO(Job job, Company company, List<Review> review) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();

        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReview(review);

        return jobWithCompanyDTO;
    }




}
