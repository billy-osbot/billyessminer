package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class BankWithdrawTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(!miner.hasEquippedPickaxe() && !miner.hasInventoryPickaxe()) {
			if(!miner.client.getInventory().isFull()) {
				if(miner.client.getBank().contains(Constant.PICKAXES)) {
					if(miner.isInBank()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		if(miner.client.getBank().isOpen()) {
			try {
				if(miner.client.getBank().withdraw(Constant.PICKAXES[0], 1)) {
					return MethodProvider.random(1000, 2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			RS2Object bank = miner.closestObjectForName(Constant.BANK_NAME);
			try {
				if(bank.interact(Constant.BANK_ACTION)) {
					return MethodProvider.random(1000, 2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
