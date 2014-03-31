package org.billy.essminer.task.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.util.Constant;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class BankDepositTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(miner.isInBank() && miner.hasRuneEssence()) {
			return true;
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		if(miner.client.getBank().isOpen()) {
			try {
				if(miner.client.getBank().depositAllExcept(Constant.PICKAXES)) {
					return MethodProvider.random(500, 1250);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else {
			RS2Object bank = miner.closestObjectForName(Constant.BANK_NAME);
			try {
				if(bank.interact(Constant.BANK_ACTION)) {
					return MethodProvider.random(1000, 1750);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
