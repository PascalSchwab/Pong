package de.pascalschwab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Ball extends Rectangle implements Disposable {
    private static final float SPEED = 400f;
    private float dirX;
    private float dirY;
    public Ball(float x, float y, float width, float height) {
        super(x,y,width,height);
        this.setDirection(0, 0);
    }

    public void draw(ShapeRenderer renderer){
        renderer.setColor(Color.WHITE);
        renderer.rect(this.x, this.y, this.width, this.height);
    }

    public void move() {
        this.setPosition(this.x + dirX*SPEED*Gdx.graphics.getDeltaTime(),
                this.y + dirY*SPEED*Gdx.graphics.getDeltaTime());
    }
    @Override
    public void dispose() {

    }
    public void setDirection(float dirX, float dirY) {
        this.dirX = dirX;
        this.dirY = dirY;
    }

    public float getDirX() {
        return dirX;
    }

    public float getDirY() {
        return dirY;
    }
}
