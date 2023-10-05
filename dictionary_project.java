/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author Admin
 */
public class dictionary_project {

    public static class Word implements Comparable<Word> {

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

    public static class Dictionary {

        public TreeSet<Word> words;

        public Dictionary() {
            words = new TreeSet<>();
        }

        public TreeSet<Word> getWords() {
            return words;
        }

    }

    public static class DictionaryManagement {

        private Dictionary dc;
        private final File word;

        public DictionaryManagement() throws IOException {
            this.word = new File("C:/Users/Admin/Documents/NetBeansProjects/JavaApplication1/src/javaapplication1/ntd_dictionaries.txt");
            this.dc = new Dictionary();
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

        public Dictionary getDc() {
            return dc;
        }

    }

    public static class DictionaryCommandline {

        private Dictionary dc;

        public DictionaryCommandline(Dictionary dc) {
            this.dc = dc;
        }

        public void showAllWords() {
            int num = 0;
            System.out.printf("%-15s", "No");
            System.out.printf("|");
            System.out.printf("%-15s", "English");
            System.out.printf("|");
            System.out.printf("%-15s", "Vietnamese");
            System.out.printf("%s\n", "|");

            for (Word i : dc.getWords()) {
                int idx = num + 1;
                System.out.printf("%-15d", idx);
                System.out.printf("|");
                System.out.printf("%-15s", i.getWord_target());
                System.out.printf("|");
                System.out.printf("%-15s", i.getWord_explain());
                System.out.printf("%s\n", "|");
                num++;
            }
        }
        public void dictionarySearcher()
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap vao tu can tra: ");
            String word = sc.nextLine();
            int  dem = 0;
            for (Word i : dc.getWords()) {
                if(i.getWord_target().indexOf(word) > -1)
                {
                    dem++;
                    System.out.println(dem + ": " + i.getWord_target());
                }
            }
            
        }

        public void DictionaryCommandLine(DictionaryManagement md) throws FileNotFoundException, IOException {
            while (true) {
                System.out.println("Welcome to My Application!");
                System.out.println("[0] Exit");
                System.out.println("[1] Add");
                System.out.println("[2] Remove");
                System.out.println("[3] Update");
                System.out.println("[4] Display");
                System.out.println("[5] Lookup");
                System.out.println("[6] Search");
                System.out.println("[7] Game");
                System.out.println("[8] Import from file");
                System.out.println("[9] Export to file");
                System.out.print("Your action: ");
                Scanner sc = new Scanner(System.in);
                int x = Integer.parseInt(sc.nextLine());
                if (x == 0) {
                    break;
                }
                else if(x == 1)
                {
                    md.dictionaryInsert();
                }
                else if(x == 2)
                {
                    md.dictionaryEraser();
                }
                else if(x == 3)
                {
                    md.dictionaryUpdate();
                }
                else if(x == 4)
                {
                    showAllWords();
                }
                else if(x == 5)
                {
                    md.dictionaryLookup();
                }
                else if(x == 6)
                {
                    dictionarySearcher();
                }
                else if(x == 7)
                {
                    
                }
                else if(x == 8)
                {
                    md.insertFromFile();
                }
                else if(x == 9)
                {
                    md.dictionaryExportToFile();
                }
                else
                {
                    System.out.println("Action not supported.");
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        DictionaryManagement md = new DictionaryManagement();
        DictionaryCommandline dc = new DictionaryCommandline(md.getDc());
        dc.DictionaryCommandLine(md);
    }
}
