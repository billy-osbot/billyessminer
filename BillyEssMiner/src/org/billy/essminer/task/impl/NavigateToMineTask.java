package org.billy.essminer.task.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.util.Constant;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class NavigateToMineTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.isInMine() && !miner.myPlayer().isMoving()) {
			if(miner.hasEquippedPickaxe() || miner.hasInventoryPickaxe()) {
				if(!miner.hasRuneEssence()) {
					RS2Object mine = miner.closestObjectForName(Constant.RUNE_ESSENCE_NAMES);
					if(!miner.isPlayerWithinDistance(mine.getPosition(), 3)) {
						return true;
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
			if(miner.walk(mine.getPosition(), 0)) {
				return MethodProvider.random(1000, 2000);
			}
			if(miner.walkMiniMap(mine.getPosition())) {
				return MethodProvider.random(1000, 2000);
			}
			if(miner.walk(mine)) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}
