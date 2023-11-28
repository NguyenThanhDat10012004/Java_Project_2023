/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.project_main.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Node {
    HashMap<Character, Node> children;
    boolean endOfWord;
    String meaning;

    public Node() {
        children = new HashMap<Character, Node>();
        endOfWord = false;
        meaning = "";
    }
}

public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    // insert word into trie
    public void insert(String word, String meaning) {
        Node head = root;
        for (Character i : word.toCharArray()) {
            head.children.putIfAbsent(i, new Node());
            head = head.children.get(i);
        }
        head.endOfWord = true;
        head.meaning = meaning;
    }

    // check if the word in the trie
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

    // get the meaning of the word
    public String getWordMeaning(String word) {
        String meaning = "";
        Node head = root;

        for (Character i : word.toCharArray()) {
            if (head.children.containsKey(i)) {
                head = head.children.get(i);
            } else {
                return "";
            }
        }
        if (head.endOfWord) {
            meaning = head.meaning;
        }
        return meaning;
    }

    public void setWordMeaning(String word, String meaning) {
        Node head = root;
        for (Character i : word.toCharArray()) {
            if (head.children.containsKey(i)) {
                head = head.children.get(i);
            } else {
                System.out.println("there is no word");
                return;
            }
        }
        if (head.endOfWord) {
            head.meaning = meaning;
        } else {
            System.out.println("there is no word");
            return;
        }
    }

    public void deleteWord(String word) {
        Node head = root;
        LinkedList<Node> path = new LinkedList<>();

        for (Character i : word.toCharArray()) {
            if (head.children.containsKey(i)) {
                path.push(head);
                head = head.children.get(i);
            } else {
                System.out.println("There is no word");
                return;
            }
        }

        if (head.endOfWord) {
            head.endOfWord = false;

            while (!path.isEmpty()) {
                Node current = path.pop();
                char lastChar = word.charAt(path.size());

                if (current.children.isEmpty() && !current.endOfWord) {
                    path.peek().children.remove(lastChar);
                } else {
                    break;
                }
            }
        } else {
            System.out.println("There is no word");
        }
    }

    // find words startWith word - return array of only words
    public List<String> startWith_W(String word) {
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

        collectWords_W(tmp, head, words);
        return words;
    }

    private void collectWords_W(StringBuilder tmp, Node head, List<String> words) {
        if (head.endOfWord) {
            words.add(tmp.toString());
        }
        for (char ch : head.children.keySet()) {
            tmp.append(ch);
            collectWords_W(tmp, head.children.get(ch), words);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }

    //find words startWith word - return array of words and meanings
    public List<Word> startWith_WM(String word) {
        List<Word> words = new ArrayList<>();
        Node head = root;
        StringBuilder tmp = new StringBuilder(word);

        for (char ch : word.toCharArray()) {
            if (head.children.containsKey(ch)) {
                head = head.children.get(ch);
            } else {
                return words;
            }
        }

        collectWords_WM(tmp, head, words);
        return words;
    }

    private void collectWords_WM(StringBuilder tmp, Node head, List<Word> words) {
        if (head.endOfWord) {
            words.add(new Word(tmp.toString(), head.meaning));
        }
        for (char ch : head.children.keySet()) {
            tmp.append(ch);
            collectWords_WM(tmp, head.children.get(ch), words);
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

        trie.setWordMeaning("data science", "a new part of data project");
        System.out.println(trie.startWith_W(""));
        System.out.println(trie.startWith_WM(""));
    }
}
