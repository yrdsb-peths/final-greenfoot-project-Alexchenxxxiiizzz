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
    private int jumpVelocity = 0;
    private boolean inAir = true; // Set to true initially, as Wukong is not on a cloud at the start
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15; // The strength of the jump

    public Wukong() {
        walkingImages = new GreenfootImage[5];
        String folderPath = "images/walking/"; // Your folder path

        for (int i = 0; i < walkingImages.length; i++) {
            String imagePath = folderPath + "walk" + (i + 1) + ".png";
            walkingImages[i] = new GreenfootImage(imagePath);
            System.out.println("Loaded image: " + imagePath);
        }

        setImage(walkingImages[0]);
    }

    public void act() {
        updateWalkingAnimation();
        handleMovement();
        handleJump();
        applyGravity();
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
            move(-5); // Move left by 5 pixels
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5); // Move right by 5 pixels
        }
    }

    private void handleJump() {
        // Only allow jumping if Wukong is not in the air
        if (Greenfoot.isKeyDown("space") && !inAir) {
            jumpVelocity = JUMP_STRENGTH;
            inAir = true;
        }
    }

    private void applyGravity() {
        if (inAir) {
            setLocation(getX(), getY() + jumpVelocity);
            jumpVelocity += GRAVITY;

            // Check for landing on a cloud
            if (isTouching(Cloud.class)) {
                inAir = false;
                jumpVelocity = 0;
                // Align Wukong on top of the cloud
                Actor cloud = getOneIntersectingObject(Cloud.class);
                setLocation(getX(), cloud.getY() - getImage().getHeight() / 2);
            } else if (getY() >= getWorld().getHeight()) {
                // Wukong falls off the screen
                Greenfoot.stop(); // Stop the game or handle it as a game over
            }
        }
    }
}

