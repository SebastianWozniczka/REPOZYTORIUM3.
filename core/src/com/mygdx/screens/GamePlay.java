package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Sprites.Player;
import com.mygdx.game.MyGame;
import com.mygdx.scenes.Hud;

import static com.mygdx.scenes.Hud.skok;


public class GamePlay implements Screen {

    Rectangle rectangle1;
    Rectangle rectangle2;

    private float timer2;

    private final MyGame game;
    private float timer,timer3;
    private Hud hud;
    public static OrthographicCamera cam;
    private Viewport gamePort;

    private TiledMap map;
    private TmxMapLoader loader;
    private Box2DDebugRenderer b2dr;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private static Player player;

    private Texture swiecznik1,swiecznik2,swiecznik3,swiecznik4,swiecznik5,swiecznik6,swiecznik7,swiecznik8,render;


    public GamePlay(MyGame game){
        this.game=game;

        init();
        timer=0;
        timer2=0;
        timer3=0;
        MyGame.manager.get("sounds/intro.mp3", Music.class).play();

        cam=new OrthographicCamera();
        gamePort = new StretchViewport(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, cam);
        loader=new TmxMapLoader();
        map = loader.load("map/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world=new World(new Vector2(0,-9.87f),true);
        b2dr=new Box2DDebugRenderer();

       getPlayer();
        hud=new Hud(game.batch);
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for (MapObject object : map.getLayers().get("ziemia").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) , (rect.getY() + rect.getHeight() / 2) );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 , rect.getHeight() / 2 );
            fdef.shape = shape;


            body.createFixture(fdef);
        }

        rectangle1=map.getLayers().get("swiecznik1").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle();
        rectangle2=map.getLayers().get("swiecznik2").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle();

    }

    private void init(){
        swiecznik1=new Texture("menu/ogien1.png");
        swiecznik2=new Texture("menu/ogien2.png");
        swiecznik3=new Texture("menu/ogien3.png");
        swiecznik4=new Texture("menu/ogien4.png");
        swiecznik5=new Texture("menu/ogien5.png");
        swiecznik6=new Texture("menu/ogien6.png");
        swiecznik7=new Texture("menu/ogien7.png");
        swiecznik8=new Texture("menu/ogien8.png");
    }

    public void getPlayer(){
        final Rectangle rectangle=map.getLayers().get("player").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle();
        player=new Player(rectangle.getX(),rectangle.getY(),world,this);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
            Gdx.gl.glClearColor(0, 0, 100, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            renderer.render();
            update(delta);

            game.batch.setProjectionMatrix(cam.combined);
           // b2dr.render(world,cam.combined);

            game.batch.begin();
            game.batch.draw(render,rectangle1.getX(),rectangle1.getY(),rectangle1.getWidth(),rectangle1.getHeight());
            game.batch.draw(render,rectangle2.getX(),rectangle2.getY(),rectangle2.getWidth(),rectangle2.getHeight());
            player.draw(game.batch);
            game.batch.end();
            hud.stage.draw();

    }

    private void update(float delta) {
        timer+=delta;
        world.step(1 / 10f, 6, 2);
        cam.update();
        renderer.setView(cam);
        hud.update(delta);


        player.update(delta);

        handleInput(delta);
        if(timer<0.5f) {
            Gdx.gl.glClearColor(255, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        if (timer>6){
            MyGame.manager.get("sounds/intro.mp3", Music.class).pause();
            MyGame.manager.get("sounds/menu.mp3", Music.class).play();
            MyGame.manager.get("sounds/menu.mp3", Music.class).setLooping(true);
        }


        render=swiecznik1;





            timer2 += delta;



                if (timer2 >0.1f) {
            render = swiecznik1;
        }
        if (timer2 > 0.2f) {
            render = swiecznik2;
        }
        if (timer2 > 0.3f) {
            render = swiecznik3;
        }
        if (timer2 > 0.2f) {
            render = swiecznik4;
        }
        if (timer2 > 0.3f) {
            render = swiecznik5;
        }
        if (timer2 > 0.4f) {
            render = swiecznik6;
        }
        if (timer2 > 0.5f) {
            render = swiecznik7;
        }
        if (timer2 > 0.6) {
            render = swiecznik8;
        }
        if (timer2 >0.7f) {
           timer2=0;
           timer2+=delta;
        }

if(skok){

    timer3+=delta;
    if(timer3>0.5f){
        cam.rotate(0.1f);

    }
   if(timer3>0.6f){
        cam.rotate(-0.17f);

    }

     if(timer3>.7f){
        cam.rotate(0.0f);
        timer3=0;
        skok=false;
    }




}

    }

    public static void handleInput(float delta) {
        if(Gdx.input.getX()>Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/10){
            cam.position.x+=1;
        }
        else if(Gdx.input.getX()<Gdx.graphics.getWidth()/10){
            {
                cam.position.x-=1;
            }

        }else
        cam.position.x=player.b2body.getPosition().x;
        cam.position.y=player.b2body.getPosition().y;


    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
    }
}
