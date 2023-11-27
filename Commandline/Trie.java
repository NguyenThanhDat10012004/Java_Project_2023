/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Node {
    HashMap<Character,Node> children;
    boolean endOfWord;
    String meaning;

    public Node() {
        children = new HashMap<Character,Node>();
        endOfWord = false;
        meaning = "";
    }
}
public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    //insert word into trie
    public void insert(String word, String meaning) {
        Node head = root;
        for (Character i : word.toCharArray()) {
            head.children.putIfAbsent(i, new Node());
            head = head.children.get(i);
        }
        head.endOfWord = true;
        head.meaning = meaning;
    }

    //check if the word in the trie
    public boolean search(String word) {
        Node head = root;
        for (Character i : word.toCharArray()) {
            if (head.children.containsKey(i)) {
                head = head.children.get(i);
            } else {
                return false;
            }
        }
        return head.endOfWord;
    }

    //get the meaning of the word
    public String getWordMeaning(String word) {
        String meaning = "";
        Node head = root;

        for (Character i : word.toCharArray()) {
            if (head.children.containsKey(i)) {
                head = head.children.get(i);
            } else {
                return "Word not found!";
            }
        }
        if (head.endOfWord) {
            meaning = head.meaning;
        }
        return meaning;
    }

    //find words startWith word - return array of words 
    public List<String> startWith(String word) {
        List<String> words = new ArrayList<>();
        Node head = root;
        StringBuilder tmp = new StringBuilder(word);
    
        for (char ch : word.toCharArray()) {
            if (head.children.containsKey(ch)) {
                head = head.children.get(ch);
            } else {
                return words;
            }
        }
    
        collectWords(tmp, head, words);
        return words;
    }
    
    private void collectWords(StringBuilder tmp, Node head, List<String> words) {
        if (head.endOfWord) {
            words.add(tmp.toString());
        }
        for (char ch : head.children.keySet()) {
            tmp.append(ch);
            collectWords(tmp, head.children.get(ch), words);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }
    
    

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("data science", "a part of data project");
        trie.insert("data structure", "data structure");
        trie.insert("computer science", "computer science");
        trie.insert("data analysis", "data analysis");
        trie.insert("datamine", "datamine");
        trie.insert("datonine", "datonine");
        
        System.out.println(trie.startWith("data"));
    }
}
