package com.kokakiwi.games.adventures.maths;

/**
 * A three-dimensional vector with float values.
 * 
 * @author Koka El Kiwi
 * @version 0.1.0
 * 
 */
public class Vector2f
{
    /** A unit vector in the X+ direction. */
    public static final Vector2f UNIT_X     = new Vector2f(1f, 0f);
    
    /** A unit vector in the Y+ direction. */
    public static final Vector2f UNIT_Y     = new Vector2f(0f, 1f);
    
    /** A vector containing unity for all components. */
    public static final Vector2f UNIT_XY    = new Vector2f(1f, 1f);
    
    /** A normalized version of UNIT_XYZ. */
    public static final Vector2f NORMAL_XYZ = UNIT_XY.normalize();
    
    /** The zero vector. */
    public static final Vector2f ZERO       = new Vector2f(0f, 0f);
    
    public float                 x;
    public float                 y;
    
    /**
     * Create an empty vector with coordinates [0,0]
     */
    public Vector2f()
    {
        this(0, 0);
    }
    
    /**
     * Create a new three-dimensional vector with coordinates [x,y]
     * 
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     */
    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get vector X coordinate.
     * 
     * @return Vector X coordinate.
     */
    public float getX()
    {
        return x;
    }
    
    /**
     * Set vector X coordinate.
     * 
     * @param x
     *            Vector X coordinate.
     */
    public void setX(float x)
    {
        this.x = x;
    }
    
    /**
     * Get vector Y coordinate.
     * 
     * @return Vector Y coordinate.
     */
    public float getY()
    {
        return y;
    }
    
    /**
     * Set vector Y coordinate.
     * 
     * @param Y
     *            Vector Y coordinate.
     */
    public void setY(float y)
    {
        this.y = y;
    }
    
    /**
     * Set vector coordinates.
     * 
     * @param x
     *            Vector X coordinate.
     * @param y
     *            Vector Y coordinate.
     */
    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Translate a vector with an other vector.
     * 
     * @param vec
     *            Vector.
     * @return this
     */
    public Vector2f translate(Vector2f vec)
    {
        return translate(vec.x, vec.y);
    }
    
    /**
     * Translate a vector
     * 
     * @param x
     *            The translation in x
     * @param y
     *            the translation in y
     * @return this
     */
    public Vector2f translate(float x, float y)
    {
        this.x += x;
        this.y += y;
        
        return this;
    }
    
    /**
     * Adds a vector to this one.
     * 
     * @param vec
     *            The vector.
     * @return this
     */
    public Vector2f add(Vector2f vec)
    {
        return add(vec.x, vec.y);
    }
    
    /**
     * Adds a vector to this one.
     * 
     * @param x
     *            X value
     * @param y
     *            Y value
     * @return this
     */
    public Vector2f add(float x, float y)
    {
        return translate(x, y);
    }
    
    /**
     * Subtracts a vector to this one.
     * 
     * @param vec
     *            The vector.
     * @return this
     */
    public Vector2f subtract(Vector2f vec)
    {
        return subtract(vec.x, vec.y);
    }
    
    /**
     * Subtracts a vector to this one.
     * 
     * @param x
     *            X value
     * @param y
     *            Y value
     * @return this
     */
    public Vector2f subtract(float x, float y)
    {
        return translate(-x, -y);
    }
    
    /**
     * Multiplies this vector by another.
     * 
     * @param vec
     *            The vector.
     * @return this
     */
    public Vector2f mult(Vector2f vec)
    {
        return mult(vec.x, vec.y);
    }
    
    /**
     * Multiplies this vector by another.
     * 
     * @param x
     *            X value
     * @param y
     *            Y value
     * @return this
     */
    public Vector2f mult(float x, float y)
    {
        this.x *= x;
        this.y *= y;
        
        return this;
    }
    
    /**
     * Computes and returns the dot product of this and the specified other
     * vector.
     * 
     * @param vec
     *            The vector.
     * @return dot
     */
    public float dot(Vector2f vec)
    {
        return dot(this, vec);
    }
    
