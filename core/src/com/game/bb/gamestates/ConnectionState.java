package com.game.bb.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.game.bb.handlers.B2DVars;
import com.game.bb.handlers.GameStateManager;

/**
 * Created by erik on 12/05/16.
 */
public class ConnectionState extends GameState {

    private SPButton hostButton, joinButton;
    private World world;
    private Texture background = new Texture("images/spaceBackground.png");
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sfx/levelselect.wav"));
    private Array<FallingBody> itRains;
    private float newFallingBody = 0f;


    public ConnectionState(GameStateManager gsm) {
        super(gsm);

        world = new World(new Vector2(0, -9.81f), true);
        itRains = new Array<FallingBody>();

        hostButton = new SPButton(new Texture("images/button/hostButton.png"), B2DVars.CAM_WIDTH/2, (B2DVars.CAM_HEIGHT/2)+100, cam);
        joinButton = new SPButton(new Texture("images/button/joinButton.png"), B2DVars.CAM_WIDTH/2, (B2DVars.CAM_HEIGHT/2), cam);
        fallingBody();
    }

    public void fallingBody(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20 / B2DVars.PPM, 20 / B2DVars.PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape=shape;
        BodyDef bdef = new BodyDef();
        bdef.position.set( (float) (B2DVars.CAM_WIDTH*Math.random()) , B2DVars.CAM_HEIGHT );
        bdef.type= BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bdef);
        itRains.add(new FallingBody(body));
    }


    @Override
    public void handleInput() {
        if (hostButton.isClicked()) {
            sound.play();
            gsm.hostGame(true);
            gsm.setState(GameStateManager.PLAY);
        }
        if(joinButton.isClicked()){
            sound.play();
            gsm.hostGame(false);
            gsm.setState(GameStateManager.PLAY);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (newFallingBody > 1f){
            fallingBody();
            newFallingBody = 0f;
        } else {
            newFallingBody+=dt;
        }
        world.step(dt, 6, 2);
        hostButton.update(dt);
        joinButton.update(dt);
    }

    @Override
    public void render() {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.end();
        for (FallingBody body : itRains)
            body.render(sb);
        hostButton.render(sb);
        joinButton.render(sb);
    }

    @Override
    public void dispose() {

    }

    public class FallingBody{
        private Texture texture = new Texture("images/player/bluePlayerJumpLeft.png");
        private Body body;
        public FallingBody(Body body){
            this.body=body;
        }

        public void render(SpriteBatch sb){
            sb.begin();
            sb.draw(texture, body.getPosition().x, body.getPosition().y, 50, 44);
            sb.end();
        }
    }
}