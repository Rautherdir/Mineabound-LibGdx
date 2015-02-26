package com.tycoon177.mineabound;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tycoon177.mineabound.utils.BlockType;

public class Block {
	private Body body;
	private BlockType type;
	private float width = 1, height = 1;

	public Block(World world, BlockType type, Vector2 position) {
		this.type = type;
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		def.fixedRotation = true;
		def.position.set(position);
		body = world.createBody(def);
		FixtureDef f = new FixtureDef();
		f.density = 1;
		f.friction = 1f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2f, height / 2f);
		f.shape = shape;
		
		body.createFixture(f);

	}

	public void draw(SpriteBatch renderer) {
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

}
