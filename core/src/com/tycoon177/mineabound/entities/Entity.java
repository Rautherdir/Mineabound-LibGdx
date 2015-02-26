package com.tycoon177.mineabound.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
	private Sprite sprite;
	private Body body;
	private float width, height;
	public static final int RIGHT = 1, LEFT = 0;

	protected void setBody(Body b) {
		this.body = b;
	}

	public Body getBody() {
		return body;
	}

	protected void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	protected Sprite getSprite() {
		return sprite;
	}

	public void draw(SpriteBatch batch) {
		if (body == null || sprite == null)
			return;
		draw(batch, RIGHT);
		batch.draw(sprite, (body.getPosition().x - width / 2) + width, body.getPosition().y - height / 2, -width, height);
	}

	public void draw(SpriteBatch batch, int direction) {
		if (body == null || sprite == null)
			return;
		if (direction == RIGHT)
			batch.draw(sprite, (body.getPosition().x - width / 2), body.getPosition().y - height / 2, width, height);
		else if (direction == LEFT)
			batch.draw(sprite, (body.getPosition().x - width / 2) + width, body.getPosition().y - height / 2, -width, height);
	}

	protected void setSize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	protected Vector2 getSize() {
		return new Vector2(width, height);
	}
}
