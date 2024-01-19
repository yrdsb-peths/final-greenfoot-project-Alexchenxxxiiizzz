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
    private final int JUMP_STRENGTH = -10;
    private final int CLOUD_BOUNCE = -5;
    private int timeAtBottom = 0; 
    private int imageIndex = 0;
    private int animationCounter = 0;
    private boolean isInitialized = false;

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
        }

        verticalVelocity += GRAVITY;
        setLocation(getX(), getY() + verticalVelocity);
        handleHorizontalMovement();
        checkForCloud();
        updateWalkingAnimation();
        checkForLosingConditions();
    }

    private void initializePosition() {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() - getImage().getHeight());
    }

    private void handleHorizontalMovement() {
        if (Greenfoot.isKeyDown("left")) {
            move(-14);
        }
        if (Greenfoot.isKeyDown("right")) {
            move(14);
        }
    }

    private void checkForCloud() {
        if (!onCloud && isTouching(Cloud.class)) {
            onCloud = true;
            verticalVelocity = CLOUD_BOUNCE;
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
        if (isAtTop() || isAtBottomTooLong()) {
            // Game over if Wukong is at the top or at the bottom for too long
            Greenfoot.stop();
            World world = getWorld();
            String message = "Game Over: " + getGameOverReason();
            world.addObject(new GameOverMessage(message), world.getWidth() / 2, world.getHeight() / 2);
        }
    }

    private boolean isAtBottomTooLong() {
        if (isAtBottom()) {
            timeAtBottom++;
            return timeAtBottom > 60; // Assume 60 frames per second, so 180 frames are 3 seconds
        } else {
            timeAtBottom = 0; // Reset the counter if Wukong is not at the bottom
            return false;
        }
    }

    private String getGameOverReason() {
        if (isAtTop()) {
            return "You hit the top!";
        } else if (isAtBottomTooLong()) {
            return "You stayed at the bottom too long!";
        } else {
            return "Unknown reason";
        }
    }

    public boolean isAtTop() {
        return getY() <= 0;
    }

    public boolean isAtBottom() {
        return getY() >= getWorld().getHeight() - getImage().getHeight();
    }
}

