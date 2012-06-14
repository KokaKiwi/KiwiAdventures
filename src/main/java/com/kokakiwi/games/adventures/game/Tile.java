package com.kokakiwi.games.adventures.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.kokakiwi.games.adventures.Main;
import com.kokakiwi.games.adventures.maths.Vector2f;
import com.kokakiwi.games.adventures.maths.VectorsUtils;
import com.kokakiwi.games.adventures.utils.GraphicsUtils;

public class Tile extends Entity
{
    private SpriteSheet  tiles;
    private PolygonShape shape;
    
    public Tile(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Tile(Board board, Vector2f position)
    {
        super(board, position, 16.0f, 16.0f);
    }
    
    @Override
    public Body createBody(Vector2f position)
    {
        BodyDef def = new BodyDef();
        def.position = VectorsUtils.toWorldVector(position, width * Main.scale, height * Main.scale);
        
        Body body = board.getWorld().createBody(def);
        
        shape = new PolygonShape();
        shape.setAsBox(width * Main.scale / 2, height * Main.scale / 2);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0f;
        fixtureDef.restitution = 0.0f;
        
        body.createFixture(fixtureDef);
        
        return body;
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
        
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        tiles.getSprite(0, 0).draw(
                VectorsUtils.toCoreVector(body.getPosition(), width
                        * Main.scale, height * Main.scale).x,
                VectorsUtils.toCoreVector(body.getPosition(), width
                        * Main.scale, height * Main.scale).y, Main.scale);
        
        GraphicsUtils.drawBox(g, shape, body.getPosition(), Color.cyan);
    }
    
}
