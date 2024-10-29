package com.project.grintech.projectmanagement.model;

public class Task {
    private String id;
    private String name;
    private String status;
    private String projectId;
    private String resourceId;

    // Constructors, Getters, and Setters

    public Task() {
    }

    public Task(String id, String name, String status, String projectId, String resourceId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.projectId = projectId;
        this.resourceId = resourceId;
    }



    public Task(String id, String name, String status, String projectId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.projectId=projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
