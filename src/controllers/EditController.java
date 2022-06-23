package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditController implements Initializable {

  @FXML
  private ListView<String> sexismusList;
  @FXML
  private TextField enterSexismus;
  @FXML
  private Button addButton;
  private ArrayList<String> entries;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    entries = new ArrayList<>();
  }

  @FXML
  public void addItemToList(MouseEvent e) {
    if (!enterSexismus.getText().trim().equals("")) {
      entries.add(enterSexismus.getText().trim());
    }
  }
}
