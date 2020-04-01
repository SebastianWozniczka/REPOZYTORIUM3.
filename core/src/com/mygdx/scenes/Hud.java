package com.mygdx.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Sprites.Player;
import com.mygdx.game.MyGame;

public class Hud implements Disposable , GestureDetector.GestureListener {

    public boolean pSkoku;
    public Stage stage;
    private Viewport viewport;

    public static Button przycisk_pojscia_w_tyl,przycisk_pojscia_w_przod,przycisk_krzyku,przycisk_snu,przycisk_ataku;
    Image image1;
    Image image2;
    public static boolean skok;




    private int stanMany;
    public static int stanZycia;
    public static int stan_Zycia_przeciwnika;
    public static int stan_many_przeciwnika;
    public static int stan_odjecia_many;
    public static int regeneracja_many;
    public static int strata_many;



    private Label countdownLabel,manaLabel,nazwaLabel,zycie_przeciwnika_label,stan_many_przeciwnika_label,nazwa_przeciwnika,odjecieMany,regMana;
    Image mana,zywotnosc,pasekNazwy,pasek_pancerza,zycie_przeciwnika,pancerz_parzeciwnika,mana_przeciwnika,pasek_nazwy_przeciwnika,przycisk_skoku,mala_mana;

    public Hud(SpriteBatch sb){

        staty();
        skok=false;
        przycisk_pojscia_w_tyl=new Button(new Button.ButtonStyle(preparePrzycisk(1)));
        przycisk_pojscia_w_tyl.setX(Gdx.graphics.getWidth()*5/12);
        przycisk_pojscia_w_tyl.setY(Gdx.graphics.getWidth()/4);
        przycisk_pojscia_w_tyl.setWidth(Gdx.graphics.getWidth()/16);
        przycisk_pojscia_w_tyl.setHeight(Gdx.graphics.getHeight()/12);


        przycisk_pojscia_w_przod=new Button(new Button.ButtonStyle(preparePrzycisk(2)));
        przycisk_pojscia_w_przod.setX(Gdx.graphics.getWidth()*47/80);
        przycisk_pojscia_w_przod.setY(Gdx.graphics.getWidth()/4);
        przycisk_pojscia_w_przod.setWidth(Gdx.graphics.getWidth()/16);
        przycisk_pojscia_w_przod.setHeight(Gdx.graphics.getHeight()/12);

        przycisk_krzyku=new Button(new Button.ButtonStyle(preparePrzycisk(3)));
        przycisk_krzyku.setX(Gdx.graphics.getWidth()/3);
        przycisk_krzyku.setY(Gdx.graphics.getHeight()/2);
        przycisk_krzyku.setWidth(Gdx.graphics.getWidth()/16);
        przycisk_krzyku.setHeight(Gdx.graphics.getHeight()/12);


        przycisk_snu=new Button(new Button.ButtonStyle(preparePrzycisk(4)));
        przycisk_snu.setX(Gdx.graphics.getWidth()/2);
        przycisk_snu.setY(Gdx.graphics.getWidth()/3);
        przycisk_snu.setWidth(Gdx.graphics.getWidth()/16);
        przycisk_snu.setHeight(Gdx.graphics.getHeight()/12);

        przycisk_ataku=new Button(new Button.ButtonStyle(preparePrzycisk(5)));
        przycisk_ataku.setX(Gdx.graphics.getWidth()/1.5f);
        przycisk_ataku.setY(Gdx.graphics.getWidth()/2);
        przycisk_ataku.setWidth(Gdx.graphics.getWidth()/16);
        przycisk_ataku.setHeight(Gdx.graphics.getHeight()/12);








        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);


        Table table = new Table();

        table.top();

        table.setFillParent(true);


        countdownLabel = new Label(String.format("%03d", stanZycia), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownLabel.setPosition(Gdx.graphics.getWidth()*3/25,Gdx.graphics.getHeight()*7/60,100);
        countdownLabel.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);

