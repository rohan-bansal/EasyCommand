package EasyCommand;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Command {

    private String command;
    private Button btn;
    private String btnText;
    private String OpSys;
    private String description;
    private String[] args;


    Command(Button btn, ColorPicker color, String command, String description, String OS) {
        this.btn = btn;
        this.command = command;
        this.OpSys = OS;
        this.btnText = btn.getText();
        this.description = description;

        this.btn.setOnAction(e -> reRoute());
        this.btn.setStyle("-fx-background-color: " + color.getValue().toString().replace("0x", "#"));
        this.btn.setTooltip(new Tooltip(this.description));
    }

    private void reRoute() {
        try {
            activateCommand();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void activateCommand() throws Exception {

        btn.setText("Running");

        switch(OpSys) {
            case "Windows":
                runWindows();
                break;
            case "Mac":
                runMac();
                break;
            case "Linux":
                runLinux();
                break;
        }
    }

    private void runWindows() throws Exception {
        args = new String[] {"cmd", "/c start", command};
        Process proc = new ProcessBuilder(args).start();

        readOutput(proc);

        btn.setText(btnText);
    }

    private void runMac() throws Exception {
        args = new String[] {"/bin/bash", "-c", command};
        Process proc = new ProcessBuilder(args).start();

        readOutput(proc);

        btn.setText(btnText);
    }

    private void runLinux() throws Exception {
        args = new String[] {"/bin/bash", "-c", command};
        Process proc = new ProcessBuilder(args).start();

        readOutput(proc);

        btn.setText(btnText);

    }

    private void readOutput(Process proc) throws Exception {

        GridPane newRoot = new GridPane();
        newRoot.setStyle("-fx-background-color: #ABB2B9");
        Stage stage = new Stage();

        newRoot.setHgap(10);
        newRoot.setVgap(10);

        TextArea outputField = new TextArea();
        outputField.setStyle("-fx-control-inner-background: #222C36; -fx-font-family: Consolas; -fx-highlight-text-fill: #860202; -fx-text-fill: #036E00; -fx-border-color: red ;");
        outputField.setPrefWidth(600);
        outputField.setPrefHeight(1000);
        GridPane.setConstraints(outputField, 0, 0);
        GridPane.setHgrow(outputField, Priority.ALWAYS);
        GridPane.setVgrow(outputField, Priority.ALWAYS);

        newRoot.getChildren().add(outputField);

        stage.setTitle("Terminal Output");
        stage.setScene(new Scene(newRoot, 600, 400));
        stage.show();

        ArrayList<String> output = new ArrayList<>();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.add(line + "\n");
            outputField.setText(String.join("", output));
        }

        proc.waitFor();
    }

    public String getCommand() {
        return command;
    }

    public void modifyCommand(String command) {
        this.command = command;
    }

    public Button getButton() {
        return btn;
    }
}
