package com.milo.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private BodyPart head;
    private List<BodyPart> body = new ArrayList<BodyPart>();
    private Direction direction = Direction.NORTH;
    private Vector2 startPos = new Vector2(SnakeGame.WIDTH / 2, SnakeGame.HEIGHT / 2);

    public Snake() {
        //skapa ormen på sin startplats
        head = new BodyPart(startPos);
        for (int i = 1; i < 10; i++){
            body.add(new BodyPart(new Vector2(startPos.x,startPos.y - i)));
        }
    }

    public void draw(Pixmap pixmap) {
        //huvudet
        pixmap.setColor(Color.RED);
        pixmap.drawPixel(head.position.x, head.position.y);

        //kroppen
        pixmap.setColor(Color.WHITE);
        for (BodyPart bodyPart : body) {
            pixmap.drawPixel(bodyPart.position.x, bodyPart.position.y);
        }
    }

    public void move() {

        for (int i = body.size() - 1; i >= 0; i--) {
            if (i > 0) {
                //flyttar varje kroppsdel till den position kroppsdelen före har
                body.get(i).position.x = body.get(i-1).position.x;
                body.get(i).position.y = body.get(i-1).position.y;
            } else if (i == 0) {
                //första kroppsdelen flyttas istället till huvudets position
                body.get(i).position.x = head.position.x;
                body.get(i).position.y = head.position.y;
            }
        }

        //flytta huvudet
        switch (direction) {
            case NORTH:
                head.position.y++;
                break;
            case SOUTH:
                head.position.y--;
                break;
            case EAST:
                head.position.x++;
                break;
            case WEST:
                head.position.x--;
                break;
        }
    }

    public void addBodyPart() {
        //lägger till en kroppsdel på samma position som den sista
        //kroppsdelen in listan, den flyttas sedan dit den ska av move() metoden
        body.add(new BodyPart(new Vector2(body.get(body.size()-1).position.x, body.get(body.size()-1).position.y)));
    }

    public BodyPart getHead() {
        return head;
    }

    public List<BodyPart> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
