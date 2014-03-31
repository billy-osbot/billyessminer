package org.billy.essminer.task.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.util.Constant;
import org.osbot.script.MethodProvider;

public class NavigateToBankTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(!miner.isInBank() && !miner.isInMine() && !miner.myPlayer().isMoving() && !miner.isDoorClosed()) {
			if((!miner.hasEquippedPickaxe() && !miner.hasInventoryPickaxe()) || miner.client.getInventory().isFull()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		try {
			if(miner.walk(miner.closestObjectForName(Constant.BANK_NAME))) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
