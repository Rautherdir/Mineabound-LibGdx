package com.tycoon177.mineabound.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Block {
	private Body body;
	private BlockType type;
	private float width = 1, height = 1;

	public Block(BlockType type) {
		this.type = type;
	}

	public void draw(SpriteBatch renderer) {
		if (body == null) // Hasn't been added to the world yet.
			return;
		TextureRegion sprite = getSprite();
		if (sprite == null)
			return;
		renderer.draw(getSprite(), body.getPosition().x - width / 2f, body.getPosition().y - height / 2f, width, height);
	}

	private TextureRegion getSprite() {
		return type.getSprite();
	}

	public Body getBody() {
		return body;
	}

	public void addToWorld(World world, Vector2 position) {
		if (body != null)
			return; // Already been added
		BodyDef def = new BodyDef();
		def.type = BodyType.StaticBody;
		def.fixedRotation = true;
		def.position.set(position);
		body = world.createBody(def);
		FixtureDef f = new FixtureDef();
		f.density = 1;
		f.friction = .5f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2f, height / 2f);
		f.shape = shape;

		body.createFixture(f);

	}

}
