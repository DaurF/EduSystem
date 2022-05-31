package kz.daur.edusystem;

public class User {

    public String fullName, age, email, city = "City", country = "Country", dateOfBirth = "Date of Birth", job = "Job", edu = "Education", biography = "Biography";
    boolean gender;
    public User() {

    }

    public User(String fullName, String age, String email) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public User(String fullName, String age, Boolean gender, String city, String country, String dateOfBirth, String job, String edu, String biography) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.job = job;
        this.edu = edu;
        this.biography = biography;
    }

}
