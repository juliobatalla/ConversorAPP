package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class ConversorTemperatura extends Application {

    private ComboBox<String> fromTemperatureComboBox;
    private ComboBox<String> toTemperatureComboBox;
    private TextField temperatureTextField;
    private TextArea resultTextArea;
    public static void main(String[] args) {
        launch(args);
    }

    public static void mainApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(" -- Conversor de Temperatura --");
        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/resources/icons_terminal_jb.png")));

        fromTemperatureComboBox = new ComboBox<>();
        fromTemperatureComboBox.getItems().addAll("Grados Fahrenheit", "Grados Celsius", "Kelvin");
        fromTemperatureComboBox.setValue("Grados Fahrenheit");

        toTemperatureComboBox = new ComboBox<>();
        toTemperatureComboBox.getItems().addAll("Grados Celsius", "Kelvin", "Grados Fahrenheit");
        toTemperatureComboBox.setValue("Grados Celsius");

        temperatureTextField = new TextField();

        Button convertButton = new Button("Convertir");
        convertButton.setOnAction(e -> convertTemperature());

        Button closeButton = new Button("Cerrar APP");
        closeButton.setOnAction(e -> primaryStage.close());

        Button mainAppButton = new Button("Menu APP");
        mainAppButton.setOnAction(e -> {
            primaryStage.close(); // Se cierra la ventana del Conversor de Temperatura
            Stage mainAppStage = new Stage();
            new MainApp().start(mainAppStage);
        });

        resultTextArea = new TextArea();
        resultTextArea.getStyleClass().add("result-text-area");
        resultTextArea.setEditable(false);
        resultTextArea.setWrapText(true);
        resultTextArea.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(resultTextArea, Priority.ALWAYS);

        Image logoImage = new Image(getClass().getResourceAsStream("/application/resources/logo_jb3.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitHeight(80);
        logoImageView.setPreserveRatio(true);

        HBox logoContainer = new HBox(10);
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.getChildren().add(logoImageView);
        logoContainer.setPadding(new Insets(30));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(100));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("De:"), 0, 0);
        gridPane.add(fromTemperatureComboBox, 1, 0);
        gridPane.add(new Label("A:"), 0, 1);
        gridPane.add(toTemperatureComboBox, 1, 1);
        gridPane.add(new Label("Cantidad:"), 0, 2);
        gridPane.add(temperatureTextField, 1, 2);
        gridPane.add(convertButton, 1, 3);
        gridPane.add(mainAppButton, 1, 4);
        gridPane.add(closeButton, 1, 5);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(logoContainer, gridPane, resultTextArea);

        Scene scene = new Scene(vbox, 350, 500);
        gridPane.setPadding(new Insets(0, 10, 10, 30));
        scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convertTemperature() {
        String fromTemperature = fromTemperatureComboBox.getValue();
        String toTemperature = toTemperatureComboBox.getValue();
        String temperatureText = temperatureTextField.getText();

        if (temperatureText.isEmpty()) {
            showAlert("No se ha ingresado la cantidad a convertir");
            return;
        }

        try {
            double temperature = Double.parseDouble(temperatureText);

            double convertedTemperature;
            if (fromTemperature.equals("Grados Fahrenheit") && toTemperature.equals("Grados Celsius")) {
                convertedTemperature = (temperature - 32) * 5 / 9;
            } else if (fromTemperature.equals("Grados Celsius") && toTemperature.equals("Kelvin")) {
                convertedTemperature = temperature + 273.15;
            } else if (fromTemperature.equals("Kelvin") && toTemperature.equals("Grados Fahrenheit")) {
                convertedTemperature = (temperature - 273.15) * 9 / 5 + 32;
            } else if (fromTemperature.equals("Grados Celsius") && toTemperature.equals("Grados Fahrenheit")) {
                convertedTemperature = temperature * 9 / 5 + 32;
            } else if (fromTemperature.equals("Kelvin") && toTemperature.equals("Grados Celsius")) {
                convertedTemperature = temperature - 273.15;
            } else {
                convertedTemperature = temperature;
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            resultTextArea.setText(decimalFormat.format(temperature) + " " + fromTemperature + " = "
                    + decimalFormat.format(convertedTemperature) + " " + toTemperature);
        } catch (NumberFormatException e) {
            showAlert("La cantidad ingresada no es válida");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
