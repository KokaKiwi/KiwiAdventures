package com.kokakiwi.games.adventures.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.kokakiwi.games.adventures.maths.Vector2f;
import com.kokakiwi.games.adventures.maths.VectorsUtils;

public class Tile extends Entity
{
    private SpriteSheet tiles;
    
    public Tile(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Tile(Board board, Vector2f position)
    {
        super(board, position);
        
    }
    
    @Override
    public Body createBody(Vector2f position)
    {
        BodyDef def = new BodyDef();
        def.position = VectorsUtils.toWorldVector(position, 16, 16);
        
        Body body = board.getWorld().createBody(def);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16.0f / 2, 16.0f / 2);
        
        body.createFixture(shape, 0);
        
        return body;
    }
    
    @Override
    public void setPosition(Vector2f position)
    {
        super.setPosition(position);
        body.getPosition().set(VectorsUtils.toWorldVector(position, 16, 16));
    }
    
    public void init(GameContainer gl) throws SlickException
    {
        // Sprites
        Image img = new Image(Tile.class.getResourceAsStream("/tiles.png"),
                "tiles", false, 0);
        tiles = new SpriteSheet(img, 16, 16);
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
        position = VectorsUtils.toCoreVector(body.getPosition(), 16, 16);
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        tiles.getSprite(0, 0).draw(position.x, position.y);
        
        g.setColor(Color.cyan);
        
        float x1 = body.getPosition().x - (16.0f / 2);
        float y1 = body.getPosition().y - (16.0f / 2);
        float width = 16.0f;
        float height = 16.0f;
        
        g.drawRect(x1, y1, width, height);
    }
    
}
