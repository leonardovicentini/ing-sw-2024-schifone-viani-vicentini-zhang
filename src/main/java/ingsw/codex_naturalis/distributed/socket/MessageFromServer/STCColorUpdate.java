package ingsw.codex_naturalis.distributed.socket.MessageFromServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ingsw.codex_naturalis.distributed.Client;

import java.io.BufferedReader;
import java.io.IOException;

public class STCColorUpdate implements MessageFromServer {

    @Override
    public void run(Client client, BufferedReader reader) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonColor = reader.readLine();
            client.stcUpdateSetupUIColor(jsonColor);
        } catch (IOException e) {
            System.err.println("Error while processing json");
        }
    }

}
