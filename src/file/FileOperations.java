package file;

import javafx.scene.control.Tab;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileOperations {

    public void createFile(String fileName) {
        try {
            File file = new File("resources/files/" + fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(File file, String text) {
        try(BufferedWriter writer = new BufferedWriter((new FileWriter(file)))) {
            writer.write(text);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public File getFileByName(String name) {
        if(name != null) {
            String catName = name.toLowerCase();
            File file = new File("resources/files/" + catName + ".csv");
            if (file.exists()) {
                return file;
            } else {
                System.out.println("When loading file in getFileForTab() a error occured");
                return null;
            }
        }
        return null;
    }

    public String readFromFile(File file) {
        try(BufferedReader reader = new BufferedReader((new FileReader(file)))) {
            return reader.readLine();
        } catch(IOException e) {
            e.printStackTrace();
            return "An error occured when trying to read the file.";
        }
    }

    public void createCategoryFiles(String categories) {
        String line = readFromFile(getFileByName(categories));
        String[] arrCategories = line.toLowerCase().split(",");
        for(String s : arrCategories) {
            createFile(s + ".csv");
        }
    }

    public String listToCSString(List<String> list) {
        String output = list.stream().collect(Collectors.joining(","));
        return output;
    }

    public ArrayList<String> convertStringToList(String text) {
        ArrayList<String> output = new ArrayList<>();
        if (text != null) {
            String[] words = text.split(",");
            Collections.addAll(output, words);
            return output;
        }
        return output;
    }

    public List<String> loadList(String category) {
        return convertStringToList(readFromFile(getFileByName(category)));
    }

    public List<String> loadCategories(String s) {
        return convertStringToList(readFromFile(getFileByName(s)));
    }
}
