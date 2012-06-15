package com.kokakiwi.games.adventures.game;

import org.newdawn.fizzy.Body;

import com.kokakiwi.games.adventures.maths.Vector2f;

public abstract class Entity implements IRenderable
{
    protected final Board   board;
    protected final Body<?> body;
    protected final float   width;
    protected final float   height;
    
    public Entity(Board board, float x, float y, float width, float height)
    {
        this(board, new Vector2f(x, y), width, height);
    }
    
    public Entity(Board board, Vector2f position, float width, float height)
    {
        this.board = board;
        
        this.width = width;
        this.height = height;
        
        body = createBody(position);
        
        board.getWorld().add(body);
    }
    
    public void setPosition(Vector2f position)
    {
        body.setPosition(position.x, position.y);
    }
    
    public Board getBoard()
    {
        return board;
    }
    
    public Body<?> getBody()
    {
        return body;
    }
    
    public abstract Body<?> createBody(Vector2f position);
}
