package com.kokakiwi.games.adventures.utils;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GraphicsUtils
{
    public static void drawBox(Graphics g, PolygonShape shape, Vec2 position,
            Color color)
    {
        float x0 = position.x + shape.m_centroid.x + shape.m_vertices[0].x;
        float y0 = position.y + shape.m_centroid.y + shape.m_vertices[0].y;
        float x1 = position.x + shape.m_centroid.x + shape.m_vertices[2].x;
        float y1 = position.y + shape.m_centroid.y + shape.m_vertices[2].y;
        float width = x1 - x0;
        float height = y1 - y0;
        
        g.setColor(color);
        g.drawRect(x0, y0, width, height);
    }
}
