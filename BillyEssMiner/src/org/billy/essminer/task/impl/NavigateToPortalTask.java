package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class NavigateToPortalTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.isInMine() && !miner.myPlayer().isMoving()) {
			if((!miner.hasEquippedPickaxe() && !miner.hasInventoryPickaxe()) || miner.client.getInventory().isFull()) {
				RS2Object portal = miner.closestObjectForName(Constant.PORTAL_NAME);
				if(portal != null && !portal.isVisible()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		try {
			if(miner.walk(miner.closestObjectForName(Constant.PORTAL_NAME))) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
