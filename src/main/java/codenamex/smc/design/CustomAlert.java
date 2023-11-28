package codenamex.smc.design;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomAlert {
    public static void showInfoAlert(String title, String text, String imagePath)
    {
        Alert alt = new Alert(Alert.AlertType.INFORMATION);
            alt.setTitle(title);
            alt.setContentText(text);
            ImageView imgV = new ImageView(); Image img = new Image(imagePath); imgV.setImage(img);
            imgV.setFitHeight(35); imgV.setFitWidth(35);
            alt.setGraphic(imgV);
            alt.show();
    }
}
