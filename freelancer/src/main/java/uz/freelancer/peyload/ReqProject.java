package uz.freelancer.peyload;

import uz.freelancer.entity.enums.Category;
import uz.freelancer.entity.enums.Status;

public class ReqProject {

    private String projectName;
    private String description;
    private String price;
    private Status status;
    private Category category;
    private Integer users;

    public ReqProject() {
    }

    public ReqProject(String projectName, String description, String price, Status status, Category category, Integer users) {
        this.projectName = projectName;
        this.description = description;
        this.price = price;
        this.status = status;
        this.category = category;
        this.users = users;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }
}
