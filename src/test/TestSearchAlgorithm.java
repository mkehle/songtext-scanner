package test;

import search.SearchAlgorithm;

public class TestSearchAlgorithm {
  public static void main(String[] args) {
    String text = "Das ist ein Test-String, welcher super toll ist test!";
    SearchAlgorithm searchAlgorithm = new SearchAlgorithm(text);
    System.out.println(searchAlgorithm.countOcc("test"));
  }
}
