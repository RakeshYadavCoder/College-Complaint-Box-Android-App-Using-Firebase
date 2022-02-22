package com.rakeshpvtltd.vectcomplaintbox;

public class StudentData {

    String Email;
    String Id;
    public StudentData() {
    }

    public StudentData(String email, String id) {
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
