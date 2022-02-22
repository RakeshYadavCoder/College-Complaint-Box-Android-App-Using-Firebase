package com.rakeshpvtltd.vectcomplaintbox;

public class UseHelperTesting {

    String roll,email,pass,re_pass;

    public UseHelperTesting() {

    }

    public UseHelperTesting(String roll, String email, String pass, String re_pass) {
        this.roll = roll;
        this.email = email;
        this.pass = pass;
        this.re_pass = re_pass;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRe_pass() {
        return re_pass;
    }

    public void setRe_pass(String re_pass) {
        this.re_pass = re_pass;
    }
}
