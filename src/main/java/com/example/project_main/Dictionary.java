package com.example.project_main;

import java.util.TreeSet;

/**
 *
 * @author Admin
 */
public class Dictionary {

    public TreeSet<Word> words;

    public Dictionary() {
        words = new TreeSet<>();
    }

    public TreeSet<Word> getWords() {
        return words;
    }
}