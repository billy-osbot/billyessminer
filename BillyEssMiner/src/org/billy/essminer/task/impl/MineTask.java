package org.billy.essminer.task.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.util.Constant;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class MineTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.hasEquippedPickaxe() || miner.hasInventoryPickaxe()) {
			if(miner.isInMine()) {
				RS2Object mine = miner.closestObjectForName(Constant.RUNE_ESSENCE_NAMES);
				if(mine != null && miner.isPlayerWithinDistance(mine.getPosition(), 3)) {
					if(!miner.client.getInventory().isFull()) {
						if(miner.myPlayer().getAnimation() != Constant.MINING_ANIMATIONS[0] && miner.myPlayer().getAnimation() != Constant.MINING_ANIMATIONS[1]) {
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
		RS2Object mine = miner.closestObjectForName(Constant.RUNE_ESSENCE_NAMES);
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
