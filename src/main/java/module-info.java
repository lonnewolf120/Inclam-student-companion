module java.codenamex.smc {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires com.dlsc.formsfx;
    requires java.sql;
//    requires mysql.connector.j;
//    requires fontawesomefx;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires mysql.connector.j;
//    requires fxfx.richtext;
    requires richtextfx;
    requires java.mail;
    requires java.net.http;
    requires com.google.gson;
    requires org.json;
    requires javafx.media;
    requires AnimateFX;
//    requires MaterialFX;

    opens codenamex.smc to javafx.fxml, javafx.graphics;
    //    opens codenamex.smc.todo_deprecated to javafx.fxml;
    opens codenamex.smc.model to javafx.base;
//    opens codenamex.smc.
    exports codenamex.smc;
//    exports codenamex.smc;
    opens codenamex.smc.Database to javafx.fxml;
    opens codenamex.smc.gui.components to javafx.fxml;
//    opens codenamex.smc.notes.home to javafx.fxml;
    exports codenamex.smc.Database;
    exports codenamex.smc.viz to javafx.graphics;
//    exports codenamex.smc.chatbot;
//    opens codenamex.smc.chatbot to javafx.fxml, javafx.graphics;
//    exports codenamex.smc.notes.home;

//    exports java.codenamex.smc.todo_deprecated;
}