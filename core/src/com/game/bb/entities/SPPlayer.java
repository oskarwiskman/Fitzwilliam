package com.game.bb.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.game.bb.handlers.B2DVars;

/**
 * Created by erik on 09/05/16.
 */
public class SPPlayer extends SPSprite {

    private Sound sound;
    private Texture onGroundRight, inAirRight, onGroundLeft, inAirLeft, deadRight, deadLeft;
    private boolean onGround = true, isDead = false;
    private float textureTimer = 0;
    private String id;
    private int xOffset = 23, yOffset=25;

    public SPPlayer(World world, float xPos, float yPos, short bodyBIT, String bodyType, String id, String color) {
        super(world);
        createPlayer(xPos, yPos, bodyBIT, bodyType);
        this.id=id;
        sound = Gdx.audio.newSound(Gdx.files.internal("sfx/jump.wav"));
        loadTexture(color);
        setTexture(onGroundRight);
    }

    public void jump(float xForce, float yForce, float xPos, float yPos){
        if(!isDead) {
            body.setTransform(xPos, yPos, 0);
            body.setLinearVelocity(0, 0);
            body.applyForceToCenter(xForce, yForce, true);
            textureTimer = 0;
            onGround = false;
            if (xForce < 0) {
                setTexture(inAirLeft);
            } else {
                setTexture(inAirRight);
            }
            sound.play();
        }
    }

    public void kill(){
        isDead = true;
        setTexture(deadRight);
    }

    public void revive(){
        isDead = false;
        setTexture(onGroundRight);
    }

    public String getId(){
        return id;
    }

    @Override
    public void render(SpriteBatch sb){
        if(texture != null) {
            sb.begin();
            float x = body.getPosition().x * B2DVars.PPM - B2DVars.PLAYER_WIDTH;
            float y = body.getPosition().y * B2DVars.PPM - B2DVars.PLAYER_HEIGHT;
            if(textureTimer>=0.5f && !onGround){
                if(texture.equals(inAirLeft)){
                    setTexture(onGroundLeft);
                    xOffset = 30;
                }
                else{
                    setTexture(onGroundRight);
                    xOffset = 23;
                }
                onGround=true;
            }
            sb.draw(texture, x-xOffset, y-yOffset, 54, 48);
            sb.end();
        }
    }

    @Override
    public void update(float dt){
        textureTimer+=dt;
    }

    public boolean isDead() {
        return isDead;
    }

    private void createPlayer(float xPos, float yPos, short bodyCategory, String bodyID){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(B2DVars.PLAYER_WIDTH, B2DVars.PLAYER_HEIGHT);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = bodyCategory;
        if (bodyID.equals(B2DVars.ID_PLAYER))
            fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_BULLET;
        else
            fdef.filter.maskBits = B2DVars.BIT_GROUND;
        BodyDef bdef = new BodyDef();
        bdef.position.set(xPos / B2DVars.PPM, yPos / B2DVars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        body.createFixture(fdef).setUserData(bodyID);

        //add foot
        if (bodyID.equals(B2DVars.ID_PLAYER)) {
            shape.setAsBox(B2DVars.PLAYER_WIDTH - 2/B2DVars.PPM, 4 / B2DVars.PPM, new Vector2(0, -B2DVars.PLAYER_HEIGHT), 0);
            fdef.shape = shape;
            fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
            fdef.filter.maskBits = B2DVars.BIT_GROUND;
            fdef.isSensor = true;
            body.createFixture(fdef).setUserData(B2DVars.ID_FOOT);
        }
    }

    private void loadTexture(String color){
        if(color.equals("blue")){
            onGroundRight = new Texture("images/player/bluePlayerStandRight.png");
            inAirRight = new Texture("images/player/bluePlayerJumpRight.png");
            onGroundLeft = new Texture("images/player/bluePlayerStandLeft.png");
            inAirLeft = new Texture("images/player/bluePlayerJumpLeft.png");
            deadRight = new Texture("images/player/bluePlayerDeadRight.png");
            deadLeft = new Texture("images/player/bluePlayerDeadLeft.png");
        }
        else {
            onGroundRight = new Texture("images/player/redPlayerStandRight.png");
            inAirRight = new Texture("images/player/redPlayerJumpRight.png");
            onGroundLeft = new Texture("images/player/redPlayerStandLeft.png");
            inAirLeft = new Texture("images/player/redPlayerJumpLeft.png");
            deadRight = new Texture("images/player/redPlayerDeadRight.png");
            deadLeft = new Texture("images/player/redPlayerDeadLeft.png");
        }
    }
}
