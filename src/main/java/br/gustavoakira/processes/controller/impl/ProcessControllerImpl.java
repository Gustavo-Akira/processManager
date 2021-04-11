package br.gustavoakira.processes.controller.impl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.gustavoakira.processes.controller.ProcessController;
import br.gustavoakira.processes.model.Process;
import br.gustavoakira.processes.service.ProcessService;
import br.gustavoakira.processes.service.impl.ProcessServiceImpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class ProcessControllerImpl implements ProcessController, Initializable {

	private ProcessService service = new ProcessServiceImpl();
	
	@FXML
	private TableView<Process> table;
	
	@FXML
	private TableColumn<Process, String> nameCol;
	
	@FXML
	private TableColumn<Process, String> pidCol;
	
	@FXML
	private TableColumn<Process, Process> actionCol; 
	
	@FXML
	private TextField  nameFilter;
	
	@Override
	public void excludeByPID(String Pid) throws IOException {
		service.killProcessByPID(Pid);
		listProcesses();
	}

	@Override
	public void excludeByName(String name) throws IOException {
		service.killProcessByName(name);
		listProcesses();
	}

	@Override
	public void listProcesses() throws IOException {
		List<Process> processes = service.getAllProccess();
		ObservableList<Process> process = FXCollections.observableArrayList();
		processes.forEach(x->{
			process.add(x);
		});
		table.setItems(process);
		table.refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listProcesses();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setCellsFactory();
		initializeButtons();
		initializeFilter();
	}
	private void initializeButtons() {
		actionCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		actionCol.setCellFactory(param -> new TableCell<Process, Process>(){
			@Override
			protected void updateItem(Process item, boolean empty) {
				final HBox box = new HBox();
				final Button deleteByName = new Button("Delete All");
				final Button deleteByPID  = new Button("Delete");
				box.setMargin(deleteByPID,new Insets(1.0, 2.0, 2.0, 1.0));
				box.setMargin(deleteByName,new Insets(1.0, 2.0, 2.0, 1.0));
				
				deleteByName.setOnAction(e ->{
					try {
						excludeByName(item.getName());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				});
				deleteByPID.setOnAction(e->{
					try {
						excludeByPID(item.getPid());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				box.getChildren().add(deleteByPID);
				box.getChildren().add(deleteByName);
				
				if(empty) {
					setGraphic(null);
				}
				
				setGraphic(box);
			}
		});
	}
	private void initializeFilter() {
		nameFilter.setOnAction(e->{
			try {
				filterAction();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	private void filterAction() throws IOException {
		listProcesses(nameFilter.getText());
	}
	private void setCellsFactory() {
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
	}
	private void listProcesses(String filter) throws IOException {
		List<Process> processes = service.getAllProccess();
		ObservableList<Process> process = FXCollections.observableArrayList();
		processes.forEach(x->{
			if(x.getName().contains(filter)) {
				process.add(x);
			}
		});
		table.setItems(process);
		table.refresh();
	}
}
