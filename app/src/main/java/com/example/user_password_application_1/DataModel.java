package com.example.user_password_application_1;

public class DataModel {

    String Title;
    String Description;
    int id_;
    int image;
    String PhoneNumber;
    String Address;
    String Location;

    public DataModel(String title, String description, int id_, int image, String phoneNumber, String address, String location) {
        Title = title;
        Description = description;
        this.id_ = id_;
        this.image = image;
        PhoneNumber = phoneNumber;
        Address = address;
        Location = location;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public String getLocation() {
        return Location;
    }

    public String getTitle() {
        return Title;
    }


    public String getDescription() {
        return Description;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
