package com.kokakiwi.games.adventures;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame
{
    private final Main main;
    
    public MainGame(Main main)
    {
        super("Kiwi Adventures");
        
        this.main = main;
    }
    
    @Override
    public void init(GameContainer gl) throws SlickException
    {
        main.getBoard().init(gl);
    }
    
    @Override
    public void update(GameContainer gl, int delta) throws SlickException
    {
        main.getBoard().update(gl, delta);
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        main.getBoard().render(gl, g);
    }
    
}
