package com.game.bb.handlers;

import com.game.bb.gamestates.StartScreenState;
import com.game.bb.gamestates.PlayState;
import com.game.bb.main.Game;
import com.game.bb.gamestates.GameState;
import com.game.bb.net.NetworkMonitor;

import java.util.Stack;

/**
 * Created by erik on 06/05/16.
 */
public class GameStateManager {
    private Game game;
    private Stack<GameState> states;
    public static final int PLAY = 1, START_SCREEN = 2, CONNECTION_STATE = 3;


    public GameStateManager(Game game) {
        this.game = game;
        states = new Stack<GameState>();
        pushState(START_SCREEN);
        //pushState(PLAY); //remove this later
    }

    public Game game() {
        return game;
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render() {
        states.peek().render();
    }

    private GameState getState(int state) {
        if (state == PLAY) {
            return new PlayState(this);
        } else if (state == START_SCREEN) {
            return new StartScreenState(this);
        }
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        states.push(getState(state));
    }

    public void popState() {
        states.pop().dispose();
    }
}

