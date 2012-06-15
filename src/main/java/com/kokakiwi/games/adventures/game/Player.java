package com.kokakiwi.games.adventures.game;

import org.jbox2d.common.Vec2;
import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.CollisionEvent;
import org.newdawn.fizzy.DynamicBody;
import org.newdawn.fizzy.Rectangle;
import org.newdawn.fizzy.WorldListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.kokakiwi.games.adventures.maths.Vector2f;

public class Player extends Entity implements
        com.kokakiwi.engine.time.data.Entity, WorldListener
{
    private SpriteSheet worker;
    private boolean     jumping = true;
    
    public Player(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Player(Board board, Vector2f position)
    {
        super(board, position, 16.0f, 24.0f);
    }
    
    @Override
    public Body<?> createBody(Vector2f position)
    {
        Rectangle shape = new Rectangle(width, height);
        shape.setFriction(0.3f);
        shape.setRestitution(0.0f);
        
        Body<Object> body = new DynamicBody<Object>(shape, position.x,
                position.y);
        body.setFixedRotation(true);
        
        board.getWorld().addBodyListener(body, this);
        
        return body;
    }
    
    public void init(GameContainer gl) throws SlickException
    {
        final Image img = new Image(
                Player.class.getResourceAsStream("/worker.png"), "worker",
                false, 0);
        worker = new SpriteSheet(img, 16, 24);
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
        final Input in = gl.getInput();
        
        final Vec2 vec = new Vec2(body.getXVelocity(), body.getYVelocity());
        
        if (in.isKeyDown(Input.KEY_Q))
        {
            vec.x = -2.0f;
        }
        
        if (in.isKeyDown(Input.KEY_D))
        {
            vec.x = 2.0f;
        }
        
        if (in.isKeyDown(Input.KEY_SPACE) && !jumping)
        {
            vec.y = -4.0f;
            jumping = true;
        }
        
        body.setVelocity(vec.x, vec.y);
    }
    
    public void render(GameContainer gl, Graphics g) throws SlickException
    {
        Image sprite = worker.getSprite(0, 0);
        sprite.draw(body.getX(), body.getY());
    }
    
    public int getId()
    {
        return 255;
    }
    
    public float getValue(int field)
    {
        float value = 0;
        
        switch (field)
        {
            case POS_X:
                value = body.getX();
                break;
            
            case POS_Y:
                value = body.getY();
                break;
            
            case VEC_X:
                value = body.getXVelocity();
                break;
            
            case VEC_Y:
                value = body.getYVelocity();
                break;
        }
        
        return value;
    }
    
    public void setValue(int field, float value)
    {
        final Vec2 position = new Vec2(body.getX(), body.getY());
        final float angle = body.getRotation();
        
        final Vec2 vec = new Vec2(body.getXVelocity(), body.getYVelocity());
        
        switch (field)
        {
            case POS_X:
                position.x = value;
                break;
            
            case POS_Y:
                position.y = value;
                break;
            
            case VEC_X:
                vec.x = value;
                break;
            
            case VEC_Y:
                vec.y = value;
                break;
        }
        
        body.setPosition(position.x, position.y);
        body.setRotation(angle);
        body.setVelocity(vec.x, vec.y);
    }
    
    public int[] getFields()
    {
        return new int[] { POS_X, POS_Y, VEC_X, VEC_Y };
    }
    
    public final static int POS_X = 1;
    public final static int POS_Y = 2;
    public final static int VEC_X = 3;
    public final static int VEC_Y = 4;
    
    public void collided(CollisionEvent event)
    {
        if (jumping)
        {
            jumping = false;
        }
    }
    
    public void separated(CollisionEvent event)
    {
        
    }
    
}
