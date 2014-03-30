package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.NPC;

public class NavigateToShopTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.hasEquippedPickaxe() || miner.hasInventoryPickaxe()) {
			if(!miner.myPlayer().isMoving()) {
				if(!miner.hasRuneEssence() && !miner.isInRuneShop() && !miner.isInMine()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		try {
			NPC aubury = miner.closestNPCForName(Constant.AUBURY_NAME);
			if(aubury != null) {
				if(miner.walk(aubury)) {
					return MethodProvider.random(1000, 2000);
				}
			} else {
				if(miner.walk(Constant.VARROCK_RUNE_SHOP)) {
					return MethodProvider.random(1000, 2000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
