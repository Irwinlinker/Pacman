package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Pacman {
    private int x; //x coor for the top left corner of pman
    private int y; //x coor for the top left corner of pman
    private int dx; //value to increment x by as the pman moves
    private int dy; //value to increment y by as the pman moves
    private final int screenHeight = 500; //height for pman to move in
    private final int screenWidth = 500; //width for pman to move in

    //image to display pman
    private Image pacmanImage;

    //initial value constructor of the pman class
    public Pacman(int x, int y, String imageName) {
        //initial x and y values for pman
        this.x = x;
        this.y = y;
        pacmanImage = new ImageIcon(imageName).getImage();
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getPacmanImage() {
        return pacmanImage;
    }

    public void setPacmanImage(Image pacmanImage) {
        this.pacmanImage = pacmanImage;
    }

    public void move() {
        //increment x and y by dx and dy
        x += dx;
        y += dy;

        //pmans x position cannot exceed the bounds of the frame
        //on the right, subtracting the width of the image from
        //the screen width because the coor represents the top
        //left corner of the image. Otherwise he can escape the screen
        // to the right
        if( x > screenWidth - pacmanImage.getWidth(null)) {
            //sets x to the maximum coor that still
            //keeps the image in the frame
            x = screenWidth - pacmanImage.getWidth(null);
        }
        //pmans x coor position cannot be less than zero
        //otherwise pman is off to te left
        if(x < 0) {
            x = 0;
        }
        //pmans y coor position cannot exceed the height of
        //the frame minus the height of the image
        //or pman will escape the bottom
        if(y > screenHeight - pacmanImage.getHeight(null)) {
            //set y to the maximum coor that still
            //keeps the image in the frame
            y = screenHeight - pacmanImage.getHeight(null);
        }
        //pmans y coor position cannot be less than zero
        //or pman will be off the top
        if(y < 0) {
            y = 0;
        }
    }

    //method for key presses when detected
    public void keyPressed(KeyEvent e) {
        System.out.println("X is" + x);
        //assigns the key that was pressed to the variable key
        int key = e.getKeyCode();

        //if left arrow
        if(key == KeyEvent.VK_LEFT) {
            //update image and set dx to negative
            pacmanImage = new ImageIcon("pacmanleft.png").getImage();
            dx = -10;
        }
        //if right arrow
        if(key == KeyEvent.VK_RIGHT) {
            //update image and set dx to positive
            pacmanImage = new ImageIcon("pacmanright.png").getImage();
            dx = 10;
        }
        //if up arrow
        if(key == KeyEvent.VK_UP) {
            //update image and set dx to negative
            pacmanImage = new ImageIcon("pacmanup.png").getImage();
            dy = -10;
        }
        //if down arrow
        if(key == KeyEvent.VK_DOWN) {
            //update image and set dy to positive
            pacmanImage = new ImageIcon("pacmandown.png").getImage();
            dy = 10;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //it only stops the movement for the direction
        //of the key that was released because the player
        //can hold down two arrow keys to go directionally

        if(key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if(key == KeyEvent.VK_RIGHT) {
           dx = 0;
        }

        if(key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if(key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    //make pacman disappear
    public void disappear() {
        pacmanImage = new ImageIcon("blacksquare.png").getImage();
    }
}

