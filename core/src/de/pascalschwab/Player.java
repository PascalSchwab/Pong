package de.pascalschwab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public class Player extends Rectangle implements Disposable {
    private static final float VELOCITY = 300f;
    private int score = 0;

    public Player(float x, float y, float width, float height) {
        super(x,y,width,height);
    }

    public void draw(ShapeRenderer renderer){
        renderer.setColor(Color.WHITE);
        renderer.rect(this.x, this.y, this.width, this.height);
    }

    public void moveUp(){
        this.setPosition(this.x, this.y+VELOCITY*Gdx.graphics.getDeltaTime());
    }

    public void moveDown(){
        this.setPosition(this.x, this.y-VELOCITY*Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {

    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScorePoint(){
        this.score++;
    }
}
