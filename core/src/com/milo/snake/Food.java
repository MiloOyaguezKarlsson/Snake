package com.milo.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class Food extends GameObject {
    public Food(Vector2 position) {
        super(position);
    }

    public void draw(Pixmap pixmap){
        pixmap.setColor(Color.GREEN);
        pixmap.drawPixel(position.x, position.y);
    }
}
