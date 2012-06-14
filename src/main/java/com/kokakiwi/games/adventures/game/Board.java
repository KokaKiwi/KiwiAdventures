package com.kokakiwi.games.adventures.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.kokakiwi.engine.time.TimeEngine;
import com.kokakiwi.engine.time.data.Entity;
import com.kokakiwi.engine.time.storage.memory.MemoryStorage;

public class Board implements IRenderable
{
    private final List<IRenderable> children  = new LinkedList<IRenderable>();
    private final World             world;
    private float                   speed     = 1 / 60.0f;
    
    private final TimeEngine        timeEngine;
    private final Stack<Long>       times     = new Stack<Long>();
    private long                    timestamp = 0;
    
    public Board()
    {
        this(new World(new Vec2(0.0f, 1.0f), true));
    }
    
    public Board(World world)
    {
        this.world = world;
        this.timeEngine = new TimeEngine(new MemoryStorage());
    }
    
    public List<IRenderable> getChildren()
    {
        return children;
    }
    
    public void addChild(IRenderable child)
    {
        children.add(child);
        if (child instanceof Entity)
        {
            timeEngine.registerEntity((Entity) child);
        }
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
        Input in = gl.getInput();
        
        if ((!in.isKeyDown(Input.KEY_LSHIFT) && !in.isKeyDown(Input.KEY_RSHIFT))
                || times.empty())
        {
            if (timestamp == 0)
            {
                timestamp = System.currentTimeMillis();
            }
            timestamp += delta;
            times.push(Long.valueOf(timestamp));
            
            world.step(delta * speed, 1, 1);
            
            timeEngine.save(timestamp);
            
            for (IRenderable child : children)
            {
                child.update(gl, delta);
            }
        }
        else
        {
            times.pop();
            
            timeEngine.apply(times.peek().longValue());
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
