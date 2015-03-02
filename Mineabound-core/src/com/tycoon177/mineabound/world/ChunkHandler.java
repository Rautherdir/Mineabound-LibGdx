package com.tycoon177.mineabound.world;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.tycoon177.mineabound.screens.GameWorld;

public class ChunkHandler {
	public static HashMap<Integer, Chunk> chunks;

	public ChunkHandler() {
		chunks = new HashMap<Integer, Chunk>();
		addChunk(new Chunk(0));
	}

	public Chunk addChunk(Chunk chunk) {
		chunks.put(chunk.getID(), chunk);
		chunk.addToWorld();
		return chunk;
	}

	public Chunk getChunk(int key) {
		Chunk c = chunks.get(key);

		return c == null ? addChunk(new Chunk(key)) : c;
	}

	public void update() {
	}

	public void render(SpriteBatch batch) {
		Array<Chunk> visibleChunks = getVisibleChunks();
		for (Chunk chunk : visibleChunks) {
			if (chunk != null)
				chunk.render(batch);
		}
	}

	private Array<Chunk> getVisibleChunks() {
		int chunkNum = (MathUtils.floor(GameWorld.player.getBody().getPosition().x)) / Chunk.WIDTH;
	//	System.out.println(chunkNum);
		if (GameWorld.player.getBody().getPosition().x < 0)
			chunkNum -= 1;
		Array<Chunk> visible = new Array<Chunk>();
		visible.add(getChunk(chunkNum));
		visible.add(getChunk(chunkNum - 1));
		visible.add(getChunk(chunkNum + 1));
		return visible;
	}

}
