package de.pascalschwab;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Game extends ApplicationAdapter {
	private static final float PADDING = 50f;
	private static final float PLAYER_WIDTH = 20f;
	private static final float PLAYER_HEIGHT = 100f;
	private static final float BALL_SIZE = 20f;
	private Player[] players;
	private Ball ball;
	private ShapeRenderer shapeRenderer;
	private Scoreboard scoreboard;
	private Stage uiStage;

	@Override
	public void create () {
		uiStage = new Stage(new ScreenViewport());

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		scoreboard = new Scoreboard(0,Gdx.graphics.getHeight()-PADDING-100);
		uiStage.addActor(scoreboard.getLabel());

		players = new Player[2];
		players[0] = new Player(
				PADDING,
				(float) Gdx.graphics.getHeight()/2-PLAYER_HEIGHT/2,
				PLAYER_WIDTH, PLAYER_HEIGHT);
		players[1] = new Player(
				(float) Gdx.graphics.getWidth()-PADDING-PLAYER_WIDTH,
				(float) Gdx.graphics.getHeight()/2-PLAYER_HEIGHT/2,
				PLAYER_WIDTH, PLAYER_HEIGHT);

		ball = new Ball(
				(float) Gdx.graphics.getWidth() /2-BALL_SIZE/2,
				(float) Gdx.graphics.getHeight() /2-BALL_SIZE/2,
				BALL_SIZE, BALL_SIZE);
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		uiStage.act();
		uiStage.draw();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for(Player player : players){
			player.draw(shapeRenderer);
		}
		ball.draw(shapeRenderer);
		shapeRenderer.end();
	}

	private void update(){
		updatePlayers();
		updateBall();
	}

	private void updateBall(){
		ball.move();
		// Player Collision
		for(Player player : players){
			if(ball.overlaps(player)){
				float ballCenterY = ball.getY()+ball.getHeight()/2;
				float playerCenterY = player.getY()+player.getHeight()/2;
				float ratio = (playerCenterY - ballCenterY)/player.getHeight()*1.5f;
				ball.setDirection(-ball.getDirX(), -ratio);
			}
		}
		// Wall Collision
		if(ball.getY()+ball.getHeight() >= Gdx.graphics.getHeight() || ball.getY() <= 0){
			ball.setDirection(ball.getDirX(), -ball.getDirY());
		}
		// Points
		if(ball.getX() <= 0){
			players[1].addScorePoint();
			Gdx.app.log("Player 0", String.valueOf(players[1].getScore()));
			scoreboard.updateScore(players);
			reset();
		}
		else if(ball.getX() >= Gdx.graphics.getWidth()){
			players[0].addScorePoint();
			Gdx.app.log("Player 1", String.valueOf(players[0].getScore()));
			scoreboard.updateScore(players);
			reset();
		}
	}

	private void updatePlayers(){
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			if(players[0].getY()+players[0].getHeight() <= Gdx.graphics.getHeight()){
				players[0].moveUp();
			}
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S)){
			if(players[0].getY() >= 0) {
				players[0].moveDown();
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
			if(players[1].getY()+players[1].getHeight() <= Gdx.graphics.getHeight()) {
				players[1].moveUp();
			}
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
			if(players[1].getY() >= 0) {
				players[1].moveDown();
			}
		}
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		for(Player player : players){
			player.dispose();
		}
		ball.dispose();
		scoreboard.dispose();
	}

	private void reset(){
		players[0].setX(PADDING);
		players[0].setY((float) Gdx.graphics.getHeight()/2-PLAYER_HEIGHT/2);
		players[1].setX((float) Gdx.graphics.getWidth()-PADDING-PLAYER_WIDTH);
		players[1].setY((float) Gdx.graphics.getHeight()/2-PLAYER_HEIGHT/2);
		ball.setX((float) Gdx.graphics.getWidth() /2-BALL_SIZE/2);
		ball.setY((float) Gdx.graphics.getHeight() /2-BALL_SIZE/2);
		ball.setDirection(-1, 0);
	}
}
