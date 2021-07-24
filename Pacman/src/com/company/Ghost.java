package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Ghost {
    //store a number between 0 and 3
    //to represent a direction
    private int ghostDir;
    //image of the ghost
    private Image ghostImage;
    //width and height of the frame for
    //ghosts to travel in
    private final int screenWidth = 500;
    private final int screenHeight = 500;
    //keep track of whether or not ghost is blue
    private boolean isBlue = false;
    //keep track of whether the ghost has been eaten
    //if eaten, disappears
    private boolean isGhostEaten = false;

    //the x, y and dx, dy for the ghost
    //x and y are the top left corner of the ghost image
    //dx and dy are the value that the x and y get incremented by
    //each cycle or "frame" of the animation
    //if dx is positive, the ghost moves to the right
    //if dy is positive, the ghost moves down
    //if dx is negative, the ghost moves left
    //if dy is positive, the ghost moves up
    private int x, y, dx = 1, dy;

    //constructor for the class
    public Ghost(int x, int y, String imageName) {
        //set x, y and the image to initial
        //values passed into this constructor
        //when the pacman gets created
        this.x = x;
        this.y = y;
        ghostImage = new ImageIcon(imageName).getImage();
    }

    //method to move the pacman, this is called once per
    //cycle, or frame, and it adjusts the pacmans position
    //by the value of dx and dy, then the frame is redrawn, so
    //it has gradual movement in a flipbook animation type of way
    public void move() {
        //ghosts direction is selected randomly
        Random randGen = new Random();
        System.out.println("X" + x + "Y" + y);

        //random number is not generated until it hits a corner, then
        //a new random number is generated

        //if the random number is 0,
        //the ghost starts moving right
        if(ghostDir == 0) {
            dx = 1;
            dy = 0;
        }

        //if the random number is 1,
        //the ghost starts moving left
        else if(ghostDir ==1) {
            dx =- 1;
            dy = 0;
        }

        //if the random number is 2,
        //the ghost starts moving up
        else if(ghostDir ==2) {
            dx = 0;
            dy =- 1;
        }

        //if the random number is 3,
        //the ghost starts moving down
        else if(ghostDir ==3) {
            dx = 0;
            dy = 1;
        }

        //move ghost
        x += dx;
        y += dy;

        //if ghost is in the corner, let him choose another dir
        //this will keep the ghost in the frame

        //top left corner
        if(x <= 0 && y <= 0) {
            System.out.println("top left corner");
            //ghost can go down or right
            int rand = randGen.nextInt(2);
            if(rand == 1) {
                //go up
                ghostDir = 3;
            }
            else {
                //go right
                ghostDir = 0;
            }
        }

        //if the ghost is in the top right corner
        else if( x >= screenWidth - ghostImage.getWidth(null) && y == 0) {
            //ghost can go down or left
            //generate a random number between 0 and 1
            //to decide which way to go
            int rand = randGen.nextInt(2);
            //if random number is 1
            if(rand ==1) {
               //go down
               ghostDir = 3;
            }
            else {
                //go left
                ghostDir = 1;
            }
        }
        //if the ghost is in the bottom right corner,
        //for the right and bottom of the panel, you have to subtract
        //the width or height of the image because the
        //x, y coordinate of the pacman represents the top left corner
        //of the image
        else if(x >= screenWidth - ghostImage.getWidth(null)
                && y >= screenWidth - ghostImage.getHeight(null)) {
            //ghost can go up or left
            int rand = randGen.nextInt(2);
            if(rand == 1) {
                //go up
                ghostDir = 2;
            }
            else {
                //go left
                ghostDir = 1;
            }
        }

        //bottom left corner
        else if(x <=0 && y >= screenHeight - ghostImage.getHeight(null)) {
            //ghost can go up or right
            int rand = randGen.nextInt(2);
            if(rand == 1) {
                //go up
                ghostDir = 2;
            }
            else {
                //go right
                ghostDir = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getGhostImage() {
        return ghostImage;
    }

    public void turnBlue() {
        isBlue = true;
        ghostImage = new ImageIcon("blueghost.png").getImage();
    }

    //method is called to see if a point is within bounds
    //of the ghost
    public boolean contains(int x, int y) {
        Rectangle2D ghost =
                new Rectangle(this.x, this.y, ghostImage.getWidth(null),
                        ghostImage.getHeight(null));
        if(ghost.contains(x, y)) {
            return true;
        }
        return false;
    }

    //method is called to see if pacman touches ghost
    public boolean collide(Pacman pacman) {
        //create two rectangles the same size and position
        //as the ghost and pacman objects where they are
        Rectangle2D ghost = new Rectangle(x, y,
                ghostImage.getWidth(null),
                ghostImage.getHeight(null));
        Rectangle2D pac = new Rectangle(pacman.getX(),
                pacman.getY(), pacman.getPacmanImage().getWidth(null),
                pacman.getPacmanImage().getHeight(null));
        //if the two rectangles are touching, return true, otherwise
        //return false. The intersects method can be called to see if
        //two Rectangle2D objects overlap, it returns a boolean.
        return ghost.intersects(pac);
    }

    //display a black rectangle for the ghost image to hide it
    //after it has been eaten
    public void disappear() {
        isGhostEaten = true;
        ghostImage = new ImageIcon("blacksquare.png").getImage();
    }


    public boolean isBlue() {
        return isBlue;
    }

    public boolean isGhostEaten() {
        return isGhostEaten;
    }
}
