package dad.calendario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarioApp extends Application {

	private CalendarioController calendarioController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		calendarioController = new CalendarioController(this);
		
		primaryStage.setTitle("Calendario");
		primaryStage.setScene(new Scene(calendarioController.getView(), 650, 550));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
