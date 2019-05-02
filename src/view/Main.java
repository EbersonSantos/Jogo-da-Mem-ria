/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.Random;
import model.Tabuleiro;

/**
 *
 * @author Eberson
 */
public class Main {
    public static void main(String[] args) {
        Tabuleiro t = new Tabuleiro("1200120012001200");
        t.printMatriz();
        String s = "0921";
        String letra = s.substring(0, 1);
        System.out.println(letra);
       
    }
           
}
