package ingsw.codex_naturalis.client.view.gui;

import ingsw.codex_naturalis.common.immutableModel.GameSpecs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class LobbiesControllerFX implements Initializable {
    private GraphicalUI viewGUI;
    private HashMap<Integer,LobbyControllerFX> lobbiesFX;

    @FXML
    private Button CreateGame_BTN;

    @FXML
    private VBox LobbyContainer;

    @FXML
    private ChoiceBox<Integer> Players_CB;

    @FXML
    void createGame(ActionEvent event) {
        if(Players_CB.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select a number of players");
            alert.showAndWait();
            return;
        }
        CreateGame_BTN.setOnMouseClicked(actionEvent -> viewGUI.endLobbiesPhase(Players_CB.getValue()));
    }

    public void setViewGUI(GraphicalUI viewGUI) {
        this.viewGUI = viewGUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lobbiesFX = new HashMap<>();
        ObservableList<Integer> playerOptions = FXCollections.observableArrayList(2, 3, 4);
        Players_CB.setItems(playerOptions);
    }

    public void updateLobbies(List<GameSpecs> gamesSpecs) {
        LobbyContainer.getChildren().clear();
        createLobbies(gamesSpecs);
    }

    public void createLobbies(List<GameSpecs> lobbies) {
        for(GameSpecs lobby : lobbies) {
            if (lobby.currentNumOfPlayers()<=lobby.maxNumOfPlayers()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FXML/LobbyFXML.fxml"));
                try {
                    HBox lobbyHBox = fxmlLoader.load();
                    lobbyHBox.maxWidthProperty().bind(LobbyContainer.widthProperty());
                    lobbyHBox.minWidthProperty().bind(LobbyContainer.widthProperty());
                    LobbyControllerFX lobbyControllerFX = fxmlLoader.getController();
                    lobbyControllerFX.setViewGUI(viewGUI);
                    lobbiesFX.put(lobby.ID(), lobbyControllerFX);
                    lobbyControllerFX.setData(lobby);
                    LobbyContainer.getChildren().add(lobbyHBox);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
