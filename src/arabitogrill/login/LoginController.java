/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.login;

import arabitogrill.addmember.MemberAddController;
import arabitogrill.consts.Consts;
import arabitogrill.util.ArabitoGrillUtil;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import arabitogrill.main.MainController;
import static arabitogrill.util.ArabitoGrillUtil.addmember;

/**
 *
 * @author eulis
 */
public class LoginController {
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    
    private MainController main;
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = DigestUtils.shaHex(password.getText());
        
        if (uname.equals("admin") && password.getText().equals("admin")) {
            closeStage();
            loadMain();
        } else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            FXMLLoader fxmmain = new FXMLLoader(getClass().getResource("/arabitogrill/main/main.fxml"));
            Parent parent = (Parent)fxmmain.load();
            main = (MainController)fxmmain.getController();
            Consts.main = main;
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Arabito Assistant");
            stage.setScene(new Scene(parent));
            ArabitoGrillUtil.setStageIcon(stage);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
