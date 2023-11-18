package com.example.project_main.MemoryGame;

public class Card {
    private String wordCard;
    private String keyCard;

    public Card(String wordCard, String keyCard) {
        this.wordCard = wordCard.toLowerCase();
        this.keyCard = keyCard.toLowerCase();
    }

    public String getWordCard() {
        return wordCard;
    }

    public void setWordCard(String wordCard) {
        this.wordCard = wordCard;
    }

    public String getKeyCard() {
        return keyCard;
    }

    public void setKeyCard(String keyCard) {
        this.keyCard = keyCard;
    }

    @Override
    public String toString() {
        return wordCard + " : " + keyCard;
    }

}
