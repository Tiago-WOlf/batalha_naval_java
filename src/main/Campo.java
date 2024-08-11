/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JButton;

/**
 *
 * @author User
 */
public class Campo {
        public static int virou;
    public static int nivelbarco;
    
    JButton butao;
    
    
    public Campo(JButton butao){
        this.butao = butao;
        this.virou = 0;
    }
}
