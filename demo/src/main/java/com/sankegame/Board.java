package com.sankegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Board extends JPanel implements ActionListener {
    
    private Image apple;
    private Image dot;
    private Image head;
    Timer timer= new Timer(170, this);
    private int dots;
    // private Timer timer;
    private final int ALL_DOTS=900;
    private final int DOT_SIZE=10;
    private final int Random_Pos=27;

    private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downftDirection=false;

    private boolean inGame=true;

    private int apple_x;
    private int apple_y;

    private final int x[]= new int[ALL_DOTS];
    private final int y[]= new int[ALL_DOTS];


    Board(){
        addKeyListener(new Tadapt());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
        loadImages();
        initGame();
        
        
        
        //initialize the game
    }
    public void loadImages(){
        
        ImageIcon i1 = new ImageIcon("D:\\My Files\\Sigma\\Snake_Game\\demo\\src\\main\\java\\com\\sankegame/icons/head.png");
        head= i1.getImage();
        
        ImageIcon i2 = new ImageIcon("D:\\My Files\\Sigma\\Snake_Game\\demo\\src\\main\\java\\com\\sankegame/icons/dot.png");
        dot= i2.getImage();
        ImageIcon i3 = new ImageIcon("D:\\My Files\\Sigma\\Snake_Game\\demo\\src\\main\\java\\com\\sankegame/icons/apple1.png");
        apple= i3.getImage();
    }
    
       
    public void initGame(){
        dots=3;
        for(int i=0;i<dots;i++){
            y[i]=50;
            x[i]=50- i*DOT_SIZE;
        }
        locateApple();
        timer.start();
    }
    public void locateApple(){
      int pos=(int)  (Math.random()* Random_Pos);
        apple_x=pos* DOT_SIZE;
      pos=(int)  (Math.random()* Random_Pos);
      apple_y=pos*DOT_SIZE;
      
      

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void gameOver(Graphics g){
        String GO="Game Over";
        Font font = new Font("SAN_SERIF",Font.BOLD,14);
       g.setColor(Color.GREEN);
        FontMetrics metrices=getFontMetrics(font);
        g.drawString(GO, (300 - metrices.stringWidth(GO))/2, 300/2);
    }
    public void replay(){
        if (!inGame) {
            inGame=true;
        }
        
    }
    public void draw(Graphics g){
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

        for (int i = 0; i < dots; i++) {
            if (i==0) {
                g.drawImage(head,x[i],y[i],this);
            }else{
                g.drawImage(dot,x[i],y[i],this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }else{
        gameOver(g);
        
    }
    }
    
    public void move(){
        for (int i = dots; i >0; i--) {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if (leftDirection) {
            x[0]=x[0]-DOT_SIZE;
        }
        if (rightDirection) {
            x[0]=x[0]+DOT_SIZE;
        }
        if (upDirection) {
            y[0]=y[0]-DOT_SIZE;
        }
        if (downftDirection) {
            y[0]=y[0]+DOT_SIZE;
        }
        
    }
    public void checkApple(){
        if (x[0]==apple_x && y[0]==apple_y) {
            dots++;
            locateApple();
        }
    }
    public void collision(){
        for (int i = dots; i >0; i--) {
            if ((i>=4 && x[0]==x[i]) && (y[0]==y[i])) {
                inGame=false;
            }
            if (y[0]>=300) {
                inGame=false;
            }
            if (x[0]>=300) {
                inGame=false;
            }
            if (y[0]<0) {
                inGame=false;
            }
            if (x[0]<0) {
                inGame=false;
            }
            // if (!inGame) {
            //     timer.stop();
            // }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (inGame) {
            checkApple();
            collision();
            move();
            
        }
        repaint();
      
    }
    /**
     * Tadapt
     */
    public class Tadapt extends KeyAdapter{
    @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (key==KeyEvent.VK_LEFT && (!rightDirection)) {
                leftDirection=true;
                downftDirection=false;
                upDirection=false;
            }
            if (key== KeyEvent.VK_RIGHT && (!leftDirection)) {
                rightDirection=true;
                downftDirection=false;
                upDirection=false;
            }
            if (key== KeyEvent.VK_UP && (!downftDirection)) {
                upDirection=true;
                rightDirection=false;
                leftDirection=false;
                
            }
            if (key== KeyEvent.VK_DOWN && (!upDirection)) {
                downftDirection=true;
                rightDirection=false;
                
                leftDirection=false;
            }
            if (key== KeyEvent.VK_ENTER&&(!inGame)) {
                // loadImages();
                // initGame();
              replay();
            //   timer.start();
              initGame();
              rightDirection=true;
                downftDirection=false;
                upDirection=false;
            
            }
            if (key== KeyEvent.VK_SPACE&& (inGame)) {
                timer.stop();
                
                // timer.start();
            }else{
                timer.start();
            }
            // if (key== KeyEvent.VK_ENTER&& (inGame)) {
            //     timer.start();
            //     // timer.start();
            // }
        }
    }
}
