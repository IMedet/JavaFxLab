import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MileKilometerConverter extends Application {

    private TextField kilometerField;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(5);

        Label mileLabel = new Label("Mile:");
        TextField mileField = new TextField();
        mileField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    double miles = Double.parseDouble(mileField.getText());
                    double kilometers = miles * 1.60934;
                    kilometerField.setText(String.format("%.2f", kilometers));
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    kilometerField.setText("Invalid input");
                }
            }
        });

        Label kilometerLabel = new Label("Kilometer:");
        kilometerField = new TextField(); // Initialize kilometerField
        kilometerField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    double kilometers = Double.parseDouble(kilometerField.getText());
                    double miles = kilometers / 1.60934;
                    mileField.setText(String.format("%.2f", miles));
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    mileField.setText("Invalid input");
                }
            }
        });

        root.add(mileLabel, 0, 0);
        root.add(mileField, 1, 0);
        root.add(kilometerLabel, 0, 1);
        root.add(kilometerField, 1, 1);

        Scene scene = new Scene(root, 300, 100);
        primaryStage.setTitle("Mile/Kilometer Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
