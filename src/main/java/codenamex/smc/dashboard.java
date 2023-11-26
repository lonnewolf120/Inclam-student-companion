package codenamex.smc;

import codenamex.smc.Database.Login;
import codenamex.smc.Database.DatabaseManager;
import codenamex.smc.model.TaskProperty;
import javafx.beans.property.*;
import codenamex.smc.design.ToggleSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import codenamex.smc.todo.add_item_controller;
import javafx.util.Callback;

public class dashboard implements Initializable {

    Stage stage;
    @FXML
    private Button AddTask;

    @FXML
    private Button EnterFullScreen;

    @FXML
    private Button clear;

    @FXML
    private StackPane contentArea;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField descriptionText;

    @FXML
    private Button markComplete;

    @FXML
    private CheckBox noDueDateCheckbox;

    @FXML
    private MenuButton prioritySetterBox;

//    @FXML
//    private ListView<?> taskList;

    @FXML
    private TextField taskTitile;
    //#Task Table
    @FXML
    private TableView<TaskProperty> taskTable;
    @FXML
    private TableColumn<TaskProperty, SimpleStringProperty> titleCol;
    @FXML
    private TableColumn<TaskProperty, SimpleStringProperty> TaskCol;
    @FXML
    private TableColumn<TaskProperty, SimpleStringProperty> DateCol;
    @FXML
    private TableColumn<TaskProperty, SimpleIntegerProperty> PriorityCol;
    @FXML
    private TableColumn<TaskProperty, SimpleBooleanProperty> CompletedCol;
    @FXML
    private TableColumn<TaskProperty, String> editCol;


    @FXML
    private ScrollPane todoBox;

    @FXML
    private AnchorPane topbar;

    @FXML
    private CheckBox urgentCheckbox;

    @FXML
    private BorderPane window;
    @FXML
    private Button refreshButton;

    private Integer priority;
    private Integer update = 0;

    static ObservableList<TaskProperty> tasksList = FXCollections.observableArrayList();



    public void ToggleSwitch()
    {
        ToggleSwitch themeButton = new ToggleSwitch();
        themeButton.setLayoutX(6);
        themeButton.setLayoutY(3);
        topbar.getChildren().add(themeButton);

        SimpleBooleanProperty isOn = themeButton.switchOnProperty();
        isOn.addListener((observable, oldValue, newValue)->{
            if(newValue)
            {
                themeButton.getScene().getRoot().getStylesheets().remove(getClass().getResource("css-files/whitetheme.css").toString());
                themeButton.getScene().getRoot().getStylesheets().add(getClass().getResource("css-files/darktheme.css").toString());
            }
            else
                themeButton.getScene().getRoot().getStylesheets().remove(getClass().getResource("css-files/darktheme.css").toString());
            themeButton.getScene().getRoot().getStylesheets().add(getClass().getResource("css-files/whitetheme.css").toString());

        });
    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleSwitch();
        prioritySetter();
        try {

            //set the TableView columns
            titleCol.setCellValueFactory(new PropertyValueFactory<>("headline"));
            TaskCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            DateCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
            PriorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
            CompletedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));

            Callback<TableColumn<TaskProperty, String>, TableCell<TaskProperty, String>> cellFoctory = (TableColumn<TaskProperty, String> param) -> {
                // make cell containing buttons
                final TableCell<TaskProperty, String> cell = new TableCell<TaskProperty, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);

                        } else {
                            String edPath = "/images/Logos/edit.png";
                            Image image1 = new Image(getClass().getResourceAsStream(edPath));
                            ImageView editIcon = new ImageView();
                            editIcon.setImage(image1);
                            editIcon.setFitHeight(25);
                            editIcon.setFitWidth(25);

                            String deletePath = "/images/Logos/delete.png";
                            Image image2 = new Image(getClass().getResourceAsStream(deletePath));
                            ImageView deleteIcon = new ImageView();
                            deleteIcon.setImage(image2);
                            deleteIcon.setFitHeight(25);
                            deleteIcon.setFitWidth(25);

//                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                    deleteIcon.setStyle(
                            " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#ff1744;"
                    );
                    editIcon.setStyle(
                            " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#00E676;"
                    );
                    deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                        try {
                            TaskProperty student = getTableView().getItems().get(getIndex());
                            java.lang.String query =("DELETE FROM `tasks` WHERE headline = ?");
                            Connection connection = DatabaseManager.connectDB();
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1,student.getHeadline());
                            preparedStatement.executeUpdate();
                            refreshData();
