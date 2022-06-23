package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RootController {

  @FXML
  private TextArea submition;
  @FXML
  private Button submitButton;
  @FXML
  private Button editCatButton;
  @FXML
  private AnchorPane anchorPane;
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  public void submit(MouseEvent e) throws IOException{
    String input = submition.getText();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectionWindow.fxml"));
    root = loader.load();
    SelectionController selectionController = loader.getController();
    selectionController.displayText(input);
    anchorPane.getChildren().clear();
    anchorPane.getChildren().add(root);
  }

  @FXML
  public void switchToEditCategories(MouseEvent e) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditCategories.fxml"));
    anchorPane.getChildren().clear();
    anchorPane.getChildren().add(loader.load());
  }
}
