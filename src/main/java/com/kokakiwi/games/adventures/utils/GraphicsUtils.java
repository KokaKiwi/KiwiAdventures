package com.kokakiwi.games.adventures.utils;

import org.jbox2d.common.Vec2;
import org.newdawn.fizzy.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GraphicsUtils
{
    public static void drawBox(Graphics g, Body<?> body, Vec2 position,
            Color color)
    {
        float x0 = body.getBoundingBox().lowerLeft.x;
        float y0 = body.getBoundingBox().lowerLeft.y;
        float x1 = body.getBoundingBox().upperRight.x;
        float y1 = body.getBoundingBox().upperRight.y;
        float width = x1 - x0;
        float height = y1 - y0;
        
        g.setColor(color);
        g.drawRect(x0, y0, width, height);
    }
}
