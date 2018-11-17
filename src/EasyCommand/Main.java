package EasyCommand;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static String operating_system;

    private ArrayList<Command> comms = new ArrayList<>();


    private void refreshNodes(FlowPane center) {
        center.getChildren().clear();
        for(Command com : comms) {
            center.getChildren().add(com.getButton());
        }
    }

    private void infoWindow(FlowPane center) {
        GridPane newRoot = new GridPane();
        newRoot.setStyle("-fx-background-color: #5D6D7E");
        Stage stage = new Stage();

        newRoot.setAlignment(Pos.CENTER);
        newRoot.setHgap(10);
        newRoot.setVgap(10);

        Label initials = new Label("Summarize command in a few letters:");
        TextField initialsInput = new TextField();
        GridPane.setConstraints(initials, 0, 0);
        GridPane.setConstraints(initialsInput, 1, 0);

        Label command = new Label("Terminal Command:");
        TextField commandInput = new TextField();
        GridPane.setConstraints(command, 0, 1);
        GridPane.setConstraints(commandInput, 1, 1);

        Label desc = new Label("Description:");
        TextField descInput = new TextField();
        GridPane.setConstraints(desc, 0, 2);
        GridPane.setConstraints(descInput, 1, 2);

        Label cpL = new Label("Choose a color:");
        ColorPicker cp = new ColorPicker();
        GridPane.setConstraints(cpL, 0, 3);
        GridPane.setConstraints(cp, 1, 3);

        Button done = new Button("Finish");
        done.setStyle("-fx-background-color: #2ECC71");
        done.setOnAction(x -> obtainCommandInfo(initialsInput, commandInput, descInput, center, stage, cp));
        GridPane.setConstraints(done, 1, 4);

        newRoot.getChildren().addAll(initials, initialsInput, command, commandInput, desc, descInput, cpL, cp, done);

        stage.setTitle("Create a New Command");
        stage.setScene(new Scene(newRoot, 500, 350));
        stage.show();
    }

    private void obtainCommandInfo(TextField initials, TextField command, TextField desc, FlowPane center, Stage stage, ColorPicker cp) {
        stage.close();
        comms.add(new Command(new Button(initials.getText()), cp, command.getText(), desc.getText(), operating_system));
        refreshNodes(center);
    }

    private void modifyCommand() {

    }

    private void deleteCommand() {

    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #34495E");
        HBox top = new HBox();
        FlowPane center = new FlowPane();

        top.setAlignment(Pos.CENTER);
        top.setSpacing(10);
        top.setPadding(new Insets(10, 10, 10, 10));

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setHgap(10);
        center.setVgap(10);

        root.setCenter(center);
        root.setTop(top);

        Button newC = new Button();
        newC.setStyle("-fx-background-color: #1E8449");
        newC.setText("New Command");
        newC.setOnAction(e -> infoWindow(center));

        Button modC = new Button();
        modC.setStyle("-fx-background-color: #F1C40F ");
        modC.setText("Modify Command");
        modC.setOnAction(m -> modifyCommand());

        Button delC = new Button();
        delC.setStyle("-fx-background-color: #E74C3C");
        delC.setText("Delete Command");
        delC.setOnAction(d -> deleteCommand());

        top.getChildren().addAll(newC, modC, delC);

        primaryStage.setTitle("EasyCommand");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            operating_system = "Windows";
        } else if (OS.contains("mac")) {
            operating_system = "Mac";
        } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            operating_system = "Linux";
        }

        launch(args);
    }
}
