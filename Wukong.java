import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wukong here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wukong extends Actor {
    private GreenfootImage[] walkingImages;
    private int imageIndex = 0;
    private int frameCounter = 0;

    public Wukong() {
        walkingImages = new GreenfootImage[5];
        for (int i = 0; i < walkingImages.length; i++) {
            walkingImages[i] = new GreenfootImage("wukong_walk/walk" + (i + 1) + ".png");
        }
        setImage(walkingImages[0]);
    }

    public void act() {
        updateWalkingAnimation();
        handleMovement();  // Add method for handling movement
        handleJump();      // Add method for handling jump
    }

    private void updateWalkingAnimation() {
        if (frameCounter % 10 == 0) { 
            imageIndex = (imageIndex + 1) % walkingImages.length;
            setImage(walkingImages[imageIndex]);
        }
        frameCounter++;
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("left")) {
            move(-5);  // Move left by 5 pixels
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5);   // Move right by 5 pixels
        }
    }

    private void handleJump() {
        if (Greenfoot.isKeyDown("space")) {
            // Add jumping logic here
            // For example: move the character upward by a certain distance
            setLocation(getX(), getY() - 10);
        }
    }
}

