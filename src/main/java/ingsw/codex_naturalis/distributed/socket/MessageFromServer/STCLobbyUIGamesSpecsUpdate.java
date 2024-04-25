package ingsw.codex_naturalis.distributed.socket.MessageFromServer;

import ingsw.codex_naturalis.distributed.Client;

import java.io.BufferedReader;
import java.io.IOException;

public class STCLobbyUIGamesSpecsUpdate implements MessageFromServer{
    @Override
    public void run(Client client, BufferedReader reader) {
        try {
            String jsonGamesSpecs = reader.readLine();
            client.stcUpdateLobbyUIGameSpecs(jsonGamesSpecs);
        } catch (IOException e) {
            System.err.println("Error while receiving from server");
        }
    }
}
