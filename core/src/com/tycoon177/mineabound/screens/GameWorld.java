package com.tycoon177.mineabound.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tycoon177.mineabound.Entity;

public class GameWorld implements Screen {
	private World world;
	private OrthographicCamera camera;
	private SpriteBatch renderer;
	@Override
	public void show() {
		renderer = new SpriteBatch();
		camera = new OrthographicCamera();
		world = new World(new Vector2(0,-9.8f), true);
	}

	@Override
	public void render(float delta) {
		camera.update();
		world.step(delta, 6, 8);
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin();
		Array<Body> entities = new Array<Body>();
		world.getBodies(entities);
		for(Body entity : entities){
			((Entity)entity).draw(renderer);
		}
		renderer.end();
		update();
	}

	private void update() {
		//TODO run updates on the game entities
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		renderer.dispose();
		world.dispose();
		
	}

}
