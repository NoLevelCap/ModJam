package com.vanhal.progressiveautomation.entities.miner;

import com.vanhal.progressiveautomation.ref.ToolHelper;

public class TileMinerIron extends TileMiner {
	public TileMinerIron() {
		super();
		setUpgradeLevel(ToolHelper.LEVEL_IRON);
	}
}