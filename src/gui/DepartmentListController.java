package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service; 
	/*declara��o da dependencia de DepartmentService
	 
	 Agora, tenho que fazer a inje��o dessa dependencia nesta classe, pois se fizer o jeito tradicional ( = new DepartmentService() )
	 vai fazer com que haja o aclopamento forte, para isso � necessario a cria��o de um m�todo(setDepartmentService) para setar 
	 o service; 

	 */
	
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList; // vou  ultilizar essa ObservableList para carregar os departamentos

	@FXML
	public void onBtNewAction() {
		System.out.println("A��o do bot�o");
	}
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	/* Temos que configurar as tabelas pois o simples fato de declara as colunas n�o faz com que essa tabela funcione 
	 - o m�todo abaixo faz com que inicialize um componente na tela  */
	private void initializeNodes() {
		
		// � padr�o do javaFx para ele iniciar o comportamento das colunas
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));

		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Abaixo, vamos fazer um comando para a tela do tableViewDepartment preencha toda a janela 
		
		Stage stage = (Stage) Main.getMainScene().getWindow(); // vamos pegar um referncia do Stage atual, vou acessa a classe principal
		// o getMainScene acessa a cena,  e getWindow pela a referencia da janela(Como window � uma superclasse do stage para que 
		// possa atribuir para o stage tem-se que fazer um cast)

		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // comando que faz com que o tableViewDepartment 
		//acompanhar a janela
	}
	
	
	/*
	 Esse m�todo � responsavel por acessar o servi�o, carregar os departamento e jogar esses departamentos na lista do ObservableList
	 ai vou associar esse ObservableList com o TableView e ai os departamentos v�o aparecer na tela
	 */
	public void updateTableView() {
		if(service == null) { // teste para testar se o programador fez a inje��o desse servi�o 
			throw new IllegalStateException("Servi�o est� nulo");
		}
		List<Department> list = service.findAll(); // vou recuperar os departamentos 
		obsList = FXCollections.observableArrayList(list);// instancia o obsList pegando os dados da lista acima
		tableViewDepartment.setItems(obsList); // vou chamar o tableViewDepartment e usar o m�todo setItems que carrega os itens
		// na tableViewDepartment e mostrar na tela
		
		/*
		O m�todo acima ainda n�o funcionou pq ele ainda precisa ser chamado, vamos chamar manualmente
		 		 * */
		}

}
