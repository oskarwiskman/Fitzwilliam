package com.game.bb.net.packets;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by erik on 17/05/16.
 */
public class EntityPacket {
    public String action;
    public Vector2 pos, force;
    public float dir;
    public String myID;
    public String entityID;
    public String[] misc;
    public long cTime;
    public float time;

    public EntityPacket(){

    }
}
