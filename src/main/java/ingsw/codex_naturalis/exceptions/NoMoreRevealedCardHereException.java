package ingsw.codex_naturalis.exceptions;

public class NoMoreRevealedCardHereException extends RuntimeException{

    public NoMoreRevealedCardHereException(){
        super("There isn't a revealed card here, pick the other one!");
    }
}
