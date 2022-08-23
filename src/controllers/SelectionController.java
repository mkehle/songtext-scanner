package controllers;

import file.FileOperations;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import search.SearchAlgorithm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectionController implements Initializable {
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
  @FXML
  private ChoiceBox<String> categoryBox;
  private FileOperations ops;
  private List<String> categories;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    ops = new FileOperations();
    categories = new ArrayList<>();
    categories = ops.loadList("categories");
    categoryBox.getItems().addAll(categories);
  }

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
