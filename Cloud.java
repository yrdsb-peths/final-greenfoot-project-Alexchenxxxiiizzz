import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cloud here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cloud extends Actor {
    private int dx; // Horizontal speed
    private int dy; // Vertical speed

    public Cloud() {
        setImage("Cloud.png"); // Set the image of the cloud

        dx = Greenfoot.getRandomNumber(3) - 1; // Random horizontal speed (-1, 0, 1)
        dy = Greenfoot.getRandomNumber(2); // Vertical speed (0 or 1)
    }

    public void act() {
        // Cloud movement logic
        moveHorizontal();
        moveDown();

        // Check if the cloud has reached the edge of the screen
        checkBounds();
    }

    private void moveHorizontal() {
        setLocation(getX() + dx, getY());
    }

    private void moveDown() {
        setLocation(getX(), getY() + dy);
    }

    private void checkBounds() {
        World world = getWorld();
        if (getX() < 0 || getX() > world.getWidth()) {
            dx = -dx; // Reverse direction horizontally
        }
        if (getY() > world.getHeight()) {
            // If the cloud reaches the bottom, reposition it at the top
            setLocation(Greenfoot.getRandomNumber(world.getWidth()), 0);
        }
    }
}
