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

	public void onMenuItemAboutAction() { // m�todo que ir� chamar uma nova tela ao clicar no bot�o 'About'(Sobre)
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	// m�todo que abre uma nova tela
	// synchronized:� usado para garantir que todo o processamento para carregar essa view ele ocorra sem ser interrompido 
	private synchronized void  loadView(String absoluteName) { // absoluteName pq vamos passar o caminho completo
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // temos que criar o objeto loader
																						// para carregar
			// uma tela, no caso, vamos abrir o absoluteName que vai ser o caminho do meu
			// FXML
			VBox newVBox = loader.load(); // carregar o a tela que vai ser um VBox

			/*
			 * Temos que fazer com que essa view seja mostrada dentro da janela principal criada no Main.java. Para que 
			 * eu tenha a condi��o de trabalhar com a janela principal eu vou ter que pegar a referencia da cena que est� no Main.java
			 * 
			 * criei o m�todo Scene getMainScene() l� na classe principal, para q eu possa ter a referencia da cena;
			 * 
			 * Mas por que precisamos de uma referencia da cena principal ? 
			 */
			
			Scene mainScene = Main.getMainScene();
			
			/*
			 
			 No design do mainView.FXML, na janela principal come�a com o <ScrollPane>, dentro do scrollPane temos 
			 o <content> e o <VBox>, no Vbox eu vou ter o <children> que � os filhos do VBox, e um desses filhos � o <MenuBar>
			 
			 Vou ter que pegar uma referencia para o Vbox do mainView e acrescentar nos filhos desse VBox os filhos do VBox da janela
			 about.FXML
			 
			 */
			VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();
/* Cria��o da variavel mainVBox do Vbox da mainview. Vou pegar minha cena principal(mainScene) e chamar a partir dela o 
m�todo getRoot() que pega o primeiro elemento da minha view (no caso, Scroll Pane) para isso vou ter q fazer um CAST do getRoot 
para ScrollPane;
 ((ScrollPane) mainScene.getRoot()) - At� agora s� foi feita a referencia do ScrollPane
 
 Dentro do ScrollPane, seu primeiro filho � o <content>, agora temos que acessar esse content com o m�todo getContent().
 
 ((ScrollPane) mainScene.getRoot()).getContent() - At� agora acessei o conteudo do ScrollPane.
 
  Esse getContent ele � uma referencia para o que estiver no ScrollPane que � o Vbox. Ai � s� fazer um cast que teremos uma referencia
  para o VBox da janela principal.
  
  (VBox) ((ScrollPane) mainScene.getRoot()).getContent() 		
*/		
			
/*
 Agora, tenho que preservar o <MenuBar>, pois sempre vai estar na minha aplica��o, vou ter que exclui tudo que estiver no <children>
 so VBox e incluir o MenuBar e em seguida os filhos do VBox da janela about.fxml 
 
 Tem que guarda uma referencia para o menu
 */
			Node mainMenu = mainVBox.getChildren().get(0);
			// cria��o do atributo mainMenu do tipo Node que vai receber - mainVBox.getChildren() - que s�o os filhos do Vbox 
			//.get(0) pega o primeiro filho do VBox da janela principal;
			mainVBox.getChildren().clear(); // vai limpar todos os filhos do mainVbox
			mainVBox.getChildren().add(mainMenu);// agora vou adicionar no mainVBox o mainMenu 
			mainVBox.getChildren().addAll(newVBox.getChildren());// addAll adiciona uma cole��o , nesse caso os filhos do newVBox			
		} catch (IOException e) {
			// caso tenha um erro vai mostrar o erro na aplica��o
			Alerts.showAlert("IO Exception", "Erro de carregamento da view", e.getMessage(), AlertType.ERROR);
		}
	}

}
