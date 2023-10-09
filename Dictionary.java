/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.HashSet;
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
