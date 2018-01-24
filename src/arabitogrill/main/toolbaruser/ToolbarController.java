/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main.toolbaruser;

import arabitogrill.util.ArabitoGrillUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 *
 * @author eulis
 */
public class ToolbarController  implements Initializable {

    @FXML
    private TableView toolbarUser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //Todo
    }
    
    @FXML
    private void loadAddMember(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addmember/member_add.fxml"), "Add New Member", null);
    }
}

