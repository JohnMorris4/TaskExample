package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;


public class Controller {
    private Task<ObservableList<String>> task;
    @FXML
    private ListView listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label pbLabel;

    public void initialize() {
        task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                String[] names = {"John Morris",
                        "Jennifer Farmer",
                        "Ronnie Boykin",
                        "Jerry",
                        "JOhn Dowd",
                        "Jim Green"
                };
//                Thread.sleep(100);
                ObservableList<String> employees = FXCollections.observableArrayList();
                       for (int i = 0; i<6; i++) {
                           employees.add(names[i]);
                           updateMessage("Added " + names[i] + " To the list");
                           updateProgress(i + 1, 6);
                           Thread.sleep(2000);
                       }
                return employees;
            }
        };
        progressBar.progressProperty().bind(task.progressProperty());
        pbLabel.textProperty().bind(task.messageProperty());
        listView.itemsProperty().bind(task.valueProperty());
    }
    @FXML
    public void buttonPressed() {
        new Thread(task).start();
    }
}
