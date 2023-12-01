package codenamex.smc.todo;

import codenamex.smc.Database.DatabaseManager;
import codenamex.smc.Database.Login;
import codenamex.smc.model.TaskProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class add_item_controller {
    private static String sql;
    private static final Integer userID = Login.getUserId();
    private static void getQuery(Integer update) {

        if (update==0) {
            sql =  "INSERT INTO `userdata`.`tasks`(`user_id`,`priority`,`headline`,`description`,`deadline`,`completed`) VALUES(?,?,?,?,?,?)";
        }else if(update==1){
            sql = "UPDATE `userdata`.`tasks`\n" +
                    "SET\n" +
                    "`user_id` = ?,\n" +
                    "`priority` = ?,\n" +
                    "`headline` = ?,\n" +
                    "`description` = ?,\n" +
                    "`deadline` = ?,\n" +
                    "`completed` = ?\n" +
                    "WHERE `user_id` = "+ Login.getUserId();
        }
        else if(update==2)
        {
            sql = "DELETE FROM `userdata`.`tasks` WHERE `headline` = ?";
        }
    }
    public static void edit(TaskProperty task, Integer update)
    {
//        Task tasks = new Task(task);
        Connection connection = DatabaseManager.connectDB();
        getQuery(update);
//        String sql = "INSERT INTO `userdata`.`tasks` (`user_id`, `priority`, `headline`, `description`, `deadline`, `completed`) VALUES (?, ?, ?, ?, ?, ?);";
//        DatabaseManager.executeUpdate(sql, task.getTaskid(), task.getUser_id(), task.getPriority(), task.get
        try
        {
//            statement.setInt(1, task.getTaskid());
//            statement.setInt(2, task.getUser_id());
            PreparedStatement statement = connection.prepareStatement(sql);
            if(update!=2)
            {
                statement.setInt(1, Login.getUserId());     //inserting the user_id from Login information to store data properly
                statement.setInt(2, task.getPriority());
                statement.setString(3, task.getHeadline());
                statement.setString(4, task.getDescription());
                statement.setDate(5, task.getDeadline());
                statement.setBoolean(6, task.getCompleted());
            }
            else
            {
                statement.setString(1, task.getHeadline());
            }
            statement.executeUpdate();
            System.out.println("Task added successfully with headline "+ task.getHeadline());;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
