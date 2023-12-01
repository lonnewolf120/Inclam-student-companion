package codenamex.smc.dashboards;

import codenamex.smc.Database.Login;
import codenamex.smc.Database.DatabaseManager;
import codenamex.smc.Except;
import codenamex.smc.todo.editTaskInfo;
import codenamex.smc.fxmlException;
import codenamex.smc.model.TaskProperty;
import codenamex.smc.sceneController;
import codenamex.smc.tictactoe.TicTacToeGui;
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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import codenamex.smc.todo.add_item_controller;
import javafx.util.Callback;

import static codenamex.smc.Database.Const.*;

public class dashboard implements Initializable {

    static Stage stage;
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
    private TableColumn<TaskProperty, Boolean> CompletedCol;
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
    @FXML
    private AnchorPane tableAnchor;
//    @FXML
//    private ScrollPane todoBox;
    private Integer priority;
    private Integer update = 0;

    static ObservableList<TaskProperty> tasksList = FXCollections.observableArrayList();

//    public static void closeButton(MouseEvent e) {
//    }


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
                themeButton.getScene().getRoot().getStylesheets().remove(getClass().getResource(WHITE_THEME).toString());
                themeButton.getScene().getRoot().getStylesheets().add(getClass().getResource(DARK_THEME).toString());
            }
            else
                themeButton.getScene().getRoot().getStylesheets().remove(getClass().getResource(DARK_THEME).toString());
            themeButton.getScene().getRoot().getStylesheets().add(getClass().getResource(WHITE_THEME).toString());

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
        tableAnchor.setTopAnchor(todoBox, 0.0);
        tableAnchor.setBottomAnchor(todoBox, 0.0);
        tableAnchor.setLeftAnchor(todoBox, 0.0);
        tableAnchor.setRightAnchor(todoBox, 0.0);
        try {

            //set the TableView columns
            titleCol.setCellValueFactory(new PropertyValueFactory<>("headline"));
            TaskCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            DateCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
            PriorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
//            CompletedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));

            Callback<TableColumn<TaskProperty, Boolean>, TableCell<TaskProperty, Boolean>> cmpltFoctory = (TableColumn<TaskProperty, Boolean> param) -> {
    // make cell containing buttons
            final TableCell<TaskProperty, Boolean> cell = new TableCell<TaskProperty, Boolean>() {
                Boolean isChecked = false;
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        String edPath = "/images/Logos/checked.png";
                        Image image1 = new Image(getClass().getResourceAsStream(edPath));
                        ImageView editIcon = new ImageView();
                        editIcon.setImage(image1);
                        editIcon.setFitHeight(25);
                        editIcon.setFitWidth(25);

                        String deletePath = "/images/Logos/unchecked.png";
                        Image image2 = new Image(getClass().getResourceAsStream(deletePath));
                        ImageView deleteIcon = new ImageView();
                        deleteIcon.setImage(image2);
                        deleteIcon.setFitHeight(25);
                        deleteIcon.setFitWidth(25);

                        // FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        // FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            TaskProperty task = getTableView().getItems().get(getIndex());
                            isChecked = task.getCompleted();
                            isChecked = !isChecked;
//                            if(isChecked)
//                            {
//                                isChecked = !isChecked;
                                String sql = "UPDATE `userdata`.`tasks` SET `completed` = ? WHERE `user_id` = ?";
                                Connection con = DatabaseManager.connectDB();
                            try {
                                PreparedStatement prepare = con.prepareStatement(sql);
                                prepare.setBoolean(1, isChecked);
                                prepare.setInt(2, Login.getUserId());
                                ResultSet res = prepare.executeQuery();

                            } catch (Except | SQLException e) {
                                throw new Except("SQL Error");
//                                throw new RuntimeException(e);
                            }
                            if(isChecked)setGraphic(editIcon);
                            else setGraphic(deleteIcon);

                        });

                        if(isChecked)setGraphic(editIcon);
                        else setGraphic(deleteIcon);
                        setText(null);
                    }
                }
            };
            return cell;
        };

        CompletedCol.setCellFactory(cmpltFoctory);

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
                            java.lang.String query =("DELETE FROM `userdata`.`tasks` WHERE headline = ?");
                            Connection connection = DatabaseManager.connectDB();
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1,student.getHeadline());
                            preparedStatement.executeUpdate();
                            refreshData();
//                            dashboard.refreshData();
                        } catch (Except | SQLException ex) {
//                            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
                            throw new Except("SQL error, check command & restart server!");
                        }
                            });
                    editIcon.setOnMouseClicked((MouseEvent event) -> {
                                TaskProperty task = getTableView().getItems().get(getIndex());
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource(ADD_TASK));
                                try {
                                    loader.load();
                                } catch (Except | IOException ex) {
//                                    ex.printStackTrace();
                                    throw new Except("Couldn't load ADD_TASK, check for errors in fxml");
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
            String sql = "SELECT priority, headline, description, deadline, completed FROM userdata.tasks WHERE `user_id` = ? ;";
            PreparedStatement preparedStatement = DatabaseManager.connectDB().prepareStatement(sql);
            preparedStatement.setInt(1,Login.getUserId());
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
            alert.show();

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
    static void closeButton(ActionEvent e) {
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }


    public void swtichToNotes(ActionEvent e) throws IOException {
        sceneController.switchToNotes(e);
    }

    public void switchToEditor(ActionEvent E) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(EDITOR_MAIN));
				VBox pane1 = null;
				try {
					pane1 = loader1.load();
				} catch (fxmlException e1) {
//					e1.printStackTrace();
                    throw new fxmlException("Error loading "+EDITOR_MAIN+"\n");
				}
//				AddAdminController controller = loader1.getController();

				Parent root = (Parent) pane1;
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
    }
    public void switchToUser(ActionEvent E) throws IOException {
        sceneController.switchControls(USER_DASHBOARD,E);
    }

    public void switchToStack(MouseEvent mouseEvent) {

    }

    public void switchToTutorial(ActionEvent actionEvent) throws IOException {
        sceneController.switchToTutorial(actionEvent);
    }

    public void closeButton(MouseEvent mouseEvent) {
        sceneController.closeButton(mouseEvent);
    }

    public void gameOn(ActionEvent e) {
        new TicTacToeGui().setVisible(true);
    }

    public void switchToViz(ActionEvent event) throws IOException {
        sceneController.switchToViz(event);
    }
}
