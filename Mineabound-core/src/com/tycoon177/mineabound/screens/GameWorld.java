package com.tycoon177.mineabound.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tycoon177.mineabound.entities.Player;
import com.tycoon177.mineabound.utils.GameInputProcessor;
import com.tycoon177.mineabound.world.ChunkHandler;

public class GameWorld implements Screen {
	public static World world;
	private static OrthographicCamera camera;
	private SpriteBatch renderer;
	private Box2DDebugRenderer dbr = new Box2DDebugRenderer();
	public static Player player;
	private Vector2 characterMovement;
	private final float forceX = 1;
	private final float forceY = 1;
	private ChunkHandler chunkHandler;
	@Override
	public void show() {
		characterMovement = new Vector2();
		renderer = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 10);
		world = new World(new Vector2(0, -9.8f), true);
		player = new Player();
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
		chunkHandler = new ChunkHandler();
	}

	private void createInputProcessor() {
		Gdx.input.setInputProcessor(new GameInputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Keys.A:
						characterMovement.x = -forceX;
						player.setDirection(Player.LEFT);
						break;
					case Keys.D:
						characterMovement.x = forceX;
						player.setDirection(Player.RIGHT);
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

			private void jump() {
				player.getBody().applyForceToCenter(0, 200f, true);
			}

			@Override
			public boolean keyUp(int keycode) {
				switch (keycode) {
					case Keys.A:
					case Keys.D:
						characterMovement.x = 0;
						player.getBody().setLinearVelocity(characterMovement.x, player.getBody().getLinearVelocity().y);
						break;
					case Keys.W:
					case Keys.S:
						characterMovement.y = 0;
						player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, characterMovement.y);
						break;

				}
				return true;
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.2f, .2f, .75f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		world.step(1 / 60f, 8, 3);
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin();
		chunkHandler.render(renderer);
		player.draw(renderer, player.getDirection());
		renderer.end();
		dbr.render(world, camera.combined);
		update(delta);
	}

	private void update(float delta) {
		player.getBody().applyLinearImpulse(characterMovement, player.getBody().getPosition(), true);
		camera.position.set(player.getBody().getPosition().x, player.getBody().getPosition().y, 0);
		updateTitleWithInfo();
		chunkHandler.update();
	}

	private void updateTitleWithInfo() {
		Vector2 pos = player.getBody().getPosition();
		pos.x = MathUtils.round(pos.x * 100) / 100f;
		pos.y = MathUtils.round(pos.y * 100) / 100f;

		Gdx.graphics.setTitle("X: " + pos.x + " Y: " + pos.y);
	}

	@Override
	public void resize(int width, int height) {

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
