import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardViewerApp extends Application {

    private static final int CARD_COUNT = 52;
    private static final int DISPLAY_COUNT = 4;
    private static final String CARD_DIR = "cards";

    private final Random rng = new Random();
    private final List<ImageView> slots = new ArrayList<>();
    private Set<Integer> currentHand = new HashSet<>();

    @Override
    public void start(Stage stage) {
        // Row of 4 card ImageViews
        HBox cardRow = new HBox(15);
        cardRow.setAlignment(Pos.CENTER);
        cardRow.setPadding(new Insets(15));

        for (int i = 0; i < DISPLAY_COUNT; i++) {
            ImageView iv = new ImageView();
            iv.setFitWidth(120);     
            iv.setPreserveRatio(true);
            slots.add(iv);
            cardRow.getChildren().add(iv);
        }

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> dealNewHand()); 

        VBox root = new VBox(15, cardRow, refreshBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));

        dealNewHand();

        stage.setTitle("Random 4-Card Viewer");
        stage.setScene(new Scene(root, 650, 320));
        stage.show();
    }

    private void dealNewHand() {
        ensureCardsFolderExists();

        Set<Integer> newHand;
        do {
            newHand = drawHand();
        } while (newHand.equals(currentHand)); 

        currentHand = newHand;

        int index = 0;
        for (int cardNumber : newHand) {
            Image img = loadCardImage(cardNumber);
            slots.get(index).setImage(img);
            index++;
        }
    }

    private Set<Integer> drawHand() {
        List<Integer> deck = IntStream.rangeClosed(1, CARD_COUNT)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(deck, rng);
        return new LinkedHashSet<>(deck.subList(0, DISPLAY_COUNT)); 
    }

    private Image loadCardImage(int n) {
        File file = new File(CARD_DIR + File.separator + n + ".png");
        
        return new Image(file.toURI().toString());
    }

    private void ensureCardsFolderExists() {
        File dir = new File(CARD_DIR);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalStateException(
                    "Missing '" + CARD_DIR + "' folder. Create it next to where you run the program, and put 1.png..52.png inside."
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
