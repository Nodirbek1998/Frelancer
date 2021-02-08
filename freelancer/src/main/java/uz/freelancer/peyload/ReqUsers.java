package uz.freelancer.peyload;

import uz.freelancer.entity.enums.PersonType;

public class ReqUsers {

    private String name;

    private String username;

    private String phone;

    private String password;

    private PersonType personType;

    public ReqUsers() {
    }

    public ReqUsers(String name, String username, String phone, String password, PersonType personType) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.personType = personType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
}
