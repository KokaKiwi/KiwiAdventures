package com.kokakiwi.games.adventures;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.kokakiwi.games.adventures.game.Board;
import com.kokakiwi.games.adventures.game.Player;
import com.kokakiwi.games.adventures.game.Tile;
import com.kokakiwi.games.adventures.utils.SystemUtils;

public class Main
{
    public static float scale = 3.0f;
    
    private final MainGame         game;
    private final AppGameContainer container;
    
    private final Board            board;
    
    public Main() throws SlickException
    {
        // Init LWJGL
        File nativesDir = new File("natives" + File.separator
                + SystemUtils.getSystemOS().name());
        System.setProperty("org.lwjgl.librarypath",
                nativesDir.getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath",
                nativesDir.getAbsolutePath());
        
        game = new MainGame(this);
        
        container = new AppGameContainer(game);
        container.setDisplayMode(1024, 768, false);
        
        board = new Board();
        
        for (float x = 0; x < 1024; x += 16 * scale)
        {
            Tile tile = new Tile(board, x, 768 - (16 * scale));
            board.addChild(tile);
        }
        
        for(float x = 16 * 10 * scale; x < 16 * 15 * scale; x += 16  * scale)
        {
            Tile tile = new Tile(board, x, 768 - (16 * 5 * scale));
            board.addChild(tile);
        }
        
        Player player = new Player(board, 1024 / 2, 768 - (16 * scale) - (24 * scale) - 200);
        board.addChild(player);
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
        catch (SlickException e)
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
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
