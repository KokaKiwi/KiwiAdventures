package com.kokakiwi.games.adventures.game;

import org.jbox2d.dynamics.Body;

import com.kokakiwi.games.adventures.maths.Vector2f;

public abstract class Entity implements IRenderable
{
    protected final Board board;
    protected Vector2f    position;
    protected final Body  body;
    
    public Entity(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Entity(Board board, Vector2f position)
    {
        this.board = board;
        this.position = position;
        
        body = createBody(position);
    }
    
    public Vector2f getPosition()
    {
        return position;
    }
    
    public void setPosition(Vector2f position)
    {
        this.position = position;
    }
    
    public Board getBoard()
    {
        return board;
    }
    
    public Body getBody()
    {
        return body;
    }
    
    public abstract Body createBody(Vector2f position);
}