        nazwa_przeciwnika = new Label(String.format("Hemdall"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nazwa_przeciwnika.setPosition(Gdx.graphics.getWidth()*9/10,Gdx.graphics.getHeight()*1/20,100);
        nazwa_przeciwnika.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);

        manaLabel = new Label(String.format("%02d", stanMany), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manaLabel.setPosition(Gdx.graphics.getWidth()*67/400,Gdx.graphics.getHeight()*7/60,100);
        manaLabel.setSize(Gdx.graphics.getWidth()/500,Gdx.graphics.getHeight()/37.5f);

        nazwaLabel = new Label(String.format("Jarek"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nazwaLabel.setPosition(Gdx.graphics.getWidth()*9/80,Gdx.graphics.getHeight()*1/20,100);
        nazwaLabel.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);




        stan_many_przeciwnika_label = new Label(String.format("%02d", stan_many_przeciwnika), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stan_many_przeciwnika_label.setPosition(Gdx.graphics.getWidth()*678/800,Gdx.graphics.getHeight()*70/600,100);
        stan_many_przeciwnika_label.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);


        zycie_przeciwnika_label = new Label(String.format("%02d",stan_Zycia_przeciwnika ), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        zycie_przeciwnika_label.setPosition(Gdx.graphics.getWidth()*134/800,Gdx.graphics.getHeight()*70/600,100);
        zycie_przeciwnika_label.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);

        odjecieMany = new Label(String.format("%02d", stan_odjecia_many), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        odjecieMany.setPosition(Gdx.graphics.getWidth()*390/800,Gdx.graphics.getHeight()*370/600,10);
        odjecieMany.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);

        regMana = new Label( "+"+regeneracja_many, new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        regMana.setPosition(Gdx.graphics.getWidth()*134/800,Gdx.graphics.getHeight()*70/600,10);
        regMana.setSize(Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/37.5f);



        mana=new Image(new Texture("menu/ampulka_many.png"));
        zywotnosc=new Image(new Texture("menu/pasek_zdrowia.png"));
        pasekNazwy=new Image(new Texture("menu/pasek_nazwy.png"));
        pasek_pancerza=new Image(new Texture("menu/pasek_pancerza.png"));
        zycie_przeciwnika=new Image(new Texture("menu/pasek_zdrowia.png"));
        pancerz_parzeciwnika=new Image(new Texture("menu/pasek_pancerza.png"));
        mana_przeciwnika=new Image(new Texture("menu/ampulka_many.png"));
        pasek_nazwy_przeciwnika=new Image(new Texture("menu/pasek_nazwy.png"));
        mana=new Image(new Texture("menu/ampulka_many.png"));
        przycisk_skoku=new Image(new Texture("menu/przycisk_skoku.png"));
        mala_mana=new Image(new Texture("menu/ampulka_many.png"));
        image1=new Image(new Texture("menu/w_lewo.png"));
        image2=new Image(new Texture("menu/w_prawo.png"));

        zywotnosc.setSize(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/12);
        zywotnosc.setPosition(Gdx.graphics.getWidth()*55/600,Gdx.graphics.getHeight()*50/600);
        mana.setSize(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/12);
        mana.setPosition(Gdx.graphics.getWidth()*110/800,Gdx.graphics.getHeight()*50/600);
        pasekNazwy.setSize(Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getHeight()/20);
        pasekNazwy.setPosition(Gdx.graphics.getWidth()*10/800,Gdx.graphics.getHeight()*20/600);
        pasek_nazwy_przeciwnika.setSize(Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getHeight()/20);
        pasek_nazwy_przeciwnika.setPosition(Gdx.graphics.getWidth()*790/800,Gdx.graphics.getHeight()*50/600);
        pasek_nazwy_przeciwnika.rotateBy(180);
        pasek_pancerza.setSize(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/12);
        pasek_pancerza.setPosition(Gdx.graphics.getWidth()*10/800,Gdx.graphics.getHeight()*50/600);
        pasek_pancerza.setColor(250,250,250,.5f);
        zycie_przeciwnika.setSize(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/12);
        zycie_przeciwnika.setPosition(Gdx.graphics.getWidth()*700/800,Gdx.graphics.getHeight()*50/600);
        pancerz_parzeciwnika.setSize(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/12);
        pancerz_parzeciwnika.setPosition(Gdx.graphics.getWidth()*750/800,Gdx.graphics.getHeight()*50/600);
        pancerz_parzeciwnika.setColor(250,250,250,.5f);
        zycie_przeciwnika_label.setPosition(Gdx.graphics.getWidth()*715/800,Gdx.graphics.getHeight()*70/600);
        zycie_przeciwnika_label.setSize(Gdx.graphics.getWidth()/80,Gdx.graphics.getHeight()/60);
        mana_przeciwnika.setPosition(Gdx.graphics.getWidth()*655/800,Gdx.graphics.getHeight()*50/600);
        mana_przeciwnika.setSize(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/12);
        przycisk_skoku.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        przycisk_skoku.setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/4);
        mala_mana.setPosition(Gdx.graphics.getWidth()*400/800,Gdx.graphics.getHeight()*350/600);
        mala_mana.setSize(Gdx.graphics.getWidth()/40,Gdx.graphics.getHeight()/30);







        stage.addActor(mana);
        stage.addActor(zywotnosc);
        stage.addActor(countdownLabel);
        stage.addActor(manaLabel);
        stage.addActor(pasekNazwy);
        stage.addActor(nazwaLabel);
        stage.addActor(pasek_pancerza);
        stage.addActor(zycie_przeciwnika);
        stage.addActor(pancerz_parzeciwnika);
        stage.addActor(zycie_przeciwnika_label);
        stage.addActor(mana_przeciwnika);
        stage.addActor(stan_many_przeciwnika_label);
        stage.addActor(pasek_nazwy_przeciwnika);
        stage.addActor(nazwa_przeciwnika);
        stage.addActor(przycisk_pojscia_w_tyl);
        stage.addActor(przycisk_pojscia_w_przod);
        stage.addActor(przycisk_skoku);
        stage.addActor(przycisk_krzyku);
        stage.addActor(przycisk_snu);
        stage.addActor(przycisk_ataku);
        //stage.addActor(mala_mana);
       // stage.addActor(odjecieMany);
       // stage.addActor(regMana);






        click();
        Gdx.input.setInputProcessor(stage);
    }

    private void click() {


        przycisk_krzyku.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("git","jest");
                Player.krzyk();
                return true;
            }
        });

