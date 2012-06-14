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

import com.kokakiwi.games.adventures.Main;
import com.kokakiwi.games.adventures.maths.Vector2f;
import com.kokakiwi.games.adventures.maths.VectorsUtils;
import com.kokakiwi.games.adventures.utils.GraphicsUtils;

public class Player extends Entity implements
        com.kokakiwi.engine.time.data.Entity
{
    private SpriteSheet  worker;
    private PolygonShape shape;
    
    public Player(Board board, float x, float y)
    {
        this(board, new Vector2f(x, y));
    }
    
    public Player(Board board, Vector2f position)
    {
        super(board, position, 16.0f, 24.0f);
    }
    
    @Override
    public Body createBody(Vector2f position)
    {
        BodyDef def = new BodyDef();
        def.position = VectorsUtils.toWorldVector(position, width * Main.scale, height * Main.scale);
        def.type = BodyType.DYNAMIC;
        
        Body body = board.getWorld().createBody(def);
        
        shape = new PolygonShape();
        shape.setAsBox(width / 2 * Main.scale, height / 2 * Main.scale);
        
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 1.0f;
        fixture.restitution = 0.0f;
        fixture.friction = 0.3f;
        
        body.createFixture(fixture);
        
        return body;
    }
    
    public void init(GameContainer gl) throws SlickException
    {
        Image img = new Image(Player.class.getResourceAsStream("/worker.png"),
                "worker", false, 0);
        worker = new SpriteSheet(img, 16, 24);
    }
    
    public void update(GameContainer gl, int delta) throws SlickException
    {
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
        worker.getSprite(0, 0).draw(
                VectorsUtils.toCoreVector(body.getPosition(), width
                        * Main.scale, height * Main.scale).x,
                VectorsUtils.toCoreVector(body.getPosition(), width
                        * Main.scale, height * Main.scale).y, Main.scale);
        
        GraphicsUtils.drawBox(g, shape, body.getPosition(), Color.cyan);
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
                value = body.getPosition().x;
                break;
            
            case POS_Y:
                value = body.getPosition().y;
                break;
        }
        
        return value;
    }
    
    public void setValue(int field, float value)
    {
        Vec2 position = body.getPosition();
        float angle = body.getAngle();
        
        switch (field)
        {
            case POS_X:
                position.x = value;
                break;
            
            case POS_Y:
                position.y = value;
                break;
        }
        
        body.setTransform(position, angle);
    }
    
    public int[] getFields()
    {
        return new int[] { POS_X, POS_Y };
    }
    
    public final static int POS_X = 1;
    public final static int POS_Y = 2;
    
}
