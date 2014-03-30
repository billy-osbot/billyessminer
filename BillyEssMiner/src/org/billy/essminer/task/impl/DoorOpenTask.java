package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.model.RS2Object;

public class DoorOpenTask implements ScriptTask {

	@Override
	public boolean activate(Miner miner) {
		if(!miner.isInMine() && miner.isDoorClosed() && miner.isInRuneShop()) {
			return true;
		}
		return false;
	}

	@Override
	public int execute(Miner miner) {
		RS2Object door = miner.closestObjectForName(Constant.DOOR_NAME);
		if(door != null) {
			try {
				if(door.interact(Constant.DOOR_ACTION)) {
					return MethodProvider.random(1000, 2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	
}
