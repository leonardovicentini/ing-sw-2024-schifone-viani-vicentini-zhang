package ingsw.codex_naturalis.distributed.socket.MessageFromServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ingsw.codex_naturalis.distributed.Client;
import ingsw.codex_naturalis.view.UI;

import java.io.BufferedReader;
import java.io.IOException;

public class STCUIUpdate implements MessageFromServer{

    @Override
    public void run(Client client, BufferedReader reader) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonUpdateUI = reader.readLine();
            client.stcUpdateUI(jsonUpdateUI);
        } catch (IOException e) {
            System.err.println("Error while receiving from server");
        }
    }
}
