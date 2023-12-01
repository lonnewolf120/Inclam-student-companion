module java.codenamex.smc {
    requires java.sql;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires mysql.connector.j;
    requires richtextfx;
    requires java.mail;
    requires java.net.http;
    requires com.google.gson;
    requires org.json;
    requires javafx.media;
    requires AnimateFX;
    requires javafx.web;

    opens codenamex.smc to javafx.fxml, javafx.graphics;
    opens codenamex.smc.Database to javafx.fxml;
    opens codenamex.smc.editor.components to javafx.fxml;
    opens codenamex.smc.model to javafx.base;
    opens codenamex.smc.splashScreen to javafx.fxml, javafx.graphics;
    opens codenamex.smc.dashboards to javafx.fxml, javafx.graphics;
    opens codenamex.smc.notes to javafx.fxml, javafx.graphics;
    opens codenamex.smc.todo to javafx.fxml, javafx.graphics;
    opens codenamex.smc.design to javafx.fxml, javafx.graphics;

    exports codenamex.smc;
    exports codenamex.smc.Database;
    exports codenamex.smc.notes;
    exports codenamex.smc.splashScreen;
    exports codenamex.smc.dashboards;
    exports codenamex.smc.todo;
    exports codenamex.smc.design;
    exports codenamex.smc.viz;
    opens codenamex.smc.viz to javafx.fxml, javafx.graphics;
    exports codenamex.smc.editor;
    opens codenamex.smc.editor to javafx.fxml, javafx.graphics;
}