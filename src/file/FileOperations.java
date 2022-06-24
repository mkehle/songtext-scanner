package file;

import javafx.scene.control.Tab;

import java.io.*;

public class FileOperations {

    public void createFile(String fileName) {
        try {
            File file = new File("A:\\Coding Projects\\resources\\files\\" + fileName);
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

    public File getFileForCategory(String category) {
        if(category != null) {
            String catName = category.toLowerCase();
            File file = new File("A:\\Coding Projects\\resources\\files\\" + catName + ".csv");
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
}
