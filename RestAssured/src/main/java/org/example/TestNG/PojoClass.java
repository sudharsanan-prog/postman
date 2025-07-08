package org.example.TestNG;

public class PojoClass {

    //we are creating pojo classes for the output in OAuthAuthorization class
    //there are six parent so we are creating the six pojo classes

    private String Instructor;
    private String Url;
    private String Services;
    private String Expertise;
    private PojoSub Courses;
    private String LinkedIn;

    public String getInstructor(){
        return Instructor;
    }

    public void setInstructor(String instructor) {
        this.Instructor = instructor;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public String getServices() {
        return Services;
    }

    public void setServices(String services) {
        this.Services = services;
    }

    public String getExpertise() {
        return Expertise;
    }

    public void setExpertise(String expertise) {
        Expertise = expertise;
    }

    public PojoSub getCourses() {
        return Courses;
    }

    public void setCourses(PojoSub courses) {
        this.Courses = courses;
    }

    public String getLinkedIn() {
        return LinkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        LinkedIn = linkedIn;
    }
}
