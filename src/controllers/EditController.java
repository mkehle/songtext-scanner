package controllers;

import file.FileOperations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class EditController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField textField;
    @FXML
    private Button addButton;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label display;
    @FXML
    private Label hintLabel;
    @FXML
    private Button removeButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button backButton;
    private List<String> entries;
    private List<String> categories;
    private FileOperations ops;
    private String currCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ops = new FileOperations();
        entries = new ArrayList<>();
        categories = new ArrayList<>();
        categories.addAll(ops.loadCategories("categories"));
        hintLabel.setText("Choose the category you would like to edit");
        choiceBox.getItems().addAll(categories);
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    currCategory = choiceBox.getSelectionModel().getSelectedItem();
                    entries = ops.loadList(currCategory);
                    listView.setItems(FXCollections.observableList(entries));
                    hintLabel.setText("Your saved words for the category " + currCategory.toLowerCase());
            }});
        listView.setItems(FXCollections.observableArrayList(entries));
    }

    @FXML
    public void addItemToList(MouseEvent e) {
        if(currCategory != null) {
            if (!contains(textField.getText().trim())) {
                if (!textField.getText().trim().equals("")) {
                    entries.add(textField.getText().trim());
                    ops.writeToFile(ops.getFileByName(currCategory), ops.listToCSString(entries));
                    listView.setItems(FXCollections.observableList(entries));
                    display.setText("Word added to category.");
                } else {
                    display.setText("Invalid word input.");
                }
            } else {
                display.setText("The entered word already exists.");
            }
        } else {
            display.setText("You need to choose a category.");
        }
        textField.clear();

    }

    private boolean contains(String word) {
        for (String s : entries) {
            if (s.equalsIgnoreCase(word)) return true;
        }
        return false;
    }

    @FXML
    public void removeItemFromList(MouseEvent e) {
        if(currCategory != null) {
            if(listView.getSelectionModel().getSelectedItem() != null) {
                String s = listView.getSelectionModel().getSelectedItem();
                entries.remove(s);
                ops.writeToFile(ops.getFileByName(currCategory), ops.listToCSString(entries));
                listView.setItems(FXCollections.observableList(entries));
            } else {
                display.setText("You have to select an item to remove.");
            }
        } else {
            display.setText("Select a category and word to remove first.");
        }
    }

    @FXML
    public void clearList(MouseEvent e) {
        if(currCategory != null) {
            entries.clear();
            ops.writeToFile(ops.getFileByName(currCategory), ops.listToCSString(entries));
            listView.setItems(FXCollections.observableList(entries));
        } else {
            display.setText("Choose a category first.");
        }
    }

    @FXML
    public void switchToMainScreen(MouseEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Root.fxml"));
        backButton.getScene().setRoot(root);
    }
}
