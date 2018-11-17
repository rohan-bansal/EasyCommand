package EasyCommand;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
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
        Stage stage = new Stage();

        newRoot.setAlignment(Pos.CENTER);
        newRoot.setHgap(10);
        newRoot.setVgap(10);

        Label initials = new Label("Summarize command in two letters:");
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

        Button done = new Button("Finish");
        done.setOnAction(x -> obtainCommandInfo(initialsInput, commandInput, descInput, center, stage));
        GridPane.setConstraints(done, 1, 4);

        newRoot.getChildren().addAll(initials, initialsInput, command, commandInput, desc, descInput, done);

        stage.setTitle("Create a New Command");
        stage.setScene(new Scene(newRoot, 500, 350));
        stage.show();
    }

    void obtainCommandInfo(TextField initials, TextField command, TextField desc, FlowPane center, Stage stage) {
        stage.close();
        comms.add(new Command(new Button(initials.getText()), command.getText(), desc.getText(), operating_system));
        refreshNodes(center);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        HBox top = new HBox();
        FlowPane center = new FlowPane();

        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10, 10, 10, 10));

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setHgap(10);
        center.setVgap(10);

        root.setCenter(center);
        root.setTop(top);

        Button newC = new Button();
        newC.setText("New Command");
        newC.setOnAction(e -> infoWindow(center));

        top.getChildren().add(newC);

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
