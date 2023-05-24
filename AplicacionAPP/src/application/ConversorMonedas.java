package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class ConversorMonedas extends Application {

    private static final Map<String, Double> CONVERSION_RATES;

    static {

        CONVERSION_RATES = new HashMap<>();
        // Agregar tasas de conversión reales aquí
        CONVERSION_RATES.put("PesosMX_Dólar", 0.055); // Año 2023
        CONVERSION_RATES.put("PesosMX_Euro", 0.051); // Año 2023
        CONVERSION_RATES.put("PesosMX_Libra", 0.045); // Año 2023
        CONVERSION_RATES.put("PesosMX_Yen", 7.73); // Año 2023
        CONVERSION_RATES.put("PesosMX_Won", 73.72); // Año 2023
        // Agregar tasas inversas
        CONVERSION_RATES.put("Dólar_PesosMX", 17.86); // Año 2023
        CONVERSION_RATES.put("Euro_PesosMX", 19.29); // Año 2023
        CONVERSION_RATES.put("Libra_PesosMX", 22.15); // Año 2023
        CONVERSION_RATES.put("Yen_PesosMX", 0.129); // Año 2023
        CONVERSION_RATES.put("Won_PesosMX", 0.013); // Año 2023
        
        // Agregar tasas de conversión reales aquí Dólar a Divisas
        CONVERSION_RATES.put("Dólar_PesosMX", 17.87); // Año 2023
        CONVERSION_RATES.put("Dólar_Euro", 0.92); // Año 2023
        CONVERSION_RATES.put("Dólar_Libra", 0.80); // Año 2023
        CONVERSION_RATES.put("Dólar_Yen", 138.34); // Año 2023
        CONVERSION_RATES.put("Dólar_Won", 1318.11); // Año 2023
        // Agregar tasas inversas
        CONVERSION_RATES.put("PesosMX_Dólar", 0.55); // Año 2023
        CONVERSION_RATES.put("Euro_Dólar", 1.07); // Año 2023
        CONVERSION_RATES.put("Libra_Dólar", 1.23); // Año 2023
        CONVERSION_RATES.put("Yen_Dólar", 0.007); // Año 2023
        CONVERSION_RATES.put("Won_Dólar", 0.00007); // Año 2023
        
        // Agregar tasas de conversión reales aquí Euro a Divisas
        CONVERSION_RATES.put("Euro_PesosMX", 19.27); // Año 2023
        CONVERSION_RATES.put("Euro_Dólar", 1.078); // Año 2023
        CONVERSION_RATES.put("Euro_Libra", 0.870); // Año 2023
        CONVERSION_RATES.put("Euro_Yen", 149.24); // Año 2023
        CONVERSION_RATES.put("Euro_Won", 1421.88); // Año 2023
        // Agregar tasas inversas
        CONVERSION_RATES.put("PesosMX_Euro", 0.051); // Año 2023
        CONVERSION_RATES.put("Dólar_Euro", 0.92); // Año 2023
        CONVERSION_RATES.put("Libra_Euro", 1.148); // Año 2023
        CONVERSION_RATES.put("Yen_Euro",  0.006); // Año 2023
        CONVERSION_RATES.put("Won_Euro", 0.0007); // Año 2023
        
        // Agregar tasas de conversión reales aquí Libra a Divisas
        CONVERSION_RATES.put("Libra_PesosMX", 22.14); // Año 2023
        CONVERSION_RATES.put("Libra_Dólar", 1.23); // Año 2023
        CONVERSION_RATES.put("Libra_Euro", 1.14); // Año 2023
        CONVERSION_RATES.put("Libra_Yen", 171.46); // Año 2023
        CONVERSION_RATES.put("Libra_Won", 1633.57); // Año 2023
        // Agregar tasas inversas
        CONVERSION_RATES.put("PesosMX_Libra", 0.045); // Año 2023
        CONVERSION_RATES.put("Dólar_Libra", 0.80); // Año 2023
        CONVERSION_RATES.put("Euro_Libra", 0.87); // Año 2023
        CONVERSION_RATES.put("Yen_Libra",  0.0058); // Año 2023
        CONVERSION_RATES.put("Won_Libra", 0.0006); // Año 2023
        
        // Agregar tasas de conversión reales aquí Yen a Divisas
        CONVERSION_RATES.put("Yen_PesosMX", 0.129); // Año 2023
        CONVERSION_RATES.put("Yen_Dólar", 0.0072); // Año 2023
        CONVERSION_RATES.put("Yen_Euro", 0.0066); // Año 2023
        CONVERSION_RATES.put("Yen_Libra", 0.0058); // Año 2023
        CONVERSION_RATES.put("Yen_Won", 9.53); // Año 2023
        // Agregar tasas inversas
        CONVERSION_RATES.put("PesosMX_Yen", 7.74); // Año 2023
        CONVERSION_RATES.put("Dólar_Yen", 138.42); // Año 2023
        CONVERSION_RATES.put("Euro_Yen", 149.25); // Año 2023
        CONVERSION_RATES.put("Libra_Yen", 171.49); // Año 2023
        CONVERSION_RATES.put("Won_Yen", 0.104); // Año 2023
        
        // Agregar tasas de conversión reales aquí Won a Divisas
        CONVERSION_RATES.put("Won_PesosMX", 0.013); // Año 2023
        CONVERSION_RATES.put("Won_Dólar", 0.0007); // Año 2023
        CONVERSION_RATES.put("Won_Euro", 0.0007); // Año 2023
        CONVERSION_RATES.put("Won_Libra", 0.00061); // Año 2023
        CONVERSION_RATES.put("Won_Yen", 0.104); // Año 2023
        // Agregar tasas inversas
        CONVERSION_RATES.put("PesosMX_Won", 73.79); // Año 2023
        CONVERSION_RATES.put("Dólar_Won", 1319.66); // Año 2023
        CONVERSION_RATES.put("Euro_Won", 1422.70); // Año 2023
        CONVERSION_RATES.put("Libra_Won", 1634.08); // Año 2023
        CONVERSION_RATES.put("Won_Yen", 9.53); // Año 2023
  }

    private ComboBox<String> fromCurrencyComboBox;
    private ComboBox<String> toCurrencyComboBox;
    private TextField amountTextField;
    private DecimalFormat decimalFormat;
    private TextArea resultTextArea;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(" -- Conversor de Monedas MX --");
        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/resources/icons_terminal_jb.png")));

        fromCurrencyComboBox = new ComboBox<>();
        fromCurrencyComboBox.getItems().addAll("PesosMX", "Dólar", "Euro", "Libra", "Yen", "Won");
        fromCurrencyComboBox.setValue("PesosMX");

        toCurrencyComboBox = new ComboBox<>();
        toCurrencyComboBox.getItems().addAll("Dólar", "Euro", "Libra", "Yen", "Won", "PesosMX");
        toCurrencyComboBox.setValue("Dólar");

        amountTextField = new TextField();

        Button convertButton = new Button("Convertir");
        convertButton.setOnAction(e -> convertCurrency());
        
        Button closeButton = new Button("Cerrar APP");
        closeButton.setOnAction(e -> primaryStage.close());
        
        Button mainAppButton = new Button("Menu APP");
        mainAppButton.setOnAction(e -> {
            Stage mainAppStage = new Stage();
            new MainApp().start(mainAppStage);
            primaryStage.close(); // Se cierra la ventana actual (Conversor de Monedas)
        });

        HBox buttonsContainer = new HBox(10);
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.getChildren().addAll(closeButton);

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
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("De:"), 0, 0);
        gridPane.add(fromCurrencyComboBox, 1, 0);
        gridPane.add(new Label("A:"), 0, 1);
        gridPane.add(toCurrencyComboBox, 1, 1);
        gridPane.add(new Label("Cantidad:"), 0, 2);
        gridPane.add(amountTextField, 1, 2);
        gridPane.add(convertButton, 1, 3);
        gridPane.add(mainAppButton, 1, 4);
        gridPane.add(closeButton, 1, 5);
        
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(logoContainer, gridPane, resultTextArea, buttonsContainer);


        Scene scene = new Scene(vbox, 350, 500);
        gridPane.setPadding(new Insets(0, 10, 10, 30));
        scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        decimalFormat = new DecimalFormat("#.##");
    }

    private void convertCurrency() {
        String fromCurrency = fromCurrencyComboBox.getValue();
        String toCurrency = toCurrencyComboBox.getValue();
        String amountText = amountTextField.getText();

        if (isValidNumber(amountText)) {
            double amount = Double.parseDouble(amountText);

            double convertedAmount;
            String conversionKey = fromCurrency + "_" + toCurrency;

            if (CONVERSION_RATES.containsKey(conversionKey)) {
                double conversionRate = CONVERSION_RATES.get(conversionKey);
                convertedAmount = amount * conversionRate;
            } else {
                convertedAmount = amount;
            }

            resultTextArea.setText(decimalFormat.format(amount) + " " + fromCurrency + " = "
                    + decimalFormat.format(convertedAmount) + " " + toCurrency);
        } else {
        	resultTextArea.setText("No se ha ingresado la cantidad a convertir");
        }
    }

    private boolean isValidNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

	public void show() {
		// TODO Auto-generated method stub
		
	}
}
