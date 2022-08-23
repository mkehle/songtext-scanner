package search;

import file.FileOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchAlgorithm {
  private String[] words;
  private FileOperations ops;

  public SearchAlgorithm(String text) {
    words = text.split("\\P{L}+");
  }

  public String[] getWords() {
    return words;
  }

  public int countOcc(String word) {
    int counter = 0;
    for(String s : words) {
      if(s.equalsIgnoreCase(word)) {
        counter++;
      }
    }
    return counter;
  }

  public HashMap<String, Integer> countOccCat(String category) {
    HashMap<String, Integer> output = new HashMap<>();
    List<String> categories = new ArrayList<>();
    categories.addAll(ops.loadCategories("categories"));



    return output;
  }
}
