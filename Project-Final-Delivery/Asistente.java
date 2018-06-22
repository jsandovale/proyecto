import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Calendar;
import javafx.scene.control.ChoiceBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;






public class Asistente extends Application {

    Stage window;
    Scene actScene, mensajeScene, menuScene, llamadasScene, noticiaScene ,ocupadoScene;
    
    LocalDateTime diferencia;
    
    TableView<Actividad> actTable;
    TableView<Llamada> llaTable;
    TableView<Mensaje> menTable;
    
    
    TextField nombreInput, descripcionInput;
    DatePicker dateInput;
    int hora, min;
    int estado = 0;
    int delta =0;
    
    ChoiceBox<String> horaInput = new ChoiceBox<>();
    ChoiceBox<String> minInput = new ChoiceBox<>();
  
    
    
    

    ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

    ArrayList<Noticia> noticias = new ArrayList<Noticia>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Asistente");
        window.centerOnScreen();
     // ---------------------------------------------------------------------------------------------------------------------    
        //inicio scene menu
        Label menuLabel = new Label("Bienvenido a su Asistente");
        //inicio botones de cambio de scene
        Button botonActividades = new Button("Actividades");
        botonActividades.setOnAction(e -> window.setScene(actScene));
        
        Button botonLlamadas = new Button("LLamadas");
        botonLlamadas.setOnAction(e -> window.setScene(llamadasScene));
        
        Button botonMensajes = new Button("Mensajes");
        botonMensajes.setOnAction(e -> window.setScene(mensajeScene));
        
        Button botonNoticias = new Button("Noticias");
        //botonNoticias.setOnAction(e -> window.setScene(noticiaScene));
        
        //stackpane
        VBox menuVBox = new VBox();
        menuVBox.setSpacing(20);
        menuVBox.getChildren().addAll(menuLabel,botonActividades,botonLlamadas,botonMensajes,botonNoticias);
        
        Button botonOcupado  = new Button("No Molestar");
        botonOcupado.setOnAction(e ->{ 
        estado = 1;
        diferencia = LocalDateTime.now();
        window.setScene(ocupadoScene);
        
        });
        
        
        VBox menuVBox2 = new VBox();
        menuVBox2.setSpacing(20);
        menuVBox2.getChildren().addAll(botonOcupado);
        
        HBox menuHBox = new HBox();
        menuHBox.setPadding(new Insets(20));
       // menuHBox.setCenterShape(true);
        menuHBox.getChildren().addAll(menuVBox,menuVBox2);
        menuHBox.setSpacing(20);
        
        menuScene = new Scene(menuHBox, 300, 300);
     //   menuScene.c
        //fin menu
        
     // ---------------------------------------------------------------------------------------------------------------------      
        //inicio scene Actividades
        //inicio columnas
        //columna nombre
        TableColumn<Actividad, String> nombreAColumn = new TableColumn<>("Nombre");
        nombreAColumn.setMinWidth(100);
        nombreAColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        //columna descripcion
        TableColumn<Actividad, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setMinWidth(300);
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        //columna date
        TableColumn<Actividad, LocalDateTime> dateColumn = new TableColumn<>("Fecha");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        
        //columna hora
     /*   TableColumn<Actividad, String> horaColumn = new TableColumn<>("Hora");
        horaColumn.setMinWidth(100);
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("date"));*/
        //fin columnas
        //inicio inputs
        //nombre input
        nombreInput = new TextField();
        nombreInput.setPromptText("Nombre");
        nombreInput.setMinWidth(100);

        //descripcion input
        descripcionInput = new TextField();
        descripcionInput.setPromptText("Descripcion");

        //fecha input
        dateInput = new DatePicker(LocalDate.now());
        
        //hora input
        Label dosPuntos = new Label(":");
       // horaInput.setMaxHeight(50);
        minInput.setMaxHeight(50);
        for(int i = 0; i <24; i++) {
        	String aux = Integer.toString(i);
        	horaInput.getItems().add(aux);
        }
        for(int i = 0; i <60; i++) {
        	String aux = Integer.toString(i);
        	minInput.getItems().add(aux);
        }
        horaInput.setValue("0");
        minInput.setValue("0");   
        
