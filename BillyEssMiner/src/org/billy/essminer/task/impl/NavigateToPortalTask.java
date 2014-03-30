package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class NavigateToPortalTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		/*List<RS2Object> objs = miner.client.getCurrentRegion().getObjects();
		for(RS2Object obj : objs) {
			if(obj == null) continue;
			if(obj.getPosition().getX() == 9957 && obj.getPosition().getY() == 5469) {
				miner.log(obj.getId() + "");
			}
		}
		miner.log("Finished");*/
		if(miner.isInMine() && !miner.myPlayer().isMoving()) {
			if((!miner.hasEquippedPickaxe() && !miner.hasInventoryPickaxe()) || miner.client.getInventory().isFull()) {
				RS2Object portal = miner.closestObject(Constant.PORTAL_IDS);
				if(portal != null && !miner.isPlayerWithinDistance(portal.getPosition(), 2)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		try {
			miner.client.moveCameraToEntity(miner.closestObject(Constant.PORTAL_IDS));
			if(miner.walkMiniMap(miner.closestObject(Constant.PORTAL_IDS).getPosition())) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
