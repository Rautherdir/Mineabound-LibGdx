package com.tycoon177.mineabound;

import com.badlogic.gdx.Game;
import com.tycoon177.mineabound.screens.GameWorld;

public class MineaboundLauncher extends Game {
	
	@Override
	public void create () {
		this.setScreen(new GameWorld());
	}
}
