package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}

	public void onMenuItemAboutAction() { // método que irá chamar uma nova tela ao clicar no botão 'About'(Sobre)
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	// método que abre uma nova tela
	// synchronized:é usado para garantir que todo o processamento para carregar essa view ele ocorra sem ser interrompido 
	private synchronized void  loadView(String absoluteName) { // absoluteName pq vamos passar o caminho completo
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // temos que criar o objeto loader
																						// para carregar
			// uma tela, no caso, vamos abrir o absoluteName que vai ser o caminho do meu
			// FXML
			VBox newVBox = loader.load(); // carregar o a tela que vai ser um VBox

			/*
			 * Temos que fazer com que essa view seja mostrada dentro da janela principal criada no Main.java. Para que 
			 * eu tenha a condição de trabalhar com a janela principal eu vou ter que pegar a referencia da cena que está no Main.java
			 * 
			 * criei o método Scene getMainScene() lá na classe principal, para q eu possa ter a referencia da cena;
			 * 
			 * Mas por que precisamos de uma referencia da cena principal ? 
			 */
			
			Scene mainScene = Main.getMainScene();
			
			/*
			 
			 No design do mainView.FXML, na janela principal começa com o <ScrollPane>, dentro do scrollPane temos 
			 o <content> e o <VBox>, no Vbox eu vou ter o <children> que é os filhos do VBox, e um desses filhos é o <MenuBar>
			 
			 Vou ter que pegar uma referencia para o Vbox do mainView e acrescentar nos filhos desse VBox os filhos do VBox da janela
			 about.FXML
			 
			 */
			VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();
/* Criação da variavel mainVBox do Vbox da mainview. Vou pegar minha cena principal(mainScene) e chamar a partir dela o 
método getRoot() que pega o primeiro elemento da minha view (no caso, Scroll Pane) para isso vou ter q fazer um CAST do getRoot 
para ScrollPane;
 ((ScrollPane) mainScene.getRoot()) - Até agora só foi feita a referencia do ScrollPane
 
 Dentro do ScrollPane, seu primeiro filho é o <content>, agora temos que acessar esse content com o método getContent().
 
 ((ScrollPane) mainScene.getRoot()).getContent() - Até agora acessei o conteudo do ScrollPane.
 
  Esse getContent ele é uma referencia para o que estiver no ScrollPane que é o Vbox. Ai é só fazer um cast que teremos uma referencia
  para o VBox da janela principal.
  
  (VBox) ((ScrollPane) mainScene.getRoot()).getContent() 		
*/		
			
/*
 Agora, tenho que preservar o <MenuBar>, pois sempre vai estar na minha aplicação, vou ter que exclui tudo que estiver no <children>
 so VBox e incluir o MenuBar e em seguida os filhos do VBox da janela about.fxml 
 
 Tem que guarda uma referencia para o menu
 */
			Node mainMenu = mainVBox.getChildren().get(0);
			// criação do atributo mainMenu do tipo Node que vai receber - mainVBox.getChildren() - que são os filhos do Vbox 
			//.get(0) pega o primeiro filho do VBox da janela principal;
			mainVBox.getChildren().clear(); // vai limpar todos os filhos do mainVbox
			mainVBox.getChildren().add(mainMenu);// agora vou adicionar no mainVBox o mainMenu 
			mainVBox.getChildren().addAll(newVBox.getChildren());// addAll adiciona uma coleção , nesse caso os filhos do newVBox			
		} catch (IOException e) {
			// caso tenha um erro vai mostrar o erro na aplicação
			Alerts.showAlert("IO Exception", "Erro de carregamento da view", e.getMessage(), AlertType.ERROR);
		}
	}

}
