package com.tycoon177.mineabound.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tycoon177.mineabound.screens.GameWorld;
import com.tycoon177.mineabound.utils.LoadedTextureAtlas;

public class Player extends Entity {
	/**The default player size**/
	private Vector2 playerSize = new Vector2(.6f, 1.8f);
	private int direction = RIGHT;
	public Player() {
		setSize(playerSize.x, playerSize.y);
		this.setSprite(LoadedTextureAtlas.blockAtlas.createSprite("playerStill"));

		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.fixedRotation = true;
		bDef.position.set(0, 40);
		setBody(GameWorld.world.createBody(bDef));

		FixtureDef fDef = new FixtureDef();
		fDef.density = 1.5f;
		fDef.friction = 1f;
		PolygonShape playerShape = new PolygonShape();
		playerShape.setAsBox(getSize().x / 2, getSize().y / 2);
		fDef.shape = playerShape;
		getBody().createFixture(fDef);
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}

}
