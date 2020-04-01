package com.mygdx.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGame;
import com.mygdx.screens.GamePlay;

public class Player extends Sprite {
    private static boolean krzyk;
    private int kondom;
    private float timer;
    private float timer3;
    static boolean cios;
    private static float timer2, timer4, timer5;
    public static boolean kon, atk1;

    public static void krzyk() {
        krzyk = true;
        timer2 += Gdx.graphics.getDeltaTime();
        if (timer2 > 3)
            krzyk = false;
    }

    public static void sen() {
        kon = true;
        timer4 += Gdx.graphics.getDeltaTime();
        if (timer4 > 3)
            kon = false;
    }

    public static void atak() {
        cios = MathUtils.randomBoolean();
        atk1 = true;
        timer5 += Gdx.graphics.getDeltaTime();
        if (timer5 > 3)
            atk1 = false;
    }

    public enum State {FALLING, JUMPING, STANDING, RUNNING, GROWING, DEAD, KICK, KRZYK, SEN, ATAK, ATAK2}

    public static State currentState;
    public static State previousState;
    private float stateTimer;
    private boolean runningRight;
    private TextureAtlas atlas_ruch;
    private TextureAtlas player_skok;
    private TextureAtlas player_krzyk;
    private TextureAtlas player_sen;
    private TextureAtlas player_atk1;
    private TextureAtlas player_atk2;

    private World world;
    private GamePlay screen;
    public static Body b2body;
    float positionX;
    float positionY;

    private TextureRegion playerStand, playerSkok;
    private TextureAtlas atlas;
    private Animation graczRun, graczHop, graczKrzyk, graczSen, graczAtak1, gracz_atak2;

    public Player(float positionX, float positionY, World world, GamePlay screen) {
        this.world = world;
        this.screen = screen;
        this.positionX = positionX;
        this.positionY = positionY;

        kon = false;
        krzyk = false;
        atk1 = false;
        timer = 0;
        timer2 = 0;
        timer5 = 0;


        currentState = State.STANDING;
        previousState = State.STANDING;
        timer3 = 0;
        timer4 = 0;
        stateTimer = 0;
        runningRight = true;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        atlas = new TextureAtlas("sprites/player.pack");
        atlas_ruch = new TextureAtlas("sprites/player_ruch.pack");
        player_skok = new TextureAtlas("sprites/player_skok.pack");
        player_krzyk = new TextureAtlas("sprites/player_krzyk.pack");
        player_sen = new TextureAtlas("sprites/sen.pack");
        player_atk1 = new TextureAtlas("sprites/player_atak1.pack");
        player_atk2 = new TextureAtlas("sprites/player_atak2.pack");


        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(player_skok.findRegion("player_skok"), i * 128, 0, 128, 128));
        graczHop = new Animation(0.1f, frames);

        frames.clear();

        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(atlas_ruch.findRegion("player_ruch"), i * 128, 0, 128, 128));
        graczRun = new Animation(0.1f, frames);

        frames.clear();

        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(player_krzyk.findRegion("player_krzyk"), i * 128, 0, 128, 128));
        graczKrzyk = new Animation(0.1f, frames);

        frames.clear();

        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(player_sen.findRegion("SEN"), i * 128, 0, 128, 128));
        graczSen = new Animation(0.1f, frames);

        frames.clear();

        for (int i = 1; i < 5; i++)
            frames.add(new TextureRegion(player_atk1.findRegion("player_atak"), i * 128, 0, 128, 128));
        graczAtak1 = new Animation(0.05f, frames);

        frames.clear();

        for (int i = 1; i < 5; i++)
            frames.add(new TextureRegion(player_atk2.findRegion("player_atak2"), i * 128, 0, 128, 128));
        gracz_atak2 = new Animation(0.1f, frames);

        frames.clear();


        playerStand = new TextureRegion(atlas.findRegion("player1"), 0, 0, 128, 128);
        playerStand.flip(true, false);
        setRegion(playerStand);
        setBounds(0, 0, 128, 128);

        definePlayer();

    }


    public void definePlayer() {
        BodyDef bdef;

        bdef = new BodyDef();
        bdef.position.set(positionX, positionY);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(50);
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("player");

    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

    }


    public TextureRegion getFrame(Float dt) {
        currentState = getState();

        TextureRegion region = playerStand;


        switch (currentState) {
            case JUMPING:
                region = (TextureRegion) graczHop.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                region = (TextureRegion) graczRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
                break;

            case SEN:
                region = (TextureRegion) graczSen.getKeyFrame(stateTimer, false);
                break;

            case KRZYK:
                region = (TextureRegion) graczKrzyk.getKeyFrame(stateTimer, true);
                break;

            case ATAK:
                    region = (TextureRegion) graczAtak1.getKeyFrame(stateTimer, true);
                break;

            case ATAK2:
                region = (TextureRegion) gracz_atak2.getKeyFrame(stateTimer, true);
                break;


            case STANDING:
            default:
                region = playerStand;

                break;
        }


        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }


        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }


    public State getState() {



        timer += Gdx.graphics.getDeltaTime();

        if ((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        } else if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;

        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;

        else if (krzyk) {
            MyGame.manager.get("sounds/ooo.mp3", Music.class).play();

            timer2 += Gdx.graphics.getDeltaTime();

            if (timer2 < 3) {

                return State.KRZYK;
            } else {
                MyGame.manager.get("sounds/ooo.mp3", Music.class).pause();
                krzyk = false;
                timer2 = 0;
                return State.STANDING;
            }


        } else if (kon) {
            timer4 += Gdx.graphics.getDeltaTime();

            if (timer4 < 3) {


                MyGame.manager.get("sounds/sex.mp3", Music.class).play();
                return State.SEN;
            } else {
                timer4 = 0;
                kon = false;
                MyGame.manager.get("sounds/sex.mp3", Music.class).pause();
                return State.STANDING;
            }
        } else if (atk1) {


            timer5 += Gdx.graphics.getDeltaTime();

            if (timer5 < 0.5f) {


                if (cios)
                    return State.ATAK;

                    if (!cios)
                        return State.ATAK2;
                return State.STANDING;

            } else {
                atk1 = false;
                timer5 = 0;
                return State.STANDING;
            }
        } else
            return State.STANDING;


    }

}



