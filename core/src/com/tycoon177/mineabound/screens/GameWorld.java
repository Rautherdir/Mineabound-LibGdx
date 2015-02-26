package com.tycoon177.mineabound.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tycoon177.mineabound.Block;
import com.tycoon177.mineabound.utils.BlockType;
import com.tycoon177.mineabound.utils.GameInputProcessor;

public class GameWorld implements Screen {
	private World world;
	private static OrthographicCamera camera;
	private SpriteBatch renderer;
	Box2DDebugRenderer dbr = new Box2DDebugRenderer();
	Block block;
	private Vector2 characterMovement;
	private final float forceX = 20f;
	private final float forceY = 5f;
	private Vector2 previousLoc;

	@Override
	public void show() {
		previousLoc = new Vector2();
		characterMovement = new Vector2();
		renderer = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 10);
		world = new World(new Vector2(0, -9.8f), true);
		block = new Block(world, BlockType.DIRT, new Vector2(2, 2));
		BodyDef floor = new BodyDef();
		floor.position.set(0, 0);
		floor.type = BodyType.StaticBody;
		Body b = world.createBody(floor);
		FixtureDef def = new FixtureDef();
		ChainShape shape = new ChainShape();
		shape.createChain(new float[] { 0, 0, 10, 0 });
		def.shape = shape;
		b.createFixture(def);
		createInputProcessor();
	}

	private void createInputProcessor() {
		Gdx.input.setInputProcessor(new GameInputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case Keys.A:
					characterMovement.x = -forceX;
					break;
				case Keys.D:
					characterMovement.x = forceX;
					break;
				case Keys.W:
					characterMovement.y = forceY;
					break;
				case Keys.S:
					characterMovement.y = -forceY;
					break;
				}

				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				switch (keycode) {
				case Keys.A:
				case Keys.D:
					characterMovement.x = 0;
					break;
				case Keys.W:
				case Keys.S:
					characterMovement.y = 0;
					break;

				}
				return true;
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		world.step(1 / 60f, 8, 3);
		System.out.println((previousLoc.dst(block.getBody().getPosition()) / delta));
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin();
		block.draw(renderer);
		renderer.end();
		dbr.render(world, camera.combined);
		update(delta);
	}

	private void update(float delta) {
		previousLoc.set(block.getBody().getPosition());
		// block.getBody().applyForceToCenter(characterMovement, true);
		block.getBody().setLinearVelocity(characterMovement.x, characterMovement.y == 0 ? block.getBody().getLinearVelocity().y : characterMovement.y);
		// block.getBody().applyForceToCenter(0, characterMovement.y, true);
		camera.position.set(block.getBody().getPosition().x, block.getBody().getPosition().y, 0);
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

	public static OrthographicCamera getCamera() {

		return camera;
	}

}
