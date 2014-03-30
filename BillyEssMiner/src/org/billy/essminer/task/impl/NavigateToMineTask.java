package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class NavigateToMineTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.isInMine() && !miner.myPlayer().isMoving()) {
			if(miner.hasEquippedPickaxe() || miner.hasInventoryPickaxe()) {
				if(!miner.hasRuneEssence()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		RS2Object mine = miner.closestObjectForName(Constant.RUNE_ESSENCE_NAME);
		if(!mine.isVisible()) {
			try {
				miner.client.moveCameraToPosition(mine.getPosition());
				if(miner.walkMiniMap(mine.getPosition())) {
					return MethodProvider.random(1000, 2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	
}