//                            dashboard.refreshData();
                        } catch (SQLException ex) {
//                            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                        }
                            });
                    editIcon.setOnMouseClicked((MouseEvent event) -> {
                                TaskProperty task = getTableView().getItems().get(getIndex());
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("addTask.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
//                            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                editTaskInfo addTask = loader.getController();

//                              addTask.setUpdate(true);
                                addTask.getStage(stage);
                                addTask.setTask(task);
//                                addTask.getLoader(loader);
                                addTask.setTextField(Login.getUserId(), task.getHeadline(), task.getDescription(),
                                task.getDeadline(), task.getPriority(),task.getCompleted());

                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
//                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();

                            });

                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn);

                            setText(null);

                        }
                    }

                };

                return cell;
            };
//            .setCellValueFactory(new PropertyValueFactory<>("headline"))
            editCol.setCellFactory(cellFoctory);
            // Retrieve data from the database and populate an ObservableList
//            tasksList.clear();
            tasksList.addAll(fetchDataFromDatabase());
            /*for (TaskProperty task : tasksList) {
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Headline: " + task.getHeadline());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Deadline: " + task.getDeadline());
                System.out.println("Description: " + task.getCompleted())
            }*/
            // Bind the data to the TableView
            taskTable.setItems(tasksList);
        }
        catch(Exception e)
        {
            System.out.println("Some Columns were null");
            e.printStackTrace();
        }
    }
    @FXML
    void refreshButtonData(ActionEvent e)
    {
        refreshData();
    }
    public void refreshData() {
        tasksList.clear();
        tasksList.addAll(fetchDataFromDatabase());
        taskTable.refresh();
        taskTable.setItems(tasksList);
    }

    private static ObservableList<TaskProperty> fetchDataFromDatabase() {
        ObservableList<TaskProperty> tasks = FXCollections.observableArrayList();
        try
        {
            String sql = "SELECT priority, headline, description, deadline, completed FROM userdata.tasks;";
            PreparedStatement preparedStatement = DatabaseManager.connectDB().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Integer priority = resultSet.getInt("priority");
                String headline = resultSet.getString("headline");
                String description = resultSet.getString("description");
                Date deadline = resultSet.getDate("deadline");
                Boolean completed = resultSet.getBoolean("completed");
                TaskProperty task = new TaskProperty(priority,headline,description,deadline,completed);
                tasks.add(task);
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return tasks;
    }

    public void AddTaskButton(ActionEvent e)
    {
        update = 0;
        Boolean isComplete = markComplete.isPressed();
        Date newDate = Date.valueOf(datePicker.getValue());
        if(newDate==null) newDate=Date.valueOf(LocalDate.now());
        if(priority==null) priority=0;
        String title, desc;
        title=(taskTitile.getText().isEmpty())? "" : taskTitile.getText();
        desc=(descriptionText.getText().isEmpty())? "" : descriptionText.getText();
        TaskProperty task = new TaskProperty(priority,title, desc,newDate,isComplete);
        add_item_controller.edit(task,update);    //#Main segment
        refreshData();
//        //#Animation : dont keep important refresh code after animation, stops code
//        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), AddTask);
//        pulse.setToX(1.1);
//        pulse.setToY(1.1);
//        pulse.setAutoReverse(true);
//        pulse.setCycleCount(Animation.INDEFINITE);
//        AddTask.setOnMouseEntered(event -> pulse.play());
//        AddTask.setOnAction(event -> pulse.play());
//        AddTask.setOnMouseExited(event -> pulse.stop());
    }
    public void EditTaskButton(ActionEvent e)
    {
        update = 1;

    }

    void deleteTaskButton(ActionEvent e)
    {
        TaskProperty selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Task");
            alert.setContentText("Are you sure you want to delete this task?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Proceed with deleting the task
                // Your code to delete the task from the database and remove it from the tasksList
                update=2;
                add_item_controller.edit(selectedTask,update);
            }
        } else {
            // No task selected, show an error message or handle it accordingly
        }
    }
    String tt, td;
    @FXML
    void clearPressed(ActionEvent e)
    {
        tt = taskTitile.getText();
        td = descriptionText.getText();
        taskTitile.clear();
        descriptionText.clear();
//        datePicker.cancelEdit();
//        priority=0;
    }
    @FXML
    void undoClear(ActionEvent e)
    {
        taskTitile.setText(tt);
        descriptionText.setText(td);
    }
    @FXML
    void closeButton(ActionEvent e) {
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }


}
