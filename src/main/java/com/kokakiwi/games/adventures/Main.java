package com.kokakiwi.games.adventures;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.kokakiwi.games.adventures.game.Board;
import com.kokakiwi.games.adventures.utils.SystemUtils;

public class Main
{
    public static float            scale = 1.0f;
    
    private final MainGame         game;
    private final AppGameContainer container;
    
    private final Board            board;
    
    public Main() throws SlickException
    {
        // Init LWJGL
        final File nativesDir = new File("natives" + File.separator
                + SystemUtils.getSystemOS().name());
        System.setProperty("org.lwjgl.librarypath",
                nativesDir.getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath",
                nativesDir.getAbsolutePath());
        
        game = new MainGame(this);
        
        container = new AppGameContainer(game);
        container.setDisplayMode(1024, 768, false);
        
        board = new Board();
    }
    
    public MainGame getGame()
    {
        return game;
    }
    
    public AppGameContainer getContainer()
    {
        return container;
    }
    
    public Board getBoard()
    {
        return board;
    }
    
    public void start()
    {
        try
        {
            container.start();
        }
        catch (final SlickException e)
        {
            e.printStackTrace();
        }
    }
    
    public void stop()
    {
        container.destroy();
    }
    
    public static void main(String[] args)
    {
        try
        {
            new Main().start();
        }
        catch (final SlickException e)
        {
            e.printStackTrace();
        }
    }
}
