package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private Button currencyConverterButton;
    private Button lengthConverterButton;
    private Button temperatureConverterButton;
    private Button closeButton;

    private Stage primaryStage;
    private List<Stage> openStages;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("-- Mi Aplicación JavaFX --");

        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/resources/icons_terminal_jb.png")));

        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        VBox rightSection = new VBox();
        rightSection.setAlignment(Pos.CENTER);
        rightSection.setPadding(new Insets(10));
        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/application/resources/logo_jb3.png")));
        rightSection.getChildren().add(logoImageView);

        GridPane leftSection = new GridPane();
        leftSection.setHgap(10);
        leftSection.setVgap(10);
        leftSection.setAlignment(Pos.CENTER);

        openStages = new ArrayList<>();

        currencyConverterButton = createStyledButton("Conversor de Monedas");
        currencyConverterButton.setId("currencyConverterButton");
        currencyConverterButton.setOnAction(e -> {
            resetButtonState(currencyConverterButton);
            openOrActivateStage(ConversorMonedas.class);
            primaryStage.close(); // Cerrar la ventana principal
        });

        lengthConverterButton = createStyledButton("Conversor de Longitud");
        lengthConverterButton.setId("lengthConverterButton");
        lengthConverterButton.setOnAction(e -> {
            resetButtonState(lengthConverterButton);
            openOrActivateStage(ConversorLongitud.class);
            primaryStage.close(); // Cerrar la ventana principal
        });

        temperatureConverterButton = createStyledButton("Conversor de Temperatura");
        temperatureConverterButton.setId("temperatureConverterButton");
        temperatureConverterButton.setOnAction(e -> {
            resetButtonState(temperatureConverterButton);
            openOrActivateStage(ConversorTemperatura.class);
            primaryStage.close(); // Cerrar la ventana principal
        });

        closeButton = createStyledButton("Cerrar APP");
        closeButton.setId("closeButton");
        closeButton.setOnAction(e -> {
            resetButtonState(closeButton);
            closeOpenStages();
            Platform.exit();
        });

        setButtonSize(currencyConverterButton);
        setButtonSize(lengthConverterButton);
        setButtonSize(temperatureConverterButton);
        setButtonSize(closeButton);

        leftSection.add(currencyConverterButton, 0, 0);
        leftSection.add(lengthConverterButton, 0, 1);
        leftSection.add(temperatureConverterButton, 0, 2);
        leftSection.add(closeButton, 0, 3);

        root.getChildren().addAll(rightSection, leftSection);

        Scene scene = new Scene(root, 350, 500);
        scene.getStylesheets().add("file:src/application/styles.css");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        return button;
    }

    private void resetButtonState(Button button) {
        button.setDefaultButton(false);
        button.setDisable(false);
    }

    private void setButtonSize(Button button) {
        button.setPrefWidth(200);
        button.setPrefHeight(20);
    }

    private void openOrActivateStage(Class<? extends Application> appClass) {
        closeOpenStages();
        try {
            Application app = appClass.getDeclaredConstructor().newInstance();
            Stage stage = new Stage();
            openStages.add(stage);
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeOpenStages() {
        for (Stage stage : openStages) {
            stage.close();
        }
        openStages.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
