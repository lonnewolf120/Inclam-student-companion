package codenamex.smc.model;

import com.mysql.cj.conf.StringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private Integer priority;
    private String headline;
    private String description;
    private Date deadline;      //this is the sql Date datatype
    private Boolean completed;

    public Task(){}
    public Task(Integer priority, String headline, String description, Date deadline, Boolean completed) {
        this.priority = priority;
        this.headline = headline;
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
    }



    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


}
