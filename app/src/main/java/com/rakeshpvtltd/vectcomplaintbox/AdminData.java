package com.rakeshpvtltd.vectcomplaintbox;

public class AdminData {

    String Email;
    String Id;
    public AdminData() {

    }

    public AdminData(String email, String id) {
        Email = email;
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
