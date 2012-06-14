package com.kokakiwi.games.adventures.game;

import org.jbox2d.dynamics.Body;

import com.kokakiwi.games.adventures.maths.Vector2f;
import com.kokakiwi.games.adventures.maths.VectorsUtils;

public abstract class Entity implements IRenderable
{
    protected final Board board;
    protected final Body  body;
    protected final float width;
    protected final float height;
    
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
    }
    
    public void setPosition(Vector2f position)
    {
        body.getPosition().set(VectorsUtils.toVec2(position));
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
