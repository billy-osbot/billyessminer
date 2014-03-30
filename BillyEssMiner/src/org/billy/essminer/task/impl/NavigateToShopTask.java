package org.billy.essminer.task.impl;

import org.billy.essminer.Constant;
import org.billy.essminer.Miner;
import org.billy.essminer.task.ScriptTask;
import org.osbot.script.MethodProvider;
import org.osbot.script.rs2.map.Position;

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
			if(miner.isDoorClosed()) {
				Position pos = new Position(3253, 3398 ,miner.myPosition().getY());
				if(miner.walk(pos)) {
					return MethodProvider.random(1000, 2000);
				}
			} else {
				Position center = new Position(((Constant.VARROCK_RUNE_SHOP.getMaxX() + Constant.VARROCK_RUNE_SHOP.getMinX()) / 2), ((Constant.VARROCK_RUNE_SHOP.getMaxY() + Constant.VARROCK_RUNE_SHOP.getMinY()) / 2), 0);
				if(miner.walk(center)) {
					return MethodProvider.random(1000, 2000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
