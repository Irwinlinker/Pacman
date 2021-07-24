//Robert Marsh
//Dec 5, 2020
//Program plays a simple game of Pacman


package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PacmanBoard extends JPanel implements ActionListener, MouseListener,
        KeyListener {

    //creates a timer variable for animation
    private Timer timer;

    //score variable
    private int score = 0;

    //display message
    private String message = "Go!";

    //create ghosts
    private Ghost redGhost = new Ghost(0, 0, "redGhost.png");
    private Ghost blueGhost = new Ghost(0, 0, "blueGhost.png");
    private Ghost greenGhost = new Ghost(425, 0, "greenGhost.png");
    private Ghost pinkGhost = new Ghost(0, 425, "pinkGhost.png");

    private Apple topLeftApple = new Apple(0, 0, "apple.png");
    private Apple topRightApple = new Apple(425, 0, "apple.png");
    private Apple bottomLeftApple = new Apple(0, 425, "apple.png");
    private Apple bottomRightApple = new Apple(425, 425, "apple.png");

    private Pacman pacman = new Pacman(250, 250, "pacmanright.png");
    //declare a list to hold the ghosts
    private ArrayList<Ghost> ghosts = new ArrayList<>();

    private ArrayList<Apple> apples = new ArrayList<>();



    //constructor for the pacman board class
    public PacmanBoard() {
        message = "Go!";

        //key press function/parts
        setOpaque(true);
        setFocusable(true);

        this.addMouseListener(this);
        this.addKeyListener(this);

        //add ghosts and apples to arrays
        ghosts.add(redGhost);
        ghosts.add(pinkGhost);
        ghosts.add(greenGhost);

        apples.add(topLeftApple);
        apples.add(topRightApple);
        apples.add(bottomLeftApple);
        apples.add(bottomRightApple);

        //set timer to a 5 millisecond interval
        timer = new Timer(5, this);

        //start timer
        timer.start();

        //set the size of the panel/screen
        setPreferredSize(new Dimension(500, 560));
    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Pacman");
        PacmanBoard pmb = new PacmanBoard();
        window.add(pmb);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        //draw black background
        g2D.setColor(new Color(0, 0, 0));
        g2D.fillRect(0, 0, getWidth(), getHeight());

        for(Ghost ghost : ghosts) {//draw all the ghosts
            g2D.drawImage(ghost.getGhostImage(), ghost.getX(),
                    ghost.getY(), null);
        }

        for(Apple apple : apples) {//draw all the apples
            g2D.drawImage(apple.getAppleImage(), apple.getX(),
                    apple.getY(), null);
        }
        //draw pman
        g2D.drawImage(pacman.getPacmanImage(), pacman.getX(),
                pacman.getY(), null);
        //setup for message display
        g2D.setColor(Color.white);
        g2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 48));
        drawCenteredString(message, 500, 60, g2D);


    }

    //method to draw string in center
    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y + 500);
    }

    public boolean areGhostsEaten() {//check for end of game to see if they're all eaten
        for(Ghost ghost : ghosts) {
            if(!ghost.isGhostEaten()) {
                return false;
            }
        }
        return true;
    }

    public boolean areApplesEaten() {//check for end of game to see if they're all eaten
        for(Apple apple : apples) {
            if(!apple.isAppleEaten()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        message = "Score: " + score;
        for(Ghost ghost : ghosts) {//loop for all ghosts and get them going
            ghost.move();

            if(ghost.collide(pacman) && !ghost.isBlue()) {//if you run into a ghost that not blue, you lose
                message = "Game Over!";
                pacman.disappear();
                timer.stop();
            }
            if(ghost.collide(pacman) && ghost.isBlue()) {//add score for eaten ghost and make ghost disappear
                if(!ghost.isGhostEaten()) {
                    score++;
                }
                ghost.disappear();
            }
        }

        for(Apple apple : apples) {//add score for eaten apple and make apple disappear
            if(apple.collide(pacman)) {
                if(!apple.isAppleEaten()) {
                    score++;
                }
                apple.disappear();
            }

        }

        if(areGhostsEaten() && areApplesEaten()) {//check if everything is eaten and end game with message and score
            message = "You Win! Score: " + score;
        }
        pacman.move();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(Ghost ghost : ghosts) {//turn ghosts blue when they're clicked on
            if(ghost.contains(e.getX(), e.getY())) {
                ghost.turnBlue();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {//key press for pman movement method
        pacman.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {//for key release to stop pman method
        pacman.keyReleased(e);
    }
}
