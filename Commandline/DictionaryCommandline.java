/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DictionaryCommandline {

    private Trie dc;

    public DictionaryCommandline(Trie dc) {
        this.dc = dc;
    }

    public void showAllWords() {
        int num = 0;
        int max_target = 7;
        List<Word> words = dc.startWith_WM("");
        int max_num = words.size();

        if (max_num == 0) {
            System.out.println("No words found");
        }

        int max_explain = 10;
        for (Word i : words) {
            max_target = Math.max(max_target, i.getWord_target().length());
            max_target = Math.max(max_target, i.getWord_explain().length());
        }
        System.out.printf("%-" + (max_num + 5) + "s", "No");
        System.out.printf("|");
        System.out.printf("%-" + (max_target + 10) + "s", "English");
        System.out.printf("|");
        System.out.printf("%-" + (max_target + 5) + "s", "Vietnamese");
        System.out.printf("%s\n", "|");

        for (Word i : words) {
            int idx = num + 1;
            System.out.printf("%-" + (max_num + 5) + "d", idx);
            System.out.printf("|");
            System.out.printf("%-" + (max_target + 10) + "s", i.getWord_target());
            System.out.printf("|");
            System.out.printf("%-" + (max_target + 5) + "s", i.getWord_explain());
            System.out.printf("%s\n", "|");
            num++;
        }
    }

    public void dictionarySearcher(Scanner sc) {
        List<Word> words = dc.startWith_WM("");
        System.out.print("Nhap vao tu can tra: ");
        sc.nextLine();
        String word = sc.nextLine();
        int dem = 0;
        for (Word i : words) {
            if (i.getWord_target().indexOf(word) > -1) {
                dem++;
                System.out.println(dem + ": " + i.getWord_target());
            }
        }
    }

    public void DictionaryCommandLine(DictionaryManagement md) throws FileNotFoundException, IOException {
        int x = -1;
        Scanner sc = new Scanner(System.in);
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

            x = sc.nextInt();

            if (x == 0) {
                break;
            } else if (x == 1) {
                System.out.println("Start_Function----------");
                md.dictionaryInsert(sc);
                System.out.println("End_Function----------");
            } else if (x == 2) {
                System.out.println("Start_Function----------");
                md.dictionaryEraser(sc);
                System.out.println("End_Function----------");
            } else if (x == 3) {
                System.out.println("Start_Function----------");
                md.dictionaryUpdate(sc);
                System.out.println("End_Function----------");
            } else if (x == 4) {
                System.out.println("Start_Function----------");
                showAllWords();
                System.out.println("End_Function----------");
            } else if (x == 5) {
                System.out.println("Start_Function----------");
                md.dictionaryLookup(sc);
                System.out.println("End_Function----------");
            } else if (x == 6) {
                System.out.println("Start_Function----------");
                dictionarySearcher(sc);
                System.out.println("End_Function----------");
            } else if (x == 7) {
                System.out.println("Start_Function----------");
                md.game(sc);
                System.out.println("End_Function----------");
            } else if (x == 8) {
                md.insertFromFile();
            } else if (x == 9) {
                md.dictionaryExportToFile();
            } else {
                System.out.println("Action not supported.");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sc.close();
    }
}
