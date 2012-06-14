package com.kokakiwi.games.adventures.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IRenderable
{
    public void init(GameContainer gl) throws SlickException;
    
    public void update(GameContainer gl, int delta) throws SlickException;
    
    public void render(GameContainer gl, Graphics g) throws SlickException;
}
