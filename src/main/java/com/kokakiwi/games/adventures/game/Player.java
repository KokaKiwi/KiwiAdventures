package com.kokakiwi.games.adventures.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.kokakiwi.games.adventures.maths.Vector2f;
import com.kokakiwi.games.adventures.maths.VectorsUtils;

public class Player extends Entity
{
    private SpriteSheet worker;
    
    public Player(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Player(Board board, Vector2f position)
    {
        super(board, position);
    }
    
    @Override
    public Body createBody(Vector2f position)
    {
        BodyDef def = new BodyDef();
        def.position = VectorsUtils.toWorldVector(position, 16, 24);
        def.type = BodyType.DYNAMIC;
        
        Body body = board.getWorld().createBody(def);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16.0f / 2, 24.0f / 2);
        
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 1.0f;
        fixture.friction = 0.2f;
        
        body.createFixture(fixture);
        
        return body;
    }
    
    @Override
    public void setPosition(Vector2f position)
    {
        super.setPosition(position);
        body.getPosition().set(VectorsUtils.toWorldVector(position, 16, 24));
    }
    
    public void init(GameContainer gl) throws SlickException
    {
        Image img = new Image(Player.class.getResourceAsStream("/worker.png"),
                "worker", false, 0);
        worker = new SpriteSheet(img, 16, 24);
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
        position = VectorsUtils.toCoreVector(body.getPosition(), 16, 24);
        
        Input in = gl.getInput();
        
        Vec2 vec = body.getLinearVelocity();
        
        if (in.isKeyDown(Input.KEY_Q))
        {
            body.setLinearVelocity(new Vec2(-2.5f, vec.y));
        }
        
        if (in.isKeyDown(Input.KEY_D))
        {
            body.setLinearVelocity(new Vec2(2.5f, vec.y));
        }
        
        if (in.isKeyDown(Input.KEY_SPACE))
        {
            body.setLinearVelocity(new Vec2(vec.x, -8.0f));
        }
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        worker.getSprite(0, 0).draw(position.x, position.y);
        
        g.setColor(Color.cyan);
        
        float x1 = body.getPosition().x - (16.0f / 2);
        float y1 = body.getPosition().y - (24.0f / 2);
        float width = 16.0f;
        float height = 24.0f;
        
        g.drawRect(x1, y1, width, height);
    }
    
}
