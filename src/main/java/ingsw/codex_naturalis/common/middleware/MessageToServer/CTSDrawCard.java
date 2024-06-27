package ingsw.codex_naturalis.common.middleware.MessageToServer;

import ingsw.codex_naturalis.server.ClientSkeleton;

public class CTSDrawCard implements MessageToServer {

    private String jsonDrawCardUpdate;
    /**
     * CTSDrawCard's constructor
     */
    public CTSDrawCard() {
    }
    /**
     * CTSDrawCard's constructor
     * @param jsonDrawCardUpdate : the card that the player wants to draw
     */
    public CTSDrawCard(String jsonDrawCardUpdate) {
        this.jsonDrawCardUpdate = jsonDrawCardUpdate;
    }
    /**
     * To run the clientSkeleton and send the message to the server that a player wants to draw a card
     */
    @Override
    public void run(ClientSkeleton clientSkeleton) {
        clientSkeleton.getGameControllerImpl().drawCard(clientSkeleton.getNickname(), jsonDrawCardUpdate);
    }
    /**
     * JsonDrawCardUpdate's getter
     * @return jsonDrawCardUpdate
     */
    public String getJsonDrawCardUpdate() {
        return jsonDrawCardUpdate;
    }
    /**
     * JsonDrawCardUpdate's setter
     * @param jsonDrawCardUpdate to set
     */
    public void setJsonDrawCardUpdate(String jsonDrawCardUpdate) {
        this.jsonDrawCardUpdate = jsonDrawCardUpdate;
    }
}
