package ingsw.codex_naturalis.distributed.socket.MessageFromClient;

import ingsw.codex_naturalis.distributed.Client;
import ingsw.codex_naturalis.distributed.Server;

import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class CTSPlayCardUpdate implements MessageFromClient{
    @Override
    public void run(Client client, Server server, BufferedReader reader) {
        try {
            String jsonPlayCardUpdate = reader.readLine();
            int x = parseInt(reader.readLine());
            int y = parseInt(reader.readLine());
            server.ctsUpdatePlayCard(client, jsonPlayCardUpdate, x, y);
        } catch (IOException e) {
            System.err.println("Error while processing json" + e.getMessage());
        }
    }
}
