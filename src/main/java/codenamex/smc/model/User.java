package codenamex.smc.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String DOB;
    private String institute;

    public User() {}
    public User(String username, String password, String email, String DOB, String institute) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.DOB = DOB;
        this.institute = institute;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }


}
