package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.NPC;

public class TeleportAuburyTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.hasEquippedPickaxe() || miner.hasInventoryPickaxe()) {
			if(!miner.hasRuneEssence() && miner.isInRuneShop()) {
				if(!miner.isDoorClosed()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		NPC aubury = miner.closestNPCForName(Constant.AUBURY_NAME);
		try {
			if(aubury.interact(Constant.AUBURY_ACTION)) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
