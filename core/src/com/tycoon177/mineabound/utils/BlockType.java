package com.tycoon177.mineabound.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BlockType {
	AIR(0, ""), DIRT(1, "grassCenter");

	private TextureRegion sprite;
	private int id;
	private BlockType(int id, String texture){
		this.id = id;
		sprite = LoadedTextureAtlas.blockAtlas.createSprite(texture);
	}
	
	public TextureRegion getSprite(){
		return sprite;
	}
	
	public int getBlockID(){
		return id;
	}
}
