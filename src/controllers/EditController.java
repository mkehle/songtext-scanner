package controllers;

import file.FileOperations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
    private List<String> entries;
    private FileOperations ops;
    private String currCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ops = new FileOperations();
        entries = new ArrayList<>();
        choiceBox.getItems().addAll("Sexism", "Drugs", "Insults", "Homophobic", "Racism");
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currCategory = choiceBox.getSelectionModel().getSelectedItem();
                entries = loadList(currCategory);
                listView.setItems(FXCollections.observableList(entries));
                hintLabel.setText("Your saved words for the category " + currCategory.toLowerCase());
            }
        });
        listView.setItems(FXCollections.observableArrayList(entries));
    }

    @FXML
    public void addItemToList(MouseEvent e) {
        if (!contains(textField.getText().trim())) {
            if (!textField.getText().trim().equals("")) {
                entries.add(textField.getText().trim());
                ops.writeToFile(ops.getFileForCategory(currCategory), listToCSString(entries));
                display.setText("Word added to category.");
            } else {
                display.setText("Invalid word input.");
            }
        } else {
            display.setText("The entered word already exists.");
        }
        textField.clear();
    }

    private boolean contains(String word) {
        for (String s : entries) {
            if (s.equalsIgnoreCase(word)) return true;
        }
        return false;
    }

    private String listToCSString(List<String> list) {
        String output = list.stream().collect(Collectors.joining(","));
        return output;
    }

    private List<String> convertStringToList(String text) {
        if (text != null) {
            String[] words = text.split(",");
            return Arrays.asList(words);
        }
        return new ArrayList<>();
    }

    private List<String> loadList(String category) {
        return convertStringToList(ops.readFromFile(ops.getFileForCategory(category)));
    }
}
