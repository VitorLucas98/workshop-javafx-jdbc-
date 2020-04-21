package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			// criação da instancia loader do tipo FXMLLoader pegando a view que foi criada 
			
			ScrollPane scrollPane = loader.load();
			// Trocamos o parent por ScrollPane para ele instanciar o objeto correto 
			
			scrollPane.setFitToHeight(true); // preenche toda altura
			scrollPane.setFitToWidth(true); // preenche toda largura 
			
			mainScene = new Scene(scrollPane); 
// criação do objeto scene que vai ser a minha cena principal passando como argumento o objeto principal da minha view (agora o ScrollPane)
			primaryStage.setScene(mainScene);
			//vai ser o palco da minha cena 
			primaryStage.setTitle("Sample JavaFX application"); // titulo da minha cena 
			primaryStage.show(); // mostrar minha cena 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Scene getMainScene() { // método criado para outras classe poderem pegar referencia da cena principal 
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
