package ingsw.codex_naturalis.view.gameplayPhase;

import ingsw.codex_naturalis.model.Game;

public abstract class GameplayUI extends GameplayObservable implements Runnable {

    public abstract void stop();

    public abstract void updatePlayerOrder(Game.Immutable immGame);

    public abstract void update(Game.Immutable immGame);

    public abstract void reportError(String error);
}
