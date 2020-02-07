package com.noon.a7pets.models;

public class User {

    private String name;
    private String mobile;
    private String email;
    private String photo;
    private String address;

    public User() {
    }

    public User(String name,
                String mobile,
                String email,
                String photo,
                String address) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.photo = photo;
        this.address = address;
    }

    public User(String username, String phoneNumber, String email, String profileImageUrl) {
        this.name = username;
        this.mobile = phoneNumber;
        this.email = email;
        this.photo = profileImageUrl;
    }

    public User(String username, String phoneNumber, String email) {
        this.name = username;
        this.mobile = phoneNumber;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
