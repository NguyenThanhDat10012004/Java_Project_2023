package com.example.project_main;

public class MemoryCard extends Card {
    private boolean check;

    public MemoryCard(String wordCard, String keyCard, boolean check) {
        super(wordCard, keyCard);
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isSameCard(MemoryCard other) {
        if (getKeyCard().equals(other.getWordCard())
                && getWordCard().equals(other.getKeyCard())) {
            return true;
        } else {
            return false;
        }
    }
}
