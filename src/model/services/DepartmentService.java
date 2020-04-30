package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	
	// m�todo que va retorna um lista de departamento com os dados 'Mockados'... (por enquanto)
	// MOCK - � retorna dados de metirinha para fazer uma simula��o
	public List<Department> findAll(){
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Eletronicos"));
		list.add(new Department(2, "Livros"));
		list.add(new Department(3, "Alimenticio"));
		return list;
		
	}
	/* Agora, vamos declara uma dependencia desse servi�o (DepartmentService), l� no controlador da tela 
	   de departmentList (DepartmentListController), e ai vou carregar esse departamento e mostrar na minha tableView
	 */
}
