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
    private final int CLOUD_BOUNCE = 2; // Slight upward bounce on touching a cloud
    private int timeWithoutCloud = 0; // Timer to track the duration without touching a cloud
    private int imageIndex = 0; // To cycle through walking images
    private int animationCounter = 0; // Counter for controlling the animation speed

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
        if (Greenfoot.isKeyDown("space") && onCloud) {
            verticalVelocity = JUMP_STRENGTH;
            onCloud = false;
            timeWithoutCloud = 0; // Reset the timer
        }

        if (!onCloud) {
            verticalVelocity += GRAVITY;
            timeWithoutCloud++;
            if (timeWithoutCloud > 300) { // Approximately 5 seconds (300 frames)
                Greenfoot.stop(); // End the game
            }
        }

        setLocation(getX(), getY() + verticalVelocity);
        handleHorizontalMovement();
        checkForCloud();
        updateWalkingAnimation();
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
            verticalVelocity = CLOUD_BOUNCE; // Slight bounce upon touching a cloud
            timeWithoutCloud = 0; // Reset the timer
        }
    }

    private void updateWalkingAnimation() {
        animationCounter++;
        if (animationCounter % 20 == 0) { // Increase this value to slow down the animation
            imageIndex = (imageIndex + 1) % walkingImages.length;
            setImage(walkingImages[imageIndex]);
        }
    }

    private boolean isOnGround() {
        // Check if Wukong is on the ground
        return getY() >= getWorld().getHeight() - getImage().getHeight() / 2;
    }
}

