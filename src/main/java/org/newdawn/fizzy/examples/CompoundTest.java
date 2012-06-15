package org.newdawn.fizzy.examples;

import org.jbox2d.common.Vec2;
import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.CompoundShape;
import org.newdawn.fizzy.DynamicBody;
import org.newdawn.fizzy.Polygon;
import org.newdawn.fizzy.Rectangle;
import org.newdawn.fizzy.StaticBody;
import org.newdawn.fizzy.World;

/**
 * Simple test for the debug rendering system
 * 
 * @author kevin
 */
public class CompoundTest extends AbstractTest
{
    /**
     * Create a simulation and run it through the renderer
     * 
     * @param argv
     *            Arguments passed to the test
     */
    public static void main(String[] argv)
    {
        final CompoundTest test = new CompoundTest();
        test.startInWindow();
    }
    
    @Override
    public World createWorld()
    {
        final World world = new World();
        world.setBounds(800, 800);
        
        CompoundShape shape = new CompoundShape();
        shape.add(new Rectangle(20, 10).setOffset(-15, -5, 0));
        shape.add(new Rectangle(10, 10).setOffset(-5, -5, 0));
        
        Body<?> compound = new DynamicBody(shape, 0, 0.0f);
        compound.setRestitution(0.1f);
        world.add(compound);
        compound.setRotation(0.5f);
        
        shape = new CompoundShape();
        shape.add(new Rectangle(10, 10).setOffset(-5, -5, 0));
        shape.add(new Rectangle(10, 10).setOffset(-15, -5, 0));
        shape.add(new Rectangle(10, 10).setOffset(-5, 5, 0));
        
        // get your winding right or get inverted shapes
        final Polygon spike = new Polygon();
        final Vec2[] points = new Vec2[] { new Vec2(10, 15), new Vec2(0, 30),
                new Vec2(-10, 15) };
        spike.setPoints(points);
        shape.add(spike);
        
        compound = new DynamicBody(shape, -10.0f, 80.0f);
        compound.setRestitution(0.1f);
        world.add(compound);
        compound.setRotation(0.5f);
        
        Body<?> floor = new StaticBody(new Rectangle(200.0f, 10.0f), -100,
                -55.0f);
        floor.setRestitution(0.1f);
        world.add(floor);
        floor = new StaticBody(new Rectangle(10.0f, 100.0f), -110.0f, -50);
        floor.setRestitution(0.1f);
        world.add(floor);
        floor = new StaticBody(new Rectangle(10.0f, 100.0f), 100.0f, -50);
        floor.setRestitution(0.1f);
        world.add(floor);
        
        return world;
    }
}
