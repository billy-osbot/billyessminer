package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class MineTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.hasEquippedPickaxe() || miner.hasInventoryPickaxe()) {
			if(miner.isInMine()) {
				if(miner.closestObjectForName(Constant.RUNE_ESSENCE_NAME).isVisible()) {
					if(!miner.client.getInventory().isFull()) {
						if(miner.myPlayer().getAnimation() != Constant.MINING_ANIMATION) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		RS2Object mine = miner.closestObjectForName(Constant.RUNE_ESSENCE_NAME);
		try {
			miner.client.moveCameraToEntity(mine);
			if(mine.interact(Constant.MINE_ACTION)) {
				return MethodProvider.random(1000, 3000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