    /**
     * Returns the angle between this vector and the specified other vector.
     * 
     * @param vec
     *            The vector.
     * @return angle
     */
    public float angle(Vector2f vec)
    {
        return angle(this, vec);
    }
    
    /**
     * Linearly interpolates between this and the specified other vector by the
     * supplied amount.
     * 
     * @param vec
     *            The vector.
     * @param t
     *            amount.
     * @return a new vector containing the result.
     */
    public Vector2f lerp(Vector2f vec, float t)
    {
        return lerp(this, vec, t);
    }
    
    /**
     * @return the length squared of the vector
     */
    public float lengthSquared()
    {
        return x * x + y * y;
    }
    
    /**
     * @return the length of the vector
     */
    public final float length()
    {
        return (float) Math.sqrt(lengthSquared());
    }
    
    /**
     * Returns the squared distance from this vector to the specified other.
     * 
     * @param vec
     *            The vector
     * @return the squared distance from this vector to the specified other.
     */
    public float distanceSquared(Vector2f vec)
    {
        final float dx = vec.x - x;
        final float dy = vec.y - y;
        
        return dx * dx + dy * dy;
    }
    
    /**
     * Returns the distance from this vector to the specified other vector.
     * 
     * @param vec
     *            The vector
     * @return the distance from this vector to the specified other vector.
     */
    public final float distance(Vector2f vec)
    {
        return (float) Math.sqrt(distanceSquared(vec));
    }
    
    /**
     * Returns the Manhattan distance between this vector and the specified
     * other.
     * 
     * @param vec
     *            The vector.
     * @return the Manhattan distance between this vector and the specified
     *         other.
     */
    public float manhattanDistance(Vector2f vec)
    {
        return Math.abs(x - vec.x) + Math.abs(y - vec.y);
    }
    
    /**
     * Normalize this vector.
     * 
     * @return the normalized vector
     */
    public Vector2f normalize()
    {
        final float length = length();
        
        return new Vector2f(x / length, y / length);
    }
    
    /**
     * Copy this vector.
     * 
     * @return This vector's copy.
     */
    public Vector2f copy()
    {
        return new Vector2f(x, y);
    }
    
    /**
     * Negates this vector.
     * 
     * @return this
     */
    public Vector2f negate()
    {
        return scale(-1.0f);
    }
    
    /**
     * Scale this vector
     * 
     * @param scale
     *            The scale factor
     * @return this
     */
    public Vector2f scale(float scale)
    {
        x *= scale;
        y *= scale;
        
        return this;
    }
    
    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("Vector2f [x=");
        builder.append(x);
        builder.append(", y=");
        builder.append(y);
        builder.append("]");
        return builder.toString();
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        return result;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Vector2f other = (Vector2f) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
        {
            return false;
        }
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
        {
            return false;
        }
        return true;
    }
    
    /**
     * The dot product of two vectors is calculated as v1.x * v2.x + v1.y * v2.y
     * + v1.z * v2.z
     * 
     * @param a
     *            The first vector
     * @param b
     *            The second vector
     * @return left dot right
     */
    public final static float dot(Vector2f a, Vector2f b)
    {
        return a.x * b.x + a.y * b.y;
    }
    
    /**
     * Calculate the angle between two vectors, in radians
     * 
     * @param a
     *            A vector
     * @param b
     *            The other vector
     * @return the angle between the two vectors, in radians
     */
    public static float angle(Vector2f a, Vector2f b)
    {
        float dls = dot(a, b) / (a.length() * b.length());
        
        if (dls < -1f)
        {
            dls = -1f;
        }
        else if (dls > 1.0f)
        {
            dls = 1.0f;
        }
        
        return (float) Math.acos(dls);
    }
    
    /**
     * Linearly interpolates between two vectors by the supplied amount.
     * 
     * @param va
     *            first vector.
     * @param vb
     *            second vector.
     * @param t
     *            amount.
     * @return result value.
     */
    public static Vector2f lerp(Vector2f va, Vector2f vb, float t)
    {
        return new Vector2f(MathsUtils.lerp(va.x, vb.x, t), MathsUtils.lerp(
                va.y, vb.y, t));
    }
}
