package com.vanhal.progressiveautomation.entities.generator;

import com.vanhal.progressiveautomation.ref.ToolHelper;

public class TileGeneratorDiamond extends TileGenerator {
	
	public TileGeneratorDiamond() {
		super();
		setUpgradeLevel(ToolHelper.LEVEL_DIAMOND);
		setEnergyStorage(160000, 4);
		setFireChance(0);
	}
}
