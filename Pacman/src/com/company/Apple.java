package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Apple {
    private int x;
    private int y;
    private final int screenHeight = 500;
    private final int screenWidth = 500;
    private boolean isAppleEaten = false;

    private Image appleImage;

    public Apple(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        appleImage = new ImageIcon(imageName).getImage();
    }

    //display a black rectangle for the apple image to hide it
    //after it has been eaten
    public void disappear() {
        isAppleEaten = true;
        appleImage = new ImageIcon("blacksquare.png").getImage();
    }

    //method is called to see if pacman touches apple
    public boolean collide(Pacman pacman) {
        //create two rectangles the same size and position
        //as the ghost and pacman objects where they are
        Rectangle2D apple = new Rectangle(x, y,
                appleImage.getWidth(null),
                appleImage.getHeight(null));
        Rectangle2D pac = new Rectangle(pacman.getX(),
                pacman.getY(), pacman.getPacmanImage().getWidth(null),
                pacman.getPacmanImage().getHeight(null));
        //if the two rectangles are touching, return true, otherwise
        //return false. The intersects method can be called to see if
        //two Rectangle2D objects overlap, it returns a boolean.
        return apple.intersects(pac);
    }

    public boolean isAppleEaten() {
        return isAppleEaten;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getAppleImage() {
        return appleImage;
    }


}
