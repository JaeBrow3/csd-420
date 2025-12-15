package com.james.dev;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FanApp extends Application {

    private final FanDao dao = new FanDao();

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        TextField firstField = new TextField();
        TextField lastField = new TextField();
        TextField teamField = new TextField();

        idField.setPromptText("Enter ID (e.g., 1)");

        Label status = new Label();

        Button displayBtn = new Button("Display");
        Button updateBtn = new Button("Update");

        displayBtn.setOnAction(e -> {
            status.setText("");
            int id;
            try {
                id = Integer.parseInt(idField.getText().trim());
            } catch (NumberFormatException ex) {
                status.setText("ID must be a number.");
                return;
            }

            Fan fan = dao.getFanById(id);
            if (fan == null) {
                firstField.clear();
                lastField.clear();
                teamField.clear();
                status.setText("No fan found for ID " + id);
                return;
            }

            firstField.setText(fan.getFirstName());
            lastField.setText(fan.getLastName());
            teamField.setText(fan.getFavoriteTeam());
            status.setText("Loaded fan ID " + id);
        });

        updateBtn.setOnAction(e -> {
            status.setText("");

            int id;
            try {
                id = Integer.parseInt(idField.getText().trim());
            } catch (NumberFormatException ex) {
                status.setText("ID must be a number.");
                return;
            }

            String first = firstField.getText().trim();
            String last = lastField.getText().trim();
            String team = teamField.getText().trim();

            if (first.isEmpty() || last.isEmpty() || team.isEmpty()) {
                status.setText("First, Last, and Team cannot be blank.");
                return;
            }

            boolean ok = dao.updateFan(new Fan(id, first, last, team));
            status.setText(ok ? "Updated fan ID " + id : "Update failed (no row with ID " + id + ")");
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(12));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.addRow(0, new Label("ID:"), idField);
        grid.addRow(1, new Label("First Name:"), firstField);
        grid.addRow(2, new Label("Last Name:"), lastField);
        grid.addRow(3, new Label("Favorite Team:"), teamField);
        grid.addRow(4, displayBtn, updateBtn);
        grid.add(status, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 420, 260);
        stage.setTitle("Fan Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
