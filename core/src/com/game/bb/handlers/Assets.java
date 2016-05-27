package com.game.bb.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by erik on 27/05/16.
 */
public class Assets {
    private static HashMap<String, Texture> tex;
    private static HashMap<String, TextureRegion[]> texRegions;
    private static HashMap<String, TextureRegion> texRegion;
    private static Texture background;

    public static Texture getTex(String key){
        return tex.get(key);
    }
    public static Texture getBackground(){
        return background;
    }
    public static TextureRegion[] getAnimation(String key){
        return texRegions.get(key);
    }


    public static void init(){
        tex = new HashMap<String, Texture>();
        texRegions = new HashMap<String, TextureRegion[]>();
        texRegion = new HashMap<String, TextureRegion>();
        background = new Texture("images/spaceBackground.png");
        //textures
        //blue player
        tex.put("blueDeadLeft", new Texture("images/player/bluePlayerDeadLeft.png"));
        tex.put("blueDeadRight", new Texture("images/player/bluePlayerDeadRight.png"));
        tex.put("blueJumpLeft", new Texture("images/player/bluePlayerJumpLeft.png"));
        tex.put("blueJumpRight", new Texture("images/player/bluePlayerJumpRight.png"));
        tex.put("blueStandLeft", new Texture("images/player/bluePlayerStandLeft.png"));
        tex.put("blueStandRight", new Texture("images/player/bluePlayerStandRight.png"));
        //red player
        tex.put("redDeadLeft", new Texture("images/player/redPlayerDeadLeft.png"));
        tex.put("redDeadRight", new Texture("images/player/redPlayerDeadRight.png"));
        tex.put("redJumpLeft", new Texture("images/player/redPlayerJumpLeft.png"));
        tex.put("redJumpRight", new Texture("images/player/redPlayerJumpRight.png"));
        tex.put("redStandLeft", new Texture("images/player/redPlayerStandLeft.png"));
        tex.put("redStandRight", new Texture("images/player/redPlayerStandRight.png"));
        //yellow player
        tex.put("yellowDeadLeft", new Texture("images/player/yellowPlayerDeadLeft.png"));
        tex.put("yellowDeadRight", new Texture("images/player/yellowPlayerDeadRight.png"));
        tex.put("yellowJumpLeft", new Texture("images/player/yellowPlayerJumpLeft.png"));
        tex.put("yellowJumpRight", new Texture("images/player/yellowPlayerJumpRight.png"));
        tex.put("yellowStandLeft", new Texture("images/player/yellowPlayerStandLeft.png"));
        tex.put("yellowStandRight", new Texture("images/player/yellowPlayerStandRight.png"));
        //green player
        tex.put("greenDeadLeft", new Texture("images/player/greenPlayerDeadLeft.png"));
        tex.put("greenDeadRight", new Texture("images/player/greenPlayerDeadRight.png"));
        tex.put("greenJumpLeft", new Texture("images/player/greenPlayerJumpLeft.png"));
        tex.put("greenJumpRight", new Texture("images/player/greenPlayerJumpRight.png"));
        tex.put("greenStandLeft", new Texture("images/player/greenPlayerStandLeft.png"));
        tex.put("greenStandRight", new Texture("images/player/greenPlayerStandRight.png"));
        //bullets
        tex.put("blueBullet", new Texture("images/weapons/blueBullet.png"));
        tex.put("redBullet", new Texture("images/weapons/redBullet.png"));
        tex.put("yellowBullet", new Texture("images/weapons/yellowBullet.png"));
        tex.put("greenBullet", new Texture("images/weapons/greenBullet.png"));

        //animations
        //grenades
        texRegions.put("blueGrenade", TextureRegion.split(new Texture("images/weapons/blueGrenade.png"), 30, 30)[0]);
        texRegions.put("redGrenade", TextureRegion.split(new Texture("images/weapons/redGrenade.png"), 30, 30)[0]);
        texRegions.put("greenGrenade", TextureRegion.split(new Texture("images/weapons/greenGrenade.png"), 30, 30)[0]);
        texRegions.put("yellowGrenade", TextureRegion.split(new Texture("images/weapons/yellowGrenade.png"), 30, 30)[0]);

    }

}
