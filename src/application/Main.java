package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			// criação da instancia loader do tipo FXMLLoader pegando a view que foi criada 
			
			Parent parent = loader.load();
			// chamamos através do objeto loader o método load que carrega a view 
			
			Scene mainScene = new Scene(parent); 
// criação do objeto scene que vai ser a minha cena principal passando como argumento o objeto principal da minha view (parent)
			primaryStage.setScene(mainScene);
			//vai ser o palco da minha cena 
			primaryStage.setTitle("Sample JavaFX application"); // titulo da minha cena 
			primaryStage.show(); // mostrar minha cena 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
