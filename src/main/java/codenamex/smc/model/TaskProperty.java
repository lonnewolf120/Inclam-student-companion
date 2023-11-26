package codenamex.smc.model;

//import com.mysql.cj.conf.BooleanProperty;
//import com.mysql.cj.conf.IntegerProperty;
//import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.sql.Date;

public class TaskProperty {
    private IntegerProperty priority;
    private StringProperty headline;
    private StringProperty description;
    private StringProperty deadline;      //this is the sql Date datatype
    private BooleanProperty completed;

    public TaskProperty(){}
    public TaskProperty(Integer priority, String headline, String description, Date deadline, Boolean completed) {
        this.priority = new SimpleIntegerProperty(priority);
        this.headline = new SimpleStringProperty(headline);
        this.description = new SimpleStringProperty(description);
        this.deadline = new SimpleStringProperty(deadline.toString());
        this.completed = new SimpleBooleanProperty(completed);
    }



//    public void setCompleted(Boolean completed) {
//        this.completed = completed;
//    }

    public IntegerProperty priorityProperty() {
        return priority;
    }

    public StringProperty headlineProperty() {
        return headline;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty deadlineProperty() {
        return deadline;
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public int getPriority() {
        return priority.get();
    }

    public String getHeadline() {
        return headline.get();
    }

    public String getDescription() {
        return description.get();
    }

    public Date getDeadline() {
        return Date.valueOf(deadline.get());
    }
    public Boolean getCompleted() {
        return completed.get();
    }
    public boolean isCompleted() {
        return completed.get();
    }
}
