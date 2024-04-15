package ingsw.codex_naturalis.view.setupPhase;

import ingsw.codex_naturalis.controller.setupPhase.SetupObserver;
import ingsw.codex_naturalis.enumerations.Color;

import java.util.ArrayList;
import java.util.List;

public class SetupObservable {

    private final List<SetupObserver> obs;


    public SetupObservable() {
        obs = new ArrayList<>();
    }


    public synchronized void addObserver(SetupObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    public synchronized void deleteObservers() {
        obs.clear();
    }



    public void notifyReady() {
        for (SetupObserver o : obs){
            o.updateReady();
        }
    }

    public void notifyInitialCard(InitialCardEvent initialCardEvent) {
        for (SetupObserver o : obs){
            o.updateInitialCard(initialCardEvent);
        }
    }

    public void notifyColor(Color color) {
        for (SetupObserver o : obs){
            o.updateColor(color);
        }
    }

    public void notifyObjectiveCardChoice(ObjectiveCardChoice objectiveCardChoice) {
        for (SetupObserver o : obs){
            o.updateObjectiveCardChoice(objectiveCardChoice);
        }
    }


}