        //fin inputs
        //inicio botones
        Button actAddButton = new Button("Agregar");
        actAddButton.setOnAction(e -> addButtonClicked());
        Button actDeleteButton = new Button("Borrar");
        actDeleteButton.setOnAction(e -> ActDeleteButtonClicked());
        Button irMenu = new Button("Menu");
        irMenu.setOnAction(e -> window.setScene(menuScene));
        //fin botones
        Label actLabel = new Label("actividades");
        HBox actHBox = new HBox();
        actHBox.setPadding(new Insets(10,10,10,10));
        actHBox.setSpacing(10);
        actHBox.getChildren().addAll(nombreInput, descripcionInput, dateInput, horaInput,dosPuntos, minInput, actAddButton, actDeleteButton, irMenu);

        actTable = new TableView<>();
        actTable.setItems(getActividades());
       // actTable.getColumns().addAll(nombreAColumn, descripcionColumn, horaColumn);
        actTable.getColumns().addAll(nombreAColumn, descripcionColumn, dateColumn);

        VBox actVBox = new VBox();
        actVBox.getChildren().addAll(actLabel, actTable, actHBox);
        //agregar a la Scene
        actScene = new Scene(actVBox,850,400);
        //Fin Scene Actividades
   // ---------------------------------------------------------------------------------------------------------------------    
        //inicio llamadas
        TableColumn<Llamada, String> llaNombreColumn = new TableColumn<>("Nombre");
        llaNombreColumn.setMinWidth(100);
        llaNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        //columna descripcion
        TableColumn<Llamada, String> llaNumeroColumn = new TableColumn<>("Numero");
        llaNumeroColumn.setMinWidth(300);
        llaNumeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));

        //columna date
        TableColumn<Llamada, LocalDateTime> LlaDateColumn = new TableColumn<>("Fecha");
        LlaDateColumn.setMinWidth(100);
        LlaDateColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));

        Button llaDeleteButton = new Button("Borrar");
        llaDeleteButton.setOnAction(e -> LlaDeleteButtonClicked());
        Button irMenu2 = new Button("Menu");
        irMenu2.setOnAction(e -> window.setScene(menuScene));
        //fin botones
        Label llaLabel = new Label("llamadas");
        HBox llaHBox = new HBox();
        llaHBox.setPadding(new Insets(10,10,10,10));
        llaHBox.setSpacing(10);
        llaHBox.getChildren().addAll(llaDeleteButton, irMenu2);

        llaTable = new TableView<>();
      //  llaTable.setItems(getLlamadas(110));
       // llaTable.getColumns().addAll(nombreAColumn, descripcionColumn, horaColumn);
        llaTable.getColumns().addAll(llaNombreColumn, llaNumeroColumn, LlaDateColumn);

        VBox llaVBox = new VBox();
        llaVBox.getChildren().addAll(llaLabel, llaTable, llaHBox);
        //agregar a la Scene
        llamadasScene = new Scene(llaVBox,850,400);
        
     // ---------------------------------------------------------------------------------------------------------------------    
        //inicio mensajes
        
		  TableColumn<Mensaje, String> menNombreColumn = new TableColumn<>("Nombre");
	        menNombreColumn.setMinWidth(100);
	        menNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));


	        TableColumn<Mensaje, String> menNumeroColumn = new TableColumn<>("Numero");
	        menNumeroColumn.setMinWidth(300);
	        menNumeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
	       
	        TableColumn<Mensaje, String> textoColumn = new TableColumn<>("Mensaje");
	        textoColumn.setMinWidth(300);
	        textoColumn.setCellValueFactory(new PropertyValueFactory<>("mensaje"));


	        TableColumn<Mensaje, LocalDateTime> menDateColumn = new TableColumn<>("Fecha");
	        menDateColumn.setMinWidth(100);
	        menDateColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));

	        Button menDeleteButton = new Button("Borrar");
	        menDeleteButton.setOnAction(e -> menDeleteButtonClicked());
	        Button irMenu3 = new Button("Menu");
	        irMenu3.setOnAction(e -> window.setScene(menuScene));

	        Label menLabel = new Label("Mensajes");
	        HBox menHBox = new HBox();
	        menHBox.setPadding(new Insets(10,10,10,10));
	        menHBox.setSpacing(10);
	        menHBox.getChildren().addAll(menDeleteButton, irMenu3);

	        menTable = new TableView<>();
	      //  menTable.setItems(getMensajes(100));
	        menTable.getColumns().addAll(menNombreColumn, menNumeroColumn,textoColumn ,menDateColumn);

	        VBox menVBox = new VBox();
	        menVBox.getChildren().addAll(menLabel, menTable, menHBox);
	        mensajeScene = new Scene(menVBox,850,400);
        
	        // ---------------------------------------------------------------------------------------------------------------------   
	       
	        
	        Label ocuLabel = new Label("Modo no molestar activado");
	        Button irMenu4 = new Button("Salir modo no molestar");
	        irMenu4.setOnAction(e ->{ 
	        	LocalDateTime auxT = LocalDateTime.now();
	        	delta = getDiferencia(auxT, diferencia);
	        	llaTable.setItems(getLlamadas(delta));
	            menTable.setItems(getMensajes(delta));
	        	estado =0;
	        	window.setScene(menuScene);
	        	});
	        VBox ocuVBox = new VBox();
	        ocuVBox.setPadding(new Insets(20));
	        ocuVBox.getChildren().addAll(ocuLabel,irMenu4);
	        ocuVBox.setSpacing(20);
	        
	        ocupadoScene = new Scene(ocuVBox, 300, 300);
	        
	        // ---------------------------------------------------------------------------------------------------------------------   
	        
        window.setScene(menuScene);
        window.show();
    }

    
    
    
    
    //Add button clicked
    public void addButtonClicked(){
        Actividad actividad = new Actividad();
        actividad.setNombre(nombreInput.getText());
        actividad.setDescripcion(descripcionInput.getText());
       // actividad.setDate(dateInput.getValue());
        actividad.setHora(dateInput.getValue(),Integer.parseInt(horaInput.getValue()),Integer.parseInt(minInput.getValue()));
        System.out.println(actividad.getHora());
        actTable.getItems().add(actividad);
        nombreInput.clear();
        descripcionInput.clear();
       // lugarInput.clear();
    }

    //Delete button clicked
    public void ActDeleteButtonClicked(){
        ObservableList<Actividad> actSelected, allAct;
        allAct = actTable.getItems();
        actSelected = actTable.getSelectionModel().getSelectedItems();

        actSelected.forEach(allAct::remove);
    }
    public void LlaDeleteButtonClicked(){
        ObservableList<Llamada> actSelected, allAct;
        allAct = llaTable.getItems();
        actSelected = llaTable.getSelectionModel().getSelectedItems();

        actSelected.forEach(allAct::remove);
    }
    public void menDeleteButtonClicked(){
        ObservableList<Mensaje> actSelected, allAct;
        allAct = menTable.getItems();
        actSelected = menTable.getSelectionModel().getSelectedItems();

        actSelected.forEach(allAct::remove);
    }
   
    public ObservableList<Llamada> getLlamadas(int delta ){
        ObservableList<Llamada> LlamadasAux = FXCollections.observableArrayList();
        for(int i = 0; i<delta/2; i++) {
        	Llamada aux = new Llamada(delta);
        	LlamadasAux.add(aux);
        }
        	
        return LlamadasAux;
    }
    
    public ObservableList<Mensaje> getMensajes(int delta ){
        ObservableList<Mensaje> MensajesAux = FXCollections.observableArrayList();
        for(int i = 0; i<delta/2; i++) {
        	Mensaje aux = new Mensaje(delta);
        	MensajesAux.add(aux);
        }
        	
        return MensajesAux;
    }
    
    //Get all of the products
    public ObservableList<Actividad> getActividades(){
        ObservableList<Actividad> actividadesAux = FXCollections.observableArrayList();
        return actividadesAux;
    }
    public int getDiferencia(LocalDateTime a, LocalDateTime b) {
    	int hor = a.getHour() - b.getHour();
    	int min = a.getMinute() - b.getMinute();
    	int seg = a.getSecond() - b.getSecond();
    	int total = Math.abs(hor*60*60 + min*60 + seg);
    	return total;
    }
    
    
    

}