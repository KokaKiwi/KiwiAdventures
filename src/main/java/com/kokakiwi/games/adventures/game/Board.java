package com.kokakiwi.games.adventures.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.newdawn.fizzy.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.kokakiwi.engine.time.TimeEngine;
import com.kokakiwi.engine.time.data.Entity;
import com.kokakiwi.engine.time.storage.memory.MemoryStorage;
import com.kokakiwi.games.adventures.Main;

public class Board implements IRenderable
{
    private final List<IRenderable> children  = new LinkedList<IRenderable>();
    private final World             world;
    private float                   speed     = 0.01f;
    
    private final TimeEngine        timeEngine;
    private final Stack<Long>       times     = new Stack<Long>();
    private long                    timestamp = 0;
    
    public Board()
    {
        this(new World(1.0f));
        world.setBounds(0.0f, 0.0f, 1024.0f, 768.0f);
    }
    
    public Board(World world)
    {
        this.world = world;
        timeEngine = new TimeEngine(new MemoryStorage());
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
        final TiledMap map = new TiledMap("res/map.tmx", false);
        
        final int tilesLayer = map.getLayerIndex("tiles");
        
        for (int x = 0; x < map.getWidth(); x++)
        {
            for (int y = 0; y < map.getHeight(); y++)
            {
                final int tileID = map.getTileId(x, y, tilesLayer);
                if (tileID == 1)
                {
                    final Tile tile = new Tile(this, x * 16 * Main.scale, y
                            * 16 * Main.scale);
                    addChild(tile);
                }
            }
        }
        
        final int x = map.getObjectX(0, 0);
        final int y = map.getObjectY(0, 0);
        
        final Player player = new Player(this, x * Main.scale, y * Main.scale);
        addChild(player);
        
        for (final IRenderable child : children)
        {
            child.init(gl);
        }
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
        final Input in = gl.getInput();
        
        if (!in.isKeyDown(Input.KEY_LSHIFT) && !in.isKeyDown(Input.KEY_RSHIFT)
                || times.empty())
        {
            timeEngine.save();
            if (timestamp == 0)
            {
                timestamp = System.currentTimeMillis();
            }
            timestamp += delta;
            times.push(Long.valueOf(timestamp));
            
            world.update(delta * speed);
            
            timeEngine.save(timestamp);
            
            for (final IRenderable child : children)
            {
                child.update(gl, delta);
            }
        }
        else
        {
            // times.pop();
            //
            // timeEngine.apply(times.peek().longValue());
            
            timeEngine.rewind(delta);
        }
        
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        for (final IRenderable child : children)
        {
            child.render(gl, g);
        }
    }
    
}
