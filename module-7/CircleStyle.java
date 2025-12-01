import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Root: horizontal layout
        HBox root = new HBox(10);
        root.setPadding(new Insets(20));

        // Left tall box with border and first white circle
        StackPane boxedCirclePane = new StackPane();
        boxedCirclePane.getStyleClass().add("border");
        boxedCirclePane.setPrefSize(70, 180); // tall rectangle

        Circle c1 = createPlainCircle(); // first white circle
        boxedCirclePane.getChildren().add(c1);

        // Remaining circles in a row to the right
        HBox circleRow = new HBox(10);

        Circle c2 = createPlainCircle(); // second white circle
        Circle c3 = createPlainCircle();
        Circle c4 = createPlainCircle();

        c3.setId("redcircle");
        c4.setId("greencircle");

        circleRow.getChildren().addAll(c2, c3, c4);

        // Add left box + right row to root
        root.getChildren().addAll(boxedCirclePane, circleRow);

        Scene scene = new Scene(root, 500, 220);
        scene.getStylesheets().add(
                getClass().getResource("circlestyles.css").toExternalForm()
        );

        stage.setTitle("Circle Style");
        stage.setScene(scene);
        stage.show();
    }

    static Circle createPlainCircle() {
        Circle circle = new Circle(40); // a bit smaller so it fits in the box
        circle.getStyleClass().addAll("plaincircle", "circleborder");
        return circle;
    }
}
