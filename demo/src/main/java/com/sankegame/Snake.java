package com.sankegame;

import javax.swing.*;

public final class Snake extends JFrame{
    //for frame here used is jframe(class) from swing-frameworke(package)
    Snake(){//show Frame
        super("Snake Game");//super is function...must be 1st statement inside constructor
        
        add(new Board());
        pack();//refreshes frame[similiar to setVisibile();]
        // setSize(300,300);
        setLocationRelativeTo(null); 
        
        setResizable(false);
    }


    public static void main(String[] args) {
        new Snake().setVisible(true);
        
    }
}
