package com.kokakiwi.games.adventures.game;

import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.Rectangle;
import org.newdawn.fizzy.Shape;
import org.newdawn.fizzy.StaticBody;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.kokakiwi.games.adventures.maths.Vector2f;

public class Tile extends Entity
{
    private SpriteSheet tiles;
    
    public Tile(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Tile(Board board, Vector2f position)
    {
        super(board, position, 16.0f, 16.0f);
    }
    
    @Override
    public Body<?> createBody(Vector2f position)
    {
        Shape shape = new Rectangle(width, height);
        shape.setFriction(1.0f);
        shape.setRestitution(0.0f);
        
        Body<Object> body = new StaticBody<Object>(shape, position.x,
                position.y);
        
        return body;
    }
    
    public void init(GameContainer gl) throws SlickException
    {
        // Sprites
        final Image img = new Image(
                Tile.class.getResourceAsStream("/tiles.png"), "tiles", false, 0);
        tiles = new SpriteSheet(img, 16, 16);
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
        
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        tiles.getSprite(0, 0).draw(body.getX(), body.getY());
    }
    
}
