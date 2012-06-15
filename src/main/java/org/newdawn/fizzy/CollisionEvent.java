package org.newdawn.fizzy;

/**
 * An event structure decribing the collision of two bodies in the world
 * simulation
 * 
 * @author kevin
 */
public class CollisionEvent
{
    /** The first body in the collision */
    private final Body<?>      bodyA;
    /** The second body in the collision */
    private final Body<?>      bodyB;
    private final FizzyContact contact;
    
    /**
     * Create a new event
     * 
     * @param bodyA
     *            The first body in the collision
     * @param bodyB
     *            The second body in the collision
     */
    CollisionEvent(Body<?> bodyA, Body<?> bodyB, FizzyContact contact)
    {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.contact = contact;
    }
    
    /**
     * Get the first body taking part in the collision
     * 
     * @return The first body taking part in the collision
     */
    public Body<?> getBodyA()
    {
        return bodyA;
    }
    
    /**
     * Get the second body taking part in the collision
     * 
     * @return The second body taking part in the collision
     */
    public Body<?> getBodyB()
    {
        return bodyB;
    }
    
    public FizzyContact getContact()
    {
        return contact;
    }
    
    /**
     * Check if this event relates to the given body
     * 
     * @param current
     *            The body to check
     * @return True if this event relates to the given body
     */
    public boolean contains(Body<?> current)
    {
        return bodyA == current || bodyB == current;
    }
}
