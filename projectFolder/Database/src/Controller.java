/*
 * Author: Hanna Lahti
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    private Stage stage;
    private Parent root;
    public void switchWindow(ActionEvent event, String filename, Button button){
        try {
            //System.out.printf("user_id : %d", db.user_id);
            stage = (Stage) button.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(filename));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
