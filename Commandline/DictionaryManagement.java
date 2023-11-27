/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DictionaryManagement {

    private Dictionary dc;
    private final File word;

    public DictionaryManagement() throws IOException {
        this.word = new File("C:/Users/Admin/Documents/NetBeansProjects/JavaApplication1/src/project/ntd_dictionaries.txt");\
        Trie trie = new Trie();
        // this.dc = new Dictionary();
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        while (n > 0) {
            System.out.print("Nhap tu tieng anh: ");
            String word_target = sc.nextLine();
            System.out.print("Nhap giai thich: ");
            String word_explain = sc.nextLine();
            Word s = new Word(word_target, word_explain);
            dc.words.add(s);
            n--;
        }
    }

    public void insertFromFile() throws FileNotFoundException {

        try (Scanner myReader = new Scanner(word)) {
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                String word_target = data[0];
                String word_explain = "";
                if (data.length >= 2) {

                    for (int i = 1; i < data.length; i++) {
                        word_explain = word_explain + data[i] + " ";
                    }
                    Word s = new Word(word_target, word_explain);
                    dc.words.add(s);
                }

            }
        }
    }

    public void dictionaryLookup() {
        System.out.print("Nhap tu can tra: ");
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
        int check = 0;
        for (Word i : dc.getWords()) {
            if (i.getWord_target().equals(word_target)) {
                String word_explain = i.getWord_explain();
                System.out.println("Nghia cua tu la: " + word_explain);
                check = 1;
                break;
            }
        }
        if (check == 0) {
            System.out.println("Tu nhap khong hop le ");
        }
    }

    public void dictionaryUpdate() {
        System.out.print("Nhap tu can sua: ");
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
        for (Word i : dc.getWords()) {
            if (i.getWord_target().equals(word_target)) {
                System.out.print("Nghia cua tu la: ");
                String word_explain = sc.nextLine();
                dc.words.remove(i);
                Word s = new Word(word_target, word_explain);
                dc.words.add(s);
                break;
            }
        }
    }

    public void dictionaryInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu tieng anh: ");
        String word_target = sc.nextLine();
        System.out.print("Nhap giai thich: ");
        String word_explain = sc.nextLine();
        Word s = new Word(word_target, word_explain);
        dc.words.add(s);
    }

    public void dictionaryEraser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu tieng anh muon xoa: ");
        String word_target = sc.nextLine();
        int check = 0;
        for (Word i : dc.getWords()) {
            if (i.getWord_target().equals(word_target)) {
                dc.words.remove(i);
                check = 1;
                break;
            }
        }
        if (check == 0) {
            System.out.println("khong co tu thoa man ");
        }
    }

    public void dictionaryExportToFile() throws IOException {
        FileWriter writer = new FileWriter(word);
        for (Word i : dc.getWords()) {
            String s = i.getWord_target() + " " + i.getWord_explain();
            writer.write(s);
            writer.write('\n');
        }
        writer.close();
    }

    public void game() {
        List<Integer> numbers = new ArrayList<>();
        Random generator = new Random();
        int value = generator.nextInt(4);
        for (int i = 0; i < dc.getWords().size(); i++) {
            numbers.add(i);
        }
        List<Word> list = new ArrayList<>();
        for(Word i: dc.getWords()) {
            list.add(i);
        }
        Collections.shuffle(numbers);

        List<Integer> x = numbers.subList(0, 4);
        String key_A, key_B, key_C, key_D, key_main;
        String value_A, value_B, value_C, value_D, value_main;
        key_A = list.get(x.get(0)).getWord_target();
        value_A = list.get(x.get(0)).getWord_explain();
        key_B = list.get(x.get(1)).getWord_target();
        value_B = list.get(x.get(1)).getWord_explain();
        key_C = list.get(x.get(2)).getWord_target();
        value_C = list.get(x.get(2)).getWord_explain();
        key_D = list.get(x.get(3)).getWord_target();
        value_D = list.get(x.get(3)).getWord_explain();
        
        key_main = list.get(x.get(value)).getWord_target();
        value_main = list.get(x.get(value)).getWord_explain();
        
        System.out.println("[" + key_main + "]" + " is maining: ");
        System.out.println("[A]  " + value_A);
        System.out.println("[B]  " + value_B);
        System.out.println("[C]  " + value_C);
        System.out.println("[D]  " + value_D);
        System.out.print("Your choice [A/B/C/D]: ");
        Scanner sc= new Scanner(System.in);
        String check  = sc.nextLine();
        if(check.equals("A") && value_A.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        } 
        else if(check.equals("B") && value_B.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        }
        else if(check.equals("C") && value_C.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        }
        else if(check.equals("D") && value_D.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        }
        else {
            System.out.println("you are Wrong!");
        }
        
    }

    public Dictionary getDc() {
        return dc;
    }
}
