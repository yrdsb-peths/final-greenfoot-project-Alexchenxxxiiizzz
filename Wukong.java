            import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wukong here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wukong extends Actor {
    private GreenfootImage[] walkingImages;
    private boolean onCloud = false;
    private int verticalVelocity = 0;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -10; // Slower ascent
    private final int CLOUD_BOUNCE = -5; // Increased bounce effect for a slight jump
    private int timeWithoutCloud = 0; // Timer to track the duration without touching a cloud
    private int timeOnTop = 0; // Timer to track the time spent at the top
    private int timeSinceStart = 0; // Timer to track the time since the start of the game
    private final int TIME_LIMIT = 150; // 3 seconds, assuming 60 frames per second
    private int imageIndex = 0; // To cycle through walking images
    private int animationCounter = 0; // Counter for controlling the animation speed
    private boolean isInitialized = false; // Flag to ensure initialization occurs only once

    public Wukong() {
        walkingImages = new GreenfootImage[5];
        String folderPath = "images/walking/";
        for (int i = 0; i < walkingImages.length; i++) {
            String imagePath = folderPath + "walk" + (i + 1) + ".png";
            walkingImages[i] = new GreenfootImage(imagePath);
            System.out.println("Loaded image: " + imagePath);
        }
        setImage(walkingImages[0]);
    }

    public void act() {
        if (!isInitialized) {
            initializePosition();
            isInitialized = true;
        }

        if (Greenfoot.isKeyDown("space") && onCloud) {
            verticalVelocity = JUMP_STRENGTH;
            onCloud = false;
            timeWithoutCloud = 0;
        }

        verticalVelocity += GRAVITY;
        setLocation(getX(), getY() + verticalVelocity);
        handleHorizontalMovement();
        checkForCloud();
        updateWalkingAnimation();
        checkForLosingConditions();

        timeSinceStart++; // Increase time since start
    }

    private void initializePosition() {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() - getImage().getHeight());
    }

    private void handleHorizontalMovement() {
        if (Greenfoot.isKeyDown("left")) {
            move(-5);
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5);
        }
    }

    private void checkForCloud() {
        if (!onCloud && isTouching(Cloud.class)) {
            onCloud = true;
            verticalVelocity = CLOUD_BOUNCE;
            timeWithoutCloud = 0;
        } else if (!isTouching(Cloud.class)) {
            onCloud = false;
        }
    }

    private void updateWalkingAnimation() {
        animationCounter++;
        if (animationCounter % 20 == 0) {
            imageIndex = (imageIndex + 1) % walkingImages.length;
            setImage(walkingImages[imageIndex]);
        }
    }

    private void checkForLosingConditions() {
        if (getY() >= getWorld().getHeight()) {
            Greenfoot.stop(); // Game over if Wukong falls off the screen
        }

        if (getY() <= 0) {
            timeOnTop++;
            if (timeOnTop > 10) {
                Greenfoot.stop(); // Game over if Wukong stays on top for more than 3 seconds
            }
        } else {
            timeOnTop = 0;
        }

        // Check if Wukong is at the bottom of the screen after 10 seconds
        if (timeSinceStart > TIME_LIMIT && getY() >= getWorld().getHeight() - getImage().getHeight()) {
            Greenfoot.stop(); // Game over if Wukong is at the bottom after 10 seconds
        }
    }
}


