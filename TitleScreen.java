import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TittleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TitleScreen extends World {

    public TitleScreen() {    
        super(700, 400, 1);
        prepare();
    }

    private void prepare() {
        // Add game title
        GreenfootImage title = new GreenfootImage("Through the clouds", 32, Color.RED, new Color(0, 0, 0, 0));
        getBackground().drawImage(title, getWidth() / 2 - title.getWidth() / 2, getHeight() / 4);

        // Add gameplay instructions
        String instructions = "Move left and right, press space to jump once.\nFalling to the ground or reaching the top both end the game.";
        GreenfootImage instructionImage = new GreenfootImage(instructions, 24, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(instructionImage, getWidth() / 2 - instructionImage.getWidth() / 2, getHeight() / 2);

        // Add start game prompt
        String startPrompt = "Press SPACE to start the game";
        GreenfootImage startPromptImage = new GreenfootImage(startPrompt, 24, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(startPromptImage, getWidth() / 2 - startPromptImage.getWidth() / 2, getHeight() - 80);
    }

    public void act() {
        // Start the game when the spacebar is pressed
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld()); // Assuming your game world is named MyWorld
        }
    }
}
