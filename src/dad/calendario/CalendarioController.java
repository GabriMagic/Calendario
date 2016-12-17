package dad.calendario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import dad.calendario.utils.DateUtils;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class CalendarioController {

	private CalendarioModel calendario;

	@FXML
	private Button siguienteButton;

	@FXML
	private Label anisoLabel;

	@FXML
	private GridPane mesesPanel;

	@FXML
	private BorderPane view;

	@FXML
	private Button anteriorButton;

	private List<MonthCalendar> listaMeses;

	public CalendarioController(CalendarioApp app) {

		calendario = new CalendarioModel();
		listaMeses = new ArrayList<>();
		FXMLloads();

		calendario.setAnio(LocalDate.now().getYear());

		int mes = 1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				MonthCalendar monthCalendar = new MonthCalendar(calendario.getAnio(), mes);
				
				listaMeses.add(monthCalendar);
				mesesPanel.add(monthCalendar, i, j);
				mes++;
			}
		}
		bind();

	}

	private void bind() {
		for (MonthCalendar mesCalendar : listaMeses) 
			mesCalendar.yearProperty().bind(calendario.anioProperty());
		
		Bindings.bindBidirectional(anisoLabel.textProperty(), calendario.anioProperty(), new NumberStringConverter());
	}

	@FXML
	void onAnterior(ActionEvent event) {
		calendario.setAnio(calendario.getAnio() - 1);
	}

	@FXML
	void onSiguiente(ActionEvent event) {
		calendario.setAnio(calendario.getAnio() + 1);
	}

	private void FXMLloads() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CalendarioView.fxml"));
			loader.setController(this);
			view = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public BorderPane getView() {
		return view;
	}

}
