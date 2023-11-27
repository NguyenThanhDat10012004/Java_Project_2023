/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) throws IOException {
        DictionaryManagement md = new DictionaryManagement();
        DictionaryCommandline dc = new DictionaryCommandline(md.getTrie());
        dc.DictionaryCommandLine(md);
    }
}
