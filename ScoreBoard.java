import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor {
    private int score;

    public ScoreBoard() {
        score = 0;
        update();
    }

    public void addScore(int points) {
        score += points;
        update();
    }

    public int getScore() {
        return score;
    }

    private void update() {
        setImage(new GreenfootImage("Score: " + score, 24, Color.WHITE, new Color(0, 0, 0, 128)));
    }
}
