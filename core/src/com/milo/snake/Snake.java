package com.milo.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private BodyPart head;
    private List<BodyPart> body = new ArrayList<BodyPart>();
    private Directions direction = Directions.NORTH;
    private Vector2 startPos = new Vector2(SnakeGame.WIDTH / 2, SnakeGame.HEIGHT / 2);

    public Snake() {
        head = new BodyPart(startPos);
        for (int i = 1; i < 10; i++){
            body.add(new BodyPart(new Vector2(startPos.x,startPos.y - i)));
        }
    }

    public void draw(Pixmap pixmap) {
        pixmap.setColor(Color.RED);
        pixmap.drawPixel(head.position.x, head.position.y);
        pixmap.setColor(Color.WHITE);
        for (BodyPart bodyPart : body) {
            pixmap.drawPixel(bodyPart.position.x, bodyPart.position.y);
        }
    }

    public void move() {

        for (int i = body.size() - 1; i >= 0; i--) {
            if (i > 0) {
                body.get(i).position.x = body.get(i-1).position.x;
                body.get(i).position.y = body.get(i-1).position.y;
            } else if (i == 0) {
                body.get(i).position.x = head.position.x;
                body.get(i).position.y = head.position.y;
            }
        }


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
        body.add(new BodyPart(new Vector2(body.get(body.size()-1).position.x, body.get(body.size()-1).position.y)));
    }

    public BodyPart getHead() {
        return head;
    }

    public void setHead(BodyPart head) {
        this.head = head;
    }

    public List<BodyPart> getBody() {
        return body;
    }

    public void setBody(List<BodyPart> body) {
        this.body = body;
    }

    public Directions getDirections() {
        return direction;
    }

    public void setDirections(Directions directions) {
        this.direction = directions;
    }
}
