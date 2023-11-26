package codenamex.smc;

import codenamex.smc.model.TaskProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import codenamex.smc.todo.add_item_controller;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class editTaskInfo implements Initializable {

    @FXML
    private DatePicker birthFld;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField nameFld1;

    @FXML
    private MenuButton prioritySetterBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
    @FXML
    private CheckBox isCompleted;

    @FXML
    private static Integer priority = 0;
    @FXML
    private static String newHeadline = "";
    @FXML
    private static String newDescription = "";
    @FXML
    private static Date newDate = Date.valueOf(LocalDate.now());
    @FXML
    private static Boolean iscomp = false;
    private Stage currentStage;
    private static TaskProperty taskP = new TaskProperty(priority, newHeadline, newDescription, newDate, iscomp);

    public void prioritySetter()
    {
        prioritySetterBox.getItems().removeAll();
        MenuItem menuItem = new MenuItem("Urgent");
        MenuItem menuItem1 = new MenuItem("High");
        MenuItem menuItem2 = new MenuItem("Mid");
        MenuItem menuItem3 = new MenuItem("Low");
        MenuItem menuItem4 = new MenuItem("None");
//        Collection<MenuItem> mnc = new Colle
        prioritySetterBox.getItems().addAll(menuItem,menuItem1,menuItem2,menuItem3,menuItem4);
        menuItem.setOnAction(event ->{
            priority=4;
            prioritySetterBox.setAccessibleText(menuItem.getText());
        });
        menuItem1.setOnAction(event ->{
            priority=3;
            prioritySetterBox.setAccessibleText(menuItem1.getText());
        });
        menuItem2.setOnAction(event ->{
            priority=2;
            prioritySetterBox.setAccessibleText(menuItem2.getText());
        });
        menuItem3.setOnAction(event ->{
            priority=1;
            prioritySetterBox.setAccessibleText(menuItem3.getText());
        });
        menuItem4.setOnAction(event ->{
            priority=0;
            prioritySetterBox.setAccessibleText(menuItem4.getText());
        });
    }
    public static void setTask(TaskProperty tp){
        taskP=tp;
    }
    public static TaskProperty getTask(){
        return taskP;
    }
    public void editIconClick()
    {

    }
    public void setTextField(int id, String H, String D, Date dt, Integer P, Boolean c) {

//        studentId = id;
        nameFld.setText(H);
        nameFld1.setText(D);
        birthFld.setValue(dt.toLocalDate());
        prioritySetterBox.setText(String.valueOf(P));
        isCompleted.setSelected(c);
    }
    private Boolean wbp=false;
    public Boolean wasBtnPressed()
    {
        return wbp;
    }
    public void saveButtonClicked(ActionEvent e)
    {
        prioritySetter();
        newHeadline = nameFld.getText();
        newDescription = nameFld1.getText();
        newDate = Date.valueOf(birthFld.getValue());
        iscomp = isCompleted.isSelected();

        TaskProperty newTask = new TaskProperty(priority,newHeadline,newDescription,newDate,iscomp);
        add_item_controller.edit(taskP, 2); //0 -> insert, 1 -> update
        add_item_controller.edit(newTask,0);
//        wbp = true;
        currentStage.close();
//        dashboard.refreshData();
    }
    public  void cancelButtonClicked(ActionEvent e)
    {
        nameFld.clear();
        nameFld1.clear();
//        birthFld.cancelEdit();
        priority=0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        prioritySetter();
    }


    public void getStage(Stage stage) {
        currentStage = stage;
    }
}
