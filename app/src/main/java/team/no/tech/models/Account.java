package team.no.tech.models;

public class Account {
    private String email;
    private String fullName;
    private Integer age;

    public Account() {

    }

    public Account(String email, String fullName, Integer age) {
        this.email = email;
        this.fullName = fullName;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
