package com.kokakiwi.games.adventures.maths;

import org.jbox2d.common.Vec2;

public class VectorsUtils
{
    public static Vec2 toVec2(Vector2f vec)
    {
        return new Vec2(vec.x, vec.y);
    }
    
    public static Vector2f toVector2f(Vec2 vec)
    {
        return new Vector2f(vec.x, vec.y);
    }
    
    public static Vector2f toCoreVector(Vec2 vec, float width, float height)
    {
        return new Vector2f(vec.x - (width / 2), vec.y - (height / 2));
    }
    
    public static Vec2 toWorldVector(Vector2f vec, float width, float height)
    {
        return new Vec2(vec.x + (width / 2), vec.y + (height / 2));
    }
}
