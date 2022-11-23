package application;

import java.io.IOException;
import java.net.URL;
import application.Animal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class VueController implements Initializable {

	@FXML
	TableView<Animal> table;
	
	@FXML
	TableColumn<Animal,String> animalCol;

	@FXML
	TableColumn<Animal,String> nomCol;
	
	@FXML
	TableColumn<Animal,String> raceCol;
	
	@FXML
	TableColumn<Animal,String> villeCol;
	
	@FXML
	TableColumn<Animal, Boolean> viewCol;
	
	@FXML
	Button animalView;
	
	@FXML
	ChoiceBox<String> animalBox;
	
	@FXML
	ChoiceBox<String> ageBox;
	
	@FXML
	ChoiceBox<String> sexeBox;
	
	@FXML
	ChoiceBox<String> tailleBox;
	
	@FXML
	ChoiceBox<String> villeBox;
	

	
	ObservableList<Animal> animaux = FXCollections.observableArrayList(
			new Animal("Chien", "Diego","fake/url","Akita inu","5","F","40","Toulouse"),
			new Animal("Chat", "Antonin","fake/url","Sphynx","6","M","30","Castres"),
			new Animal("Corbeau", "Gauthier","fake/url","Europe","2","M","10","Bordeaux"));

	ObservableList<Animal> data = FXCollections.observableArrayList();
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		animalCol.setCellValueFactory(new PropertyValueFactory<>("famille"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		raceCol.setCellValueFactory(new PropertyValueFactory<>("race"));
		villeCol.setCellValueFactory(new PropertyValueFactory<>("coordRefuge"));
	
		
		ObservableList<String> animauxChoice = FXCollections.observableArrayList("Tout");
		ObservableList<String> ageChoice = FXCollections.observableArrayList("Tout","<=5", "<=10","<=15");
		ObservableList<String> sexeChoice = FXCollections.observableArrayList("Tout");
		ObservableList<String> tailleChoice = FXCollections.observableArrayList("Tout","<=20", "<=30","<=40","<=50","<=80");
		ObservableList<String> villeChoice = FXCollections.observableArrayList("Tout");
		
		animaux.forEach((animal) -> { 
		    animauxChoice.add(animal.getFamille());
		    sexeChoice.add(animal.getSexe());
		    villeChoice.add(animal.getCoordRefuge()); 
		}); 
		
		ObservableList<String> animauxUnique = FXCollections.observableArrayList(animauxChoice.stream().distinct().collect(Collectors.toList()));
		ObservableList<String> sexeUnique = FXCollections.observableArrayList(sexeChoice.stream().distinct().collect(Collectors.toList()));
		ObservableList<String> villeUnique = FXCollections.observableArrayList(villeChoice.stream().distinct().collect(Collectors.toList()));
		
		animalBox.setItems(animauxUnique);
		animalBox.setValue("Tout");
		ageBox.setItems(ageChoice);
		ageBox.setValue("Tout");
		sexeBox.setItems(sexeUnique);
		sexeBox.setValue("Tout");
		tailleBox.setItems(tailleChoice);
		tailleBox.setValue("Tout");
		villeBox.setItems(villeUnique);
		villeBox.setValue("Tout");
		
		table.setItems(animaux);	
		addButtonToTable();
	}
	
	public void refresh() {
		data.clear();
		animaux.forEach((animal)->{
			boolean isCheck = true;
			if( animalBox.getValue() != "Tout" && animal.getFamille() != animalBox.getValue() ) {
				isCheck = false;
			}
			if( ageBox.getValue() != "Tout") {
				if(Integer.parseInt(animal.getAge()) > Integer.parseInt((ageBox.getValue()).substring(2))) {
					isCheck = false;					
				}
			}
			if( sexeBox.getValue() != "Tout" && animal.getSexe() != sexeBox.getValue() ) {
				isCheck = false;
			}
			if( tailleBox.getValue() != "Tout") {
				if(Integer.parseInt(animal.getTaille()) > Integer.parseInt((tailleBox.getValue()).substring(2))) {
					isCheck = false;					
				}
			}
			if( villeBox.getValue() != "Tout" && animal.getCoordRefuge() != villeBox.getValue() ) {
				isCheck = false;
			}
			if(isCheck) {
				data.add(animal);
			}
		});
		table.setItems(data);
	}
	
	public void reset() {
		animalBox.setValue("Tout");
		ageBox.setValue("Tout");
		sexeBox.setValue("Tout");
		tailleBox.setValue("Tout");
		villeBox.setValue("Tout");
		table.setItems(animaux);
	}
	
	  private void addButtonToTable() {
	        Callback<TableColumn<Animal, Boolean>, TableCell<Animal, Boolean>> cellFactory = new Callback<TableColumn<Animal, Boolean>, TableCell<Animal, Boolean>>() {
	            @Override
	            public TableCell<Animal, Boolean> call(final TableColumn<Animal, Boolean> param) {
	                final TableCell<Animal, Boolean> cell = new TableCell<Animal, Boolean>() {

	                    private final Button btn = new Button("Action");

	                    {
	                        btn.setOnAction((ActionEvent event) -> {
	                            String data = getTableView().getItems().get(getIndex()).getAge();
	                            Parent rootFXML;
	                            
								try {
									
									rootFXML = FXMLLoader.load(getClass().getResource("card.fxml"));
									 Scene sceneFXML = new Scene(rootFXML);	
									 Stage secondStage = new Stage();
									 secondStage.setUserData(data);
									 secondStage.setScene(sceneFXML);
									 secondStage.show();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                           
	                        });
	                    }

	                    @Override
	                    public void updateItem(Boolean item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };

	        viewCol.setCellFactory(cellFactory);
	    }
}
