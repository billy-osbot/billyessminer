package org.billy.essminer.task.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.util.Constant;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class TeleportPortalTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.isInMine()) {
			if((!miner.hasEquippedPickaxe() && !miner.hasInventoryPickaxe()) || miner.client.getInventory().isFull()) {
				RS2Object portal = miner.closestObject(Constant.PORTAL_IDS);
				if(portal != null && miner.isPlayerWithinDistance(portal.getPosition(), 2)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		RS2Object portal = miner.closestObject(Constant.PORTAL_IDS);
		try {
			miner.client.moveCameraToEntity(portal);
			for(String action : Constant.PORTAL_ACTIONS) {
				if(portal.interact(action)) {
					return MethodProvider.random(1000, 2000);
				}
			}
			if(miner.client.moveMouseTo(portal.getMouseDestination(), false, true, false)) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
