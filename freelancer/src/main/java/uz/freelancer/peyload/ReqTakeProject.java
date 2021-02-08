package uz.freelancer.peyload;

public class ReqTakeProject {

    private Integer usersId;
    private Integer projectId;

    public ReqTakeProject() {
    }

    public ReqTakeProject(Integer usersId, Integer projectId) {
        this.usersId = usersId;
        this.projectId = projectId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
