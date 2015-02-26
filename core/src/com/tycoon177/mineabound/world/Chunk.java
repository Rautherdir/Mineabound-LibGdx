package com.tycoon177.mineabound.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tycoon177.mineabound.screens.GameWorld;

public class Chunk {
	private Block[][] block;
	public static int WIDTH = 16, HEIGHT = 256;
	private int id;

	public Chunk(int id) {
		this.id = id;
		generateChunk();
	}

	public void addToWorld() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (block[i][j] != null)
					block[i][j].addToWorld(GameWorld.world, new Vector2(id * WIDTH + i, j));
			}
		}
	}

	public void render(SpriteBatch renderer) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (block[i][j] != null)
					block[i][j].draw(renderer);
			}
		}
	}

	public int getID() {
		return id;
	}

	public void generateChunk() {
		block = new Block[WIDTH][HEIGHT];
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < 30; j++) {
				block[i][j] = new Block(j == 0 ? BlockType.BEDROCK : BlockType.DIRT);
			}
		}
	}
}
