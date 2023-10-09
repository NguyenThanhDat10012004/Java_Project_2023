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
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DictionaryManagement {

    private Dictionary dc;
        private final File word;

        public DictionaryManagement() throws IOException {
            this.word = new File("C:/Users/Admin/Documents/NetBeansProjects/JavaApplication1/src/project/ntd_dictionaries.txt");
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
