/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class newinterface {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Frame");
        JButton fn = new JButton("first number");
        fn.setBounds(50, 30, 80, 50);
        frame.add(fn);
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
