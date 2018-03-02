package com.milo.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.milo.GUI.SnakeMenu;
import com.milo.GUI.SubmitScoreForm;

import java.util.Random;

public class SnakeGame extends ApplicationAdapter {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;
    // booleaner som används för att kommunicera mellan menyn och spelet
    public static boolean gameRunning = false;
    public static boolean restart = false;

    private SpriteBatch batch;
    private Texture texture;
    private Pixmap pixmap;
    private Sprite sprite;
    private OrthographicCamera camera;
    private Snake snake;
    private Food food;
    private int points;
    private int moveFreq = 3;
    private SnakeMenu menu;
    private SubmitScoreForm submitScoreForm;
    private Sound biteSound;
    private Sound collisionSound;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        //ställa in kameran/skärmen till 80x60
        camera.setToOrtho(true, WIDTH, HEIGHT);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        //pixmap med samma storlek som kameran/skärmen, 160x120
        pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);
        biteSound = Gdx.audio.newSound(Gdx.files.internal("bite.wav"));
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("whack.wav"));
        menu = new SnakeMenu();
        submitScoreForm = new SubmitScoreForm();

        gameRunning = false;

        menu.open();
        restart();
    }

    public void restart() {
        // ta bort ormen
        snake = null;
        //skapa en ny orm
        snake = new Snake();
        spawnFood();
        points = 0;
        // skicka till menyn att spelet inte är pausat längre
        restart = false;
    }

    public void gameOver(){
        collisionSound.play();
        pauseGame();
        submitScoreForm.open(points);
        restart();
    }

    public void pauseGame() {
        menu.open();
        gameRunning = false;
    }

    public void spawnFood() {
        Random rnd = new Random();
        int rndX = rnd.nextInt(WIDTH);
        int rndY = rnd.nextInt(HEIGHT);
        food = new Food(new Vector2(rndX, rndY));
    }

    public void update() {
        //moveFreq används för att inte flytta ormen vid varje uppdatering utan för var tredje för annars går spelet för snabbt
        moveFreq--;
        if (moveFreq == 0) {
            //flytta ormen
            snake.move();
            moveFreq = 3;
        }

        // input för ormens rörelser, ormen ska inte kunna svänga i motsatt riktning som den rör sig i
        // alltså om den rör sig upp ska man inte kunna svänga ner, samma med alla andra riktningar
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && (snake.getDirection() != Direction.EAST)) {
            snake.setDirection(Direction.WEST);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (snake.getDirection() != Direction.WEST)) {
            snake.setDirection(Direction.EAST);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && (snake.getDirection() != Direction.SOUTH)) {
            snake.setDirection(Direction.NORTH);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && (snake.getDirection() != Direction.NORTH)) {
            snake.setDirection(Direction.SOUTH);
        }
        //ESC för att pausa
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            pauseGame();
        }

        // kollision mellan ormen och maten
        if (snake.getHead().position.x == food.position.x && snake.getHead().position.y == food.position.y) {
            biteSound.play();
            points++;
            spawnFood();
            snake.addBodyPart();
        }

        //kolla ormens kollision med sig själv
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart.position.x == snake.getHead().position.x && bodyPart.position.y == snake.getHead().position.y) {
                gameOver();

            }
        }
        // kolla kollision men fönstrets kanter
        if (snake.getHead().position.x <= 0 || snake.getHead().position.x >= WIDTH
                || snake.getHead().position.y <= 0 || snake.getHead().position.y >= HEIGHT) {
            gameOver();
        }


    }

    @Override
    public void render() {
        if(restart){
            restart();
        }
        if(gameRunning){
            update();
        }

        //rensa skärmen/pixmapen
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        batch.begin();
        //rita maten i pixmapen
        food.draw(pixmap);
        //rita ormen i pixmapen
        snake.draw(pixmap);
        //göra om pixmapen till en textur
        texture.draw(pixmap, 0, 0);
        //rita ut spriten som är hela skärmen
        sprite.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        pixmap.dispose();
        biteSound.dispose();
        collisionSound.dispose();
        texture.dispose();
    }
}
