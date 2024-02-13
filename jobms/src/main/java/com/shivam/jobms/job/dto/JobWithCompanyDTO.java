package com.shivam.jobms.job.dto;

import com.shivam.jobms.job.Job;
import com.shivam.jobms.job.external.Company;

public class JobWithCompanyDTO {

    private Company company;

    private Job job;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
