import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (Alex Chen) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    private int cloudTimer = 0;
    private ScoreBoard scoreBoard; // Declare the ScoreBoard

    public MyWorld() {    
        super(700, 500, 1);  
        scoreBoard = new ScoreBoard(); // Initialize the ScoreBoard
        addObject(scoreBoard, 350, 25); // Add ScoreBoard to the world at the top center

        addClouds(5); // Initially add some clouds
        addWukong();  // Add Wukong to the world
    }

    public void act() {
        if (cloudTimer % 100 == 0) { 
            addCloud();
        }
        cloudTimer++;
    }

    private void addClouds(int numberOfClouds) {
        for (int i = 0; i < numberOfClouds; i++) {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight() / 2);
            addObject(new Cloud(), x, y);
        }
    }

    private void addCloud() {
        int x = Greenfoot.getRandomNumber(getWidth());
        addObject(new Cloud(), x, 0); 
    }

    private void addWukong() {
        Wukong wukong = new Wukong();
        addObject(wukong, getWidth() / 2, getHeight() - 30); // Position Wukong at the bottom center
    }

    // Method to get the ScoreBoard
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
