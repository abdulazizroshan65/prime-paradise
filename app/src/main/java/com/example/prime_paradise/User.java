package com.example.prime_paradise;

public class User {
    private String username;
    private String name;
    private String email;
    private String dob;
    private String sex;
    byte[] profilepic;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public byte[] getProfilepic() { return profilepic; }
    public void setProfilepic(byte[] profilepic) { this.profilepic = profilepic; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

