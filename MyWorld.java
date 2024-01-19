import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (Alex Chen) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    private int cloudTimer = 0;
    private ScoreBoard scoreBoard;
    private Wukong wukong;

    public MyWorld() {    
        super(900, 600, 1);  
        scoreBoard = new ScoreBoard();
        addObject(scoreBoard, 350, 25);

        addClouds(5);
        addWukong();
    }

    public void act() {
        if (cloudTimer % 100 == 0) { 
            addCloud();
        }
        cloudTimer++;

        checkWinCondition();
    }

    private void checkWinCondition() {
        if (scoreBoard.getScore() >= 80) {
            Label winLabel = new Label("You Win!", 50); // Assumes a Label class that takes a String and font size
            addObject(winLabel, getWidth() / 2, getHeight() / 2);
            Greenfoot.stop(); // Stops the act() loop if the game is won
        }
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
        wukong = new Wukong();
        addObject(wukong, getWidth() / 2, getHeight() - 30);
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
  


    
