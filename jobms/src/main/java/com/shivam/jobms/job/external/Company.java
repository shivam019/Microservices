package com.shivam.jobms.job.external;

import java.util.List;

public class Company {
    private Long id;
    private String name;

    private String description;


    //Every Company has a List of Job
    //CREATE NEW TABLE, RELATIONSHIP WILL BE MANAGE IN NEW TABLE

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
