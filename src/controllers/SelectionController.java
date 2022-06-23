package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import search.SearchAlgorithm;

import java.io.IOException;

public class SelectionController {
  @FXML
  private TextArea textDisplay;
  @FXML
  private TextField searchField;
  @FXML
  private Button searchButton;
  @FXML
  private Label outputLabel;
  @FXML
  private Button backButton;

  public void displayText(String input) {
    textDisplay.setText(input);
  }

  @FXML
  public void search(MouseEvent e) {
    SearchAlgorithm searchAlgorithm = new SearchAlgorithm(textDisplay.getText());
    String word = searchField.getText().trim();
    searchField.clear();
    int amount = searchAlgorithm.countOcc(word);
    if(textDisplay.getText().trim().equals("")) {
      outputLabel.setText("You didn't enter any songtext!");
    } else if(amount == 0) {
      outputLabel.setText("The word you searched for didn't occur!");
    } else {
      outputLabel.setText("'" + word + "'" + " occured " + amount + " times.");
    }
  }

  @FXML
  public void switchToMainScreen(MouseEvent e) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Root.fxml"));
    backButton.getScene().setRoot(root);
  }
}
