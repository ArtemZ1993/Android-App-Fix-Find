package com.example.user_password_application_1;

public class User {

    private String name;
    private String id;
    private String mail;
    private String password;
    private String Rating;
    private String Profession;
    private String Location;
    private String Phone;

    public User(){

    }

    public User(String name, String id, String mail, String password, String rating, String profession, String Location, String phone) {
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.Rating = rating;
        this.Profession = profession;
        this.Location = Location;
        this.Phone = phone;
    }
    public String getPhone() { return Phone; }
    public void setPhone(String phone) { Phone = phone; }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
