package com.example.project_main;

public class Word implements Comparable<Word> {

    private final String word_target;
    private final String word_explain;

    Word(String word_target, String word_explain) {
        this.word_explain = word_explain;
        this.word_target = word_target;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    @Override
    public int compareTo(Word other) {
        // Compare Word objects based on their word_target property
        return this.word_target.compareTo(other.word_target);
    }
}
