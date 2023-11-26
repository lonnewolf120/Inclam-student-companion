//package main.java.codenamex.smc.design;
//
//import static main.java.codenamex.smc.Database.DatabaseManager.connectDB;
//
//import main.java.codenamex.smc.Database.DatabaseManager;
//import main.java.codenamex.smc.Database.Login;
//import main.java.codenamex.smc.dashboard;
//import main.java.codenamex.smc.model.Task;
//import main.java.codenamex.smc.model.TaskProperty;
//import javafx.scene.control.TableCell;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
////import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
////import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
//import javafx.geometry.Insets;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.fxml.FXMLLoader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import  main.java.codenamex.smc.model.TaskProperty;
//public class ButtonCellFactory {
//
//    public static <TaskProperty, String> TableCell<codenamex.smc.model.TaskProperty, String> createButtonCellFactory() {
//        return new TableCell<codenamex.smc.model.TaskProperty, String>() {
//            @Override
//            public void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                    setText(null);
//                } else {
//                    ImageView deleteIcon = new ImageView("images/Logos/delete.png");
//                    ImageView editIcon = new ImageView("images/Logos/edit.png");
//
//                    deleteIcon.setStyle(
//                            " -fx-cursor: hand ;"
//                                    + "-glyph-size:28px;"
//                                    + "-fx-fill:#ff1744;"
//                    );
//                    editIcon.setStyle(
//                            " -fx-cursor: hand ;"
//                                    + "-glyph-size:28px;"
//                                    + "-fx-fill:#00E676;"
//                    );
//                    deleteIcon.setOnMouseClicked((MouseEvent event) -> {
//                        try {
//                            TaskProperty student = (TaskProperty) getTableView().getItems().get(getIndex());
//                            java.lang.String query =("DELETE FROM `tasks` WHERE id  =" + Login.getUserId());
//                            Connection connection = DatabaseManager.connectDB();
//                            PreparedStatement preparedStatement = connection.prepareStatement(query);
//                            preparedStatement.execute();
////                            dashboard.refreshData();
//                        } catch (SQLException ex) {
////                            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                        }
//                    });
//                    editIcon.setOnMouseClicked((MouseEvent event) -> {
//                        TaskProperty student = (TaskProperty) getTableView().getItems().get(getIndex());
//                        FXMLLoader loader = new FXMLLoader();
//                        loader.setLocation(getClass().getResource("/tableView/addTask.fxml"));
//                        try {
//                            loader.load();
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
////                            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
////                        AddStudentController addStudentController = loader.getController();
////                        addStudentController.setUpdate(true);
////                        addStudentController.setTextField(student.getId(), student.getName(),
////                                student.getBirth().toLocalDate(), student.getAdress(), student.getEmail());
//                        Parent parent = loader.getRoot();
//                        Stage stage = new Stage();
//                        stage.setScene(new Scene(parent));
//                        stage.initStyle(StageStyle.UTILITY);
//                        stage.show();
//                    });
//
//                    HBox managebtn = new HBox(editIcon, deleteIcon);
//                    managebtn.setStyle("-fx-alignment:center");
//                    HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
//                    HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
//
//                    setGraphic(managebtn);
//                    setText(null);
//                }
//            }
//        };
//    }
//}