        przycisk_pojscia_w_tyl.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("git","jest");
                MyGame.manager.get("sounds/chod.mp3", Music.class).play();
                Player.b2body.applyLinearImpulse(new Vector2(-35,0),Player.b2body.getWorldCenter(),true);
                return true;
            }
        });

        przycisk_pojscia_w_przod.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("git","jest");
                MyGame.manager.get("sounds/chod.mp3", Music.class).play();
                Player.b2body.applyLinearImpulse(new Vector2(35,0),Player.b2body.getWorldCenter(),true);
                return true;
            }
        });

        przycisk_snu.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manaLabel.setText( stanMany+=regeneracja_many);
                Player.sen();
                stage.addActor(przycisk_pojscia_w_przod);
                stage.addActor(przycisk_pojscia_w_tyl);
                stage.addActor(przycisk_skoku);
                stage.addActor(przycisk_ataku);
                return true;
            }
        });



        przycisk_skoku.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("git","jest");
                manaLabel.setText( stanMany-=stan_odjecia_many);
                pSkoku=true;



                image1.setPosition(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/2);
                image2.setPosition(Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/2);
                image1.setSize(Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/12);
                image2.setSize(Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/12);
                przycisk_skoku.remove();
                przycisk_pojscia_w_przod.remove();
                przycisk_pojscia_w_tyl.remove();
                przycisk_krzyku.remove();
                stage.addActor(image1);
                stage.addActor(image2);
                image1.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        image1.remove();
                        image2.remove();
                        stage.addActor(przycisk_skoku);
                        stage.addActor(przycisk_pojscia_w_przod);
                        stage.addActor(przycisk_pojscia_w_tyl);
                        stage.addActor(przycisk_krzyku);

                        MyGame.manager.get("sounds/skok.mp3", Music.class).play();


                        if(stanMany<=0){
                            sen();
                        }

                        Player.b2body.applyLinearImpulse(new Vector2(-85,55),Player.b2body.getWorldCenter(),true);
                        skok=true;


                        return super.touchDown(event, x, y, pointer, button);
                    }
                });



                image2.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                        image1.remove();
                        image2.remove();
                        stage.addActor(przycisk_skoku);
                        stage.addActor(przycisk_pojscia_w_przod);
                        stage.addActor(przycisk_pojscia_w_tyl);
                        stage.addActor(przycisk_krzyku);
                        MyGame.manager.get("sounds/skok.mp3", Music.class).play();


                        skok=true;


                        Player.b2body.applyLinearImpulse(new Vector2(85,25),Player.b2body.getWorldCenter(),true);
                        return super.touchDown(event, x, y, pointer, button);
                    }
                });


                return super.touchDown(event, x, y, pointer, button) &&pSkoku;
            }


        });


        przycisk_ataku.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manaLabel.setText( stanMany-=strata_many);
                Player.atak();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    private void staty() {
        stanMany = 50;
        stanZycia = 100;
        stan_Zycia_przeciwnika=50;
        stan_many_przeciwnika=50;
        stan_odjecia_many=2;
        regeneracja_many=15;
        strata_many=5;
    }

    private void sen() {
         int zero=0;
         Player.sen();
         przycisk_krzyku.remove();
         przycisk_skoku.remove();
         przycisk_pojscia_w_przod.remove();
         przycisk_pojscia_w_tyl.remove();
         przycisk_ataku.remove();
         manaLabel.setText(String.format("%01d", zero));
         manaLabel.setPosition(Gdx.graphics.getWidth()*67/400,Gdx.graphics.getHeight()*7/60,100);
    }


    private Button.ButtonStyle preparePrzycisk(int i) {
        TextureAtlas atlasNg = null;
        if(i==1)
            atlasNg = new TextureAtlas("menu/przycisk_pojscia_w_tyl.pack");
        if(i==2)
            atlasNg = new TextureAtlas("menu/przycisk_pojscia_w_przod.pack");
        if(i==3)
            atlasNg = new TextureAtlas("menu/przycisk_krzyku.pack");

        if(i==4)
            atlasNg = new TextureAtlas("menu/przycisk_snu.pack");

        if(i==5)
            atlasNg = new TextureAtlas("menu/atak.pack");

        Skin skinNg = new Skin(atlasNg);
        Button.ButtonStyle buttonStyleNg=new Button.ButtonStyle();
        if(i==1) {
            buttonStyleNg.up = skinNg.getDrawable("przycisk_pojscia_w_tyl");
            skinNg.getDrawable("przycisk_pojscia_w_tyl2");
        }
        if(i==2) {
            buttonStyleNg.up = skinNg.getDrawable("przycisk_pojscia_w_przod");
            skinNg.getDrawable("przycisk_pojscia_w_przod2");
        }

        if(i==3) {
            buttonStyleNg.up = skinNg.getDrawable("przycisk_krzyku");
            skinNg.getDrawable("przycisk_krzyku");
        }

        if(i==4) {
            buttonStyleNg.up = skinNg.getDrawable("przycisk_snu");
            skinNg.getDrawable("przycisk_snu");
        }

        if(i==5) {
            buttonStyleNg.up = skinNg.getDrawable("atak");
            skinNg.getDrawable("atak");
        }

        return buttonStyleNg;
    }







    public void update(float delta) {
    }




    @Override
    public void dispose() { stage.dispose(); }



    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(pointer > 1){
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(count > 1){
        }
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }


}
