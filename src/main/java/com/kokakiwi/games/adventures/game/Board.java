package com.kokakiwi.games.adventures.game;

import java.util.LinkedList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Board implements IRenderable
{
    private final List<IRenderable> children = new LinkedList<IRenderable>();
    private final World             world;
    private float                   speed    = 1 / 60.0f;
    
    public Board()
    {
        world = new World(new Vec2(0.0f, 1.0f), true);
    }
    
    public List<IRenderable> getChildren()
    {
        return children;
    }
    
    public void addChild(IRenderable child)
    {
        children.add(child);
    }
    
    public void removeChild(IRenderable child)
    {
        children.remove(child);
    }
    
    public Vec2 getGravity()
    {
        return world.getGravity();
    }
    
    public void setGravity(Vec2 gravity)
    {
        world.setGravity(gravity);
    }
    
    public World getWorld()
    {
        return world;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    
    public void init(GameContainer gl) throws SlickException
    {
        for (IRenderable child : children)
        {
            child.init(gl);
        }
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
        world.step(delta * speed, 6, 2);
        
        for (IRenderable child : children)
        {
            child.update(gl, delta);
        }
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        for (IRenderable child : children)
        {
            child.render(gl, g);
        }
    }
    
}
