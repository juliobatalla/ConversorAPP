package application;

import javafx.application.Application;
import javafx.application.Platform;
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

public class ConversorLongitud extends Application {

    private ComboBox<String> fromLengthComboBox;
    private ComboBox<String> toLengthComboBox;
    private TextField lengthTextField;
    private TextArea resultTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(" -- Conversor de Longitud --");

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/resources/icons_terminal_jb.png")));

        fromLengthComboBox = new ComboBox<>();
        fromLengthComboBox.getItems().addAll("Metro", "Kilómetro", "Pulgada");
        fromLengthComboBox.setValue("Metro");

        toLengthComboBox = new ComboBox<>();
        toLengthComboBox.getItems().addAll("Kilómetro", "Metro", "Pulgada", "Milla");
        toLengthComboBox.setValue("Kilómetro");

        lengthTextField = new TextField();

        Button convertButton = new Button("Convertir");
        convertButton.setOnAction(e -> convertLength());

        Button closeButton = new Button("Cerrar APP");
        closeButton.setOnAction(e -> Platform.exit());

        Button mainAppButton = new Button("Menu APP");
        mainAppButton.setOnAction(e -> {
            primaryStage.close(); // Se cierra la ventana del Conversor de Longitud
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
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("De:"), 0, 0);
        gridPane.add(fromLengthComboBox, 1, 0);
        gridPane.add(new Label("A:"), 0, 1);
        gridPane.add(toLengthComboBox, 1, 1);
        gridPane.add(new Label("Longitud:"), 0, 2);
        gridPane.add(lengthTextField, 1, 2);
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

    private void convertLength() {
        String fromLength = fromLengthComboBox.getValue();
        String toLength = toLengthComboBox.getValue();
        String lengthText = lengthTextField.getText();

        if (lengthText.isEmpty()) {
            showAlert("No se ha ingresado la cantidad a convertir");
            return;
        }

        try {
            double length = Double.parseDouble(lengthTextField.getText());

            double convertedLength;
            if (fromLength.equals("Metro") && toLength.equals("Kilómetro")) {
                convertedLength = length / 1000;
            } else if (fromLength.equals("Metro") && toLength.equals("Pulgada")) {
                convertedLength = length * 39.37;
            } else if (fromLength.equals("Kilómetro") && toLength.equals("Metro")) {
                convertedLength = length * 1000;
            } else if (fromLength.equals("Kilómetro") && toLength.equals("Milla")) {
                convertedLength = length * 0.621371;
            } else if (fromLength.equals("Pulgada") && toLength.equals("Metro")) {
                convertedLength = length / 39.37;
            } else if (fromLength.equals("Pulgada") && toLength.equals("Milla")) {
                convertedLength = length / 63360;
            } else {
                convertedLength = length;
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            resultTextArea.setText(decimalFormat.format(length) + " " + fromLength + " = "
                    + decimalFormat.format(convertedLength) + " " + toLength);
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
