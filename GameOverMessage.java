import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverMessage extends Actor {
    public GameOverMessage(String message) {
        setImage(new GreenfootImage(message, 30, Color.WHITE, new Color(0, 0, 0, 128)));
    }
}
