package com.milo.snake;

public abstract class GameObject {
    protected Vector2 position;

    public GameObject(Vector2 position) {
        this.position = position;
    }
}
