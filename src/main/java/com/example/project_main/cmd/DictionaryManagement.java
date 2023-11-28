package com.example.project_main.cmd;

import com.example.project_main.ControllerMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DictionaryManagement extends ControllerMain {

    private Trie dc;
    private final File word;
    private final File Favor;

    public DictionaryManagement() throws IOException {
        this.word = new File("D:/Code/Java_Pro/Java_Project_2023/src/main/resources/View/eng_vie.txt");
        this.Favor = new File("D:/Code/Java_Pro/Java_Project_2023/src/main/resources/View/Favor.txt");
        this.dc = new Trie();
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        while (n > 0) {
            System.out.print("Nhap tu tieng anh: ");
            String word_target = sc.nextLine();
            System.out.print("Nhap giai thich: ");
            String word_explain = sc.nextLine();
            dc.insert(word_target, word_explain);
            n--;
        }
    }

    public void insertFromFile() throws FileNotFoundException {
        try (Scanner myReader = new Scanner(word)) {
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("<html>");
                String word_target = data[0];
                String word_explain = "<html>" + data[1];
                dc.insert(word_target, word_explain);
            }
        }
    }
    public void insertFromFileFavor() throws FileNotFoundException {

        try (Scanner myReader = new Scanner(Favor)) {
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("<html>");
                String word_target = data[0];
                String word_explain = "<html>" + data[1];
                dc.insert(word_target, word_explain);
            }
        }
    }

    public void dictionaryLookup() {
        System.out.print("Nhap tu can tra: ");
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
        System.out.println(dc.getWordMeaning(word_target));
    }

    public void dictionaryUpdate(String word_target, String maining) {
        dc.setWordMeaning(word_target, maining);
    }

    public void dictionaryInsert(String word_target, String word_explain) {
        dc.insert(word_target, word_explain);
    }

    public void dictionaryEraser(String word_target) throws IOException {
        dc.deleteWord(word_target);
    }

    public void dictionaryExportToFile() throws IOException {
        FileWriter writer = new FileWriter(word);
        List<Word> words = dc.startWith_WM("");
        for (Word i : words) {
            String s = i.getWord_target() + " " + i.getWord_explain();
            writer.write(s + "\n");
        }
        writer.close();
    }


    public Trie getDc() {
        return dc;
    }

    public void dictionaryExportToFileFavor() throws IOException {
        FileWriter writer = new FileWriter(Favor);
        List<Word> words = dc.startWith_WM("");
        for (Word i : words) {
            String s = i.getWord_target() + " " + i.getWord_explain();
            writer.write(s + "\n");
        }
        writer.close();
    }
}
