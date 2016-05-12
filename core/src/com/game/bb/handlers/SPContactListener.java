package com.game.bb.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.game.bb.entities.SPPlayer;
import com.game.bb.gamestates.GameState;

import java.util.ArrayList;

/**
 * Created by erik on 08/05/16.
 */
public class SPContactListener implements ContactListener {
    private int footContact = 0, amntJumps = 0;
    private boolean playerDead = false;
    private Array<Body> bodiesToRemove;
    private Fixture killingBullet;

    public SPContactListener() {
        bodiesToRemove = new Array<Body>();
    }


    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getUserData().equals(B2DVars.ID_FOOT) || fb.getUserData().equals(B2DVars.ID_FOOT)) {
            footContact++;
            amntJumps = 0;
        }
        if (fa.getUserData().equals(B2DVars.ID_PLAYER) && fb.getUserData().equals(B2DVars.ID_BULLET)) {
            //Unless player is dead, mark him as dead.
            if (!playerDead) {
                playerDead = true;
                killingBullet = fb;
                bodiesToRemove.add(fb.getBody());
            }
        } else if (fa.getUserData().equals(B2DVars.ID_BULLET) && fb.getUserData().equals(B2DVars.ID_PLAYER)) {
            if (!playerDead) {
                playerDead = true;
                killingBullet = fa;
                bodiesToRemove.add(fa.getBody());
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getUserData().equals(B2DVars.ID_FOOT) || fb.getUserData().equals(B2DVars.ID_FOOT))
            footContact--;
    }

    public Array<Body> getBodiesToRemove() {
        Array<Body> temp = bodiesToRemove;
        if (bodiesToRemove.size > 1)
            System.out.println("Size of bodiesToRemove: " + bodiesToRemove.size);
        bodiesToRemove.clear();
        return temp;
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    public void revivePlayer(){
        playerDead = false;
    }

    public Fixture getKillingBullet() {
        return killingBullet;
    }

    public boolean canJump() {
        if (footContact > 0 || amntJumps < 4) {
            amntJumps++;
            return true;
        }
        return false;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void resetJumps() {
        amntJumps=0;
    }
}
