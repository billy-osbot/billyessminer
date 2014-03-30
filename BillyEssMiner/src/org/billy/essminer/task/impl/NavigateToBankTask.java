package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;

public class NavigateToBankTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(!miner.isInBank() && !miner.isInMine() && !miner.myPlayer().isMoving()) {
			if((!miner.hasEquippedPickaxe() && !miner.hasInventoryPickaxe()) || miner.client.getInventory().isFull()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		try {
			if(miner.walk(Constant.VARROCK_BANK)) {
				return MethodProvider.random(1000, 2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}
