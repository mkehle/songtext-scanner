import file.FileOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  private final int WIDTH = 1280;
  private final int HEIGHT = 720;

  public static void main(String[] args) {
    FileOperations ops = new FileOperations();
    ops.createFile("sexism.csv");
    ops.createFile("drugs.csv");
    ops.createFile("insults.csv");
    ops.createFile("homophobic.csv");
    ops.createFile("racism.csv");
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Root.fxml"));
    final Parent root = loader.load();
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    stage.setTitle("Deutschrap Songtext Scanner");
    stage.setMinWidth(WIDTH);
    stage.setMinHeight(HEIGHT);
    stage.setScene(scene);
    stage.show();
  }
}
