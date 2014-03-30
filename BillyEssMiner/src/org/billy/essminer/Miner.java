package org.billy.essminer;

import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.billy.essminer.antiban.AntiBanTask;
import org.billy.essminer.antiban.impl.CameraMoveTask;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.task.impl.BankDepositTask;
import org.billy.essminer.task.impl.BankWithdrawTask;
import org.billy.essminer.task.impl.MineTask;
import org.billy.essminer.task.impl.NavigateToBankTask;
import org.billy.essminer.task.impl.NavigateToMineTask;
import org.billy.essminer.task.impl.NavigateToPortalTask;
import org.billy.essminer.task.impl.NavigateToShopTask;
import org.billy.essminer.task.impl.TeleportAuburyTask;
import org.billy.essminer.task.impl.TeleportPortalTask;
import org.osbot.script.Script;
import org.osbot.script.ScriptManifest;
import org.osbot.script.rs2.model.Item;
import org.osbot.script.rs2.ui.EquipmentSlot;

@ScriptManifest(author = "Billy", info = "A simple open source varrock essence miner.", name = "BillyEssMiner", version = 1.0)
public class Miner extends Script {
	
	private final ExecutorService service = Executors.newSingleThreadExecutor();
	
	private final ScriptTask[] tasks = new ScriptTask[] {
			new MineTask(),
			new TeleportAuburyTask(),
			new TeleportPortalTask(),
			new BankDepositTask(),
			new BankWithdrawTask(),
			new NavigateToShopTask(),
			new NavigateToMineTask(),
			new NavigateToPortalTask(),
			new NavigateToBankTask()
	};
	
	private final AntiBanTask[] antibanTasks = new AntiBanTask[] {
			new CameraMoveTask()
	};
	
	@Override
	public void onStart() {
		
	}

	@Override
	public int onLoop() throws InterruptedException {
		for(AntiBanTask task : antibanTasks) {
			if(task.activate()) {
				service.execute(task);
				break;
			}
		}
		for(ScriptTask task : tasks) {
			if(task.activate(this)) {
				return task.execute(this);
			}
		}
		return 0;
	}

	@Override
	public void onPaint(Graphics graphics) {

	}
	
	public boolean hasEquippedPickaxe() {
		Item pickaxe = equipmentTab.getItemInSlot(EquipmentSlot.WEAPON);
		for(int pick : Constant.PICKAXES) {
			if(pickaxe.getId() == pick) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasInventoryPickaxe() {
		Item[] items = client.getInventory().getItems();
		for(Item item : items) {
			for(int pick : Constant.PICKAXES) {
				if(item.getId() == pick) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isInBank() {
		return Constant.VARROCK_BANK.contains(myPlayer());
	}
	
	public boolean isInRuneShop() {
		return Constant.VARROCK_RUNE_SHOP.contains(myPlayer());
	}
	
	public boolean isInMine() {
		if(myPlayer().getPosition().getY() > 7000) {
			return true;
		}
		return false;
	}

	public boolean hasRuneEssence() {
		return client.getInventory().contains(Constant.RUNE_ESSENCE_NAME);
	}
	
}
