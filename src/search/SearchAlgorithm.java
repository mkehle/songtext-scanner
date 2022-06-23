package search;

public class SearchAlgorithm {
  private String[] words;

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
}
