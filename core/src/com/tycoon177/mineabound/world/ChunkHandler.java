package com.tycoon177.mineabound.world;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tycoon177.mineabound.screens.GameWorld;

public class ChunkHandler {
	private HashMap<Integer, Chunk> chunks;
	private int chunksToRender = 5;

	public ChunkHandler() {
		chunks = new HashMap<Integer, Chunk>();
		addChunk(new Chunk(0));
		addChunk(new Chunk(-1));
		addChunk(new Chunk(1));
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

	public void generateChunk(Vector2 playerLoc) {
		int chunkNum = (MathUtils.round(playerLoc.x)) / Chunk.WIDTH;
		if (!chunks.containsKey(chunkNum)) {
			addChunk(new Chunk(chunkNum));
		}
	}

	public void update() {
		generateChunk(GameWorld.player.getBody().getPosition());
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
		if(GameWorld.player.getBody().getPosition().x < 0) chunkNum -= 1;
		System.out.println(chunkNum);
		Array<Chunk> visible = new Array<Chunk>();
		visible.add(getChunk(chunkNum));
		visible.add(getChunk(chunkNum - 1));
		visible.add(getChunk(chunkNum + 1));
		return visible;
	}

}
