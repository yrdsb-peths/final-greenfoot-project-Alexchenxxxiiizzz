import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cloud here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cloud extends Actor {
    private static final int POINTS_PER_CLOUD = 4;
    private static final int INCREASE_DIFFICULTY_SCORE = 60;
    private static final int WINNING_SCORE = 120;
    private boolean touchedByWukong = false;

    public Cloud() {
        setImage("Cloud.png"); // Set the cloud image
    }

    public void act() {
        checkIfTouchedByWukong();
        moveCloud();
        checkBounds();
        increaseDifficultyIfNeeded();
        checkForWinning(); // Check if winning conditions are met
    }

    private void checkIfTouchedByWukong() {
        if (isTouching(Wukong.class) && !touchedByWukong) {
            touchedByWukong = true;
            MyWorld world = (MyWorld) getWorld();
            world.getScoreBoard().addScore(POINTS_PER_CLOUD);
            // Other actions when Wukong touches the cloud
        }
    }

    private void moveCloud() {
        // Adjust the speed or pattern of the cloud's movement here
        setLocation(getX(), getY() + 3); // Simple downward movement
    }

    private void checkBounds() {
        World world = getWorld();
        if (getY() > world.getHeight()) {
            world.removeObject(this); // Remove the cloud if it goes off-screen
        }
    }

    private void increaseDifficultyIfNeeded() {
        MyWorld world = (MyWorld) getWorld();
        int currentScore = world.getScoreBoard().getScore();
        if (currentScore >= INCREASE_DIFFICULTY_SCORE) {
            // Increase difficulty, e.g., by increasing cloud movement speed
            // This can be done by adjusting the parameters in the moveCloud method
        }
    }

    private void checkForWinning() {
        MyWorld world = (MyWorld) getWorld();
        int currentScore = world.getScoreBoard().getScore();
        if (currentScore >= WINNING_SCORE) {
            // Implement the winning logic, e.g., show a winning screen
            Greenfoot.stop(); // Example: stop the game
        }
    }
}
