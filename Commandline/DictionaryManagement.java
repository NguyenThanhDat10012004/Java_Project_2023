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

    // private Dictionary dc;
    private final File word;
    private Trie trie = new Trie();

    public DictionaryManagement() throws IOException {
        this.word = new File("D:\\Code\\Java_Pro\\forOasis5\\solution3\\ex1\\src\\ntd_dictionaries.txt");
        if (!word.exists()) {
            System.out.println("no file");
        }
        
    }

    public void insertFromCommandline(Scanner tmp_sc) {
        tmp_sc.nextInt();
        int n = Integer.parseInt(tmp_sc.nextLine());
        while (n > 0) {
            tmp_sc.nextLine();
            System.out.print("Nhap tu tieng anh: ");
            String word_target = tmp_sc.nextLine();
            System.out.print("Nhap giai thich: ");
            String word_explain = tmp_sc.nextLine();
            
            trie.insert(word_target, word_explain);
            n--;
        }

    }

    public void insertFromFile() throws FileNotFoundException {

        try {
            Scanner myReader = new Scanner(word);

            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                String word_target = data[0];
                String word_explain = "";
                if (data.length >= 2) {

                    for (int i = 1; i < data.length; i++) {
                        word_explain = word_explain + data[i] + " ";
                    }
                    trie.insert(word_target, word_explain);
                }
            }
        } catch (Exception e) {
            System.out.println("error insertFile");
        }
    }

    public void dictionaryLookup(Scanner tmp_sc) {
        System.out.print("Nhap tu can tra: ");
        tmp_sc.nextLine();
        String word_target = tmp_sc.nextLine();
        System.out.println(trie.getWordMeaning(word_target));

    }

    public void dictionaryUpdate(Scanner tmp_sc) {
        System.out.print("Nhap tu can sua: ");
        tmp_sc.nextLine();
        String word_target = tmp_sc.nextLine();
        System.out.print("Nhap y nghia: ");

        if (trie.search(word_target)) {
            String word_explain = tmp_sc.nextLine();
            trie.setWordMeaning(word_target, word_explain);
        }

    }

    public void dictionaryInsert(Scanner tmp_sc) {
        tmp_sc.nextLine();
        System.out.print("Nhap tu tieng anh: ");
        String word_target = tmp_sc.nextLine();
        System.out.print("Nhap giai thich: ");
        String word_explain = tmp_sc.nextLine();
        Word s = new Word(word_target, word_explain);
        trie.insert(word_target, word_explain);

    }

    public void dictionaryEraser(Scanner tmp_sc) {
        System.out.print("Nhap tu tieng anh muon xoa: ");
        tmp_sc.nextLine();
        String word_target = tmp_sc.nextLine();
        trie.deleteWord(word_target);

    }

    public void dictionaryExportToFile() throws IOException {
        FileWriter writer = new FileWriter(word);
        List<Word> words = trie.startWith_WM("");
        for (Word i : words) {
            String s = i.getWord_target() + " " + i.getWord_explain();
            writer.write(s + "\n");
        }
        writer.close();
    }

    public void game(Scanner tmp_sc) {
        List<Integer> numbers = new ArrayList<>();
        List<Word> words = trie.startWith_WM("");

        if (words.size() < 5) {
            System.out.println("There is not enough Words in library!");
            return;
        }

        Random generator = new Random();
        int value = generator.nextInt(4);
        for (int i = 0; i < words.size(); i++) {
            numbers.add(i);
        }
        List<Word> list = new ArrayList<>();
        for (Word i : words) {
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

        System.out.println("[" + key_main + "]" + " is meaning: ");
        System.out.println("[A]  " + value_A);
        System.out.println("[B]  " + value_B);
        System.out.println("[C]  " + value_C);
        System.out.println("[D]  " + value_D);
        System.out.print("Your choice [A/B/C/D]: ");

        // Consume the newline character left in the buffer
        tmp_sc.nextLine();

        String check = tmp_sc.nextLine();

        if (check.equals("A") && value_A.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        } else if (check.equals("B") && value_B.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        } else if (check.equals("C") && value_C.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        } else if (check.equals("D") && value_D.equals(value_main)) {
            System.out.println("Congratulate you are Correct!");
        } else {
            System.out.println("you are Wrong!");
        }
    }

    public Trie getTrie() {
        return trie;
    }
}
