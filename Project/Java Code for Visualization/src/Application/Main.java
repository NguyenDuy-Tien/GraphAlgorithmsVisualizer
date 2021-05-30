package Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Algorithm.*;


public class Main extends Application {

    public static Stage primaryStage;
    public static String algorithmName;
    public static Algorithm algorithm;
    @Override
    public void start(Stage stage) throws Exception {
    	
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("InputMenu.fxml"));        
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
