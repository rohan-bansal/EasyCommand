package EasyCommand;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import java.io.IOException;

// Command newButton = new Command(btn1, "gedit", operating_system);
// newButton.activateCommand();

public class Command {

    private String command;
    private Button btn;
    private String btnText;
    private String OpSys;
    private String description;
    private String[] args;


    public Command(Button btn, String command, String description, String OS) {
        this.btn = btn;
        this.command = command;
        this.OpSys = OS;
        this.btnText = btn.getText();
        this.description = description;

        this.btn.setOnAction(e -> reRoute());
        this.btn.setTooltip(new Tooltip(this.description));
    }

    private void reRoute() {
        try {
            activateCommand();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void activateCommand() throws IOException {

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

    public void runWindows() throws IOException {
        args = new String[] {"cmd", "/c start", command};
        Process proc = new ProcessBuilder(args).start();

        btn.setText(btnText);
    }

    public void runMac() throws IOException {
        args = new String[] {"/bin/bash", "-c", command};
        Process proc = new ProcessBuilder(args).start();

        btn.setText(btnText);
    }

    public void runLinux() throws IOException {
        args = new String[] {"/bin/bash", "-c", command};
        Process proc = new ProcessBuilder(args).start();

        btn.setText(btnText);

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
