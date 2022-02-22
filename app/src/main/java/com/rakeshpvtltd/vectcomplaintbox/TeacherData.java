package com.rakeshpvtltd.vectcomplaintbox;

public class TeacherData {

    String Email;
    String Id;

    public TeacherData() {
    }

    public TeacherData(String email, String id) {
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
