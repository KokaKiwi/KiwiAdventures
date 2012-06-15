package org.newdawn.fizzy;

import java.util.ArrayList;
import java.util.List;

/**
 * A shape built up of other shapes
 * 
 * @author kevin
 */
public class CompoundShape implements Shape
{
    /** The children of this compound, i.e. the shapes it's built out of */
    private final List<Shape> children = new ArrayList<Shape>();
    /** The body this shape is being used in if any */
    private Body<?>           body;
    
    /**
     * Create a new shape built up of a set of others
     */
    public CompoundShape()
    {
    }
    
    public Body<?> getBody()
    {
        return body;
    }
    
    /**
     * Add a shape to build up this compound
     * 
     * @param child
     *            The shape to be added
     */
    public void add(Shape child)
    {
        children.add(child);
    }
    
    /**
     * Get the number of shapes in the compound
     * 
     * @return The number of shapes in the compound
     */
    public int getShapeCount()
    {
        return children.size();
    }
    
    /**
     * Get a sub-shape of this compound at the given index
     * 
     * @param i
     *            The index of the shape to retrieve
     * @return The shape at the requested index
     */
    public Shape getShape(int i)
    {
        return children.get(i);
    }
    
    public void createInBody(Body<?> body)
    {
        for (final Shape child : children)
        {
            child.createInBody(body);
        }
    }
    
    public List<org.jbox2d.collision.shapes.Shape> getJBoxShapes()
    {
        final List<org.jbox2d.collision.shapes.Shape> shapes = new ArrayList<org.jbox2d.collision.shapes.Shape>();
        
        for (int i = 0; i < children.size(); i++)
        {
            shapes.addAll(children.get(i).getJBoxShapes());
        }
        
        return shapes;
    }
    
    public void setDensity(float density)
    {
        for (final Shape child : children)
        {
            child.setDensity(density);
        }
    }
    
    public void setFriction(float friction)
    {
        for (final Shape child : children)
        {
            child.setFriction(friction);
        }
    }
    
    public void setRestitution(float rest)
    {
        for (final Shape child : children)
        {
            child.setRestitution(rest);
        }
    }
    
}
