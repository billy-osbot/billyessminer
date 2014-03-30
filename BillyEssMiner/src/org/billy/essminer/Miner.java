package org.billy.essminer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.billy.essminer.async.AsyncTask;
import org.billy.essminer.async.impl.CameraMoveTask;
import org.billy.essminer.async.impl.MouseMoveTask;
import org.billy.essminer.async.impl.RunTask;
import org.billy.essminer.task.ScriptTask;
import org.billy.essminer.task.impl.BankDepositTask;
import org.billy.essminer.task.impl.BankWithdrawTask;
import org.billy.essminer.task.impl.DoorOpenTask;
import org.billy.essminer.task.impl.MineTask;
import org.billy.essminer.task.impl.NavigateToBankTask;
import org.billy.essminer.task.impl.NavigateToMineTask;
import org.billy.essminer.task.impl.NavigateToPortalTask;
import org.billy.essminer.task.impl.NavigateToShopTask;
import org.billy.essminer.task.impl.TeleportAuburyTask;
import org.billy.essminer.task.impl.TeleportPortalTask;
import org.osbot.script.Script;
import org.osbot.script.ScriptManifest;
import org.osbot.script.rs2.map.Position;
import org.osbot.script.rs2.model.Item;
import org.osbot.script.rs2.model.RS2Object;
import org.osbot.script.rs2.skill.Skill;
import org.osbot.script.rs2.ui.EquipmentSlot;

@ScriptManifest(author = "Billy", info = "A simple open source varrock essence miner.", name = "BillyEssMiner", version = 1.0)
public class Miner extends Script {
	
	private final ExecutorService service = Executors.newSingleThreadExecutor();
	private final StatusTracker statusTracker = new StatusTracker(this);
	
	private final ScriptTask[] tasks = new ScriptTask[] {
			new MineTask(),
			new TeleportAuburyTask(),
			new TeleportPortalTask(),
			new BankDepositTask(),
			new BankWithdrawTask(),
			new NavigateToShopTask(),
			new NavigateToMineTask(),
			new NavigateToPortalTask(),
			new NavigateToBankTask(),
			new DoorOpenTask()
	};
	
	private final AsyncTask[] antibanTasks = new AsyncTask[] {
			new CameraMoveTask(this),
			new MouseMoveTask(this),
			new RunTask(this)
	};
	
	@Override
	public void onStart() {
		this.experienceTracker.start(Skill.MINING);
	}
	
	@Override
	public int onLoop() throws InterruptedException {
		statusTracker.update();
		for(AsyncTask task : antibanTasks) {
			if(task.activate()) {
				service.execute(task);
			}
		}
		for(ScriptTask task : tasks) {
			if(task.activate(this)) {
				statusTracker.setCurrentTask(task);
				return task.execute(this);
			}
		}
		return 1000;
	}
	
	@Override
	public void onPaint(Graphics graphics) {
		graphics.setColor(Constant.BLACK_OPAQUE);
		graphics.fillRect(7, 344, 300, 130);
		String[] strings = new String[] {
				"Runtime: " + statusTracker.getFormattedRunTime(),
				"Experience Gained: " + this.experienceTracker.getGainedXP(Skill.MINING),
				"Experience P/H: " + this.experienceTracker.getGainedXPPerHour(Skill.MINING),
				"Essence Mined: " + statusTracker.getTotalEssenceMined(),
				"Current Task: " + (statusTracker.getCurrentTask() != null ? statusTracker.getCurrentTask().getClass().getName().replace(".class", "").replace("org.billy.essminer.task.impl.", "") : "NONE")
		};
		graphics.setColor(Color.WHITE);
		for(int i = 0; i < strings.length; ++i) {
			graphics.drawString(strings[i], 20, 364 + (i * 20));
		}
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
	
	public boolean isPlayerWithinDistance(Position p, int distance) {
		if(myPlayer().getX() >= p.getX() - distance) {
			if(myPlayer().getX() <= p.getX() + distance) {
				if(myPlayer().getY() >= p.getY() - distance) {
					if(myPlayer().getY() <= p.getY() + distance) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isDoorClosed() {
		boolean closed = true;
		List<RS2Object> objs = this.client.getCurrentRegion().getObjects();
		for(RS2Object obj : objs) {
			if(obj == null) continue;
			if(obj.getPosition().getX() == 3253 && obj.getPosition().getY() == 3399) {
				if(obj.getName().contains(Constant.DOOR_NAME)) {
					closed = false;
				}
			}
		}
		return closed;
	}
	
	public boolean isInBank() {
		return Constant.VARROCK_BANK.contains(myPlayer());
	}
	
	public boolean isInRuneShop() {
		return Constant.VARROCK_RUNE_SHOP.contains(myPlayer());
	}
	
	public boolean isInMine() {
		return closestObjectForName(Constant.RUNE_ESSENCE_NAME) != null;
	}

	public boolean hasRuneEssence() {
		return client.getInventory().contains(Constant.RUNE_ESSENCE_NAME);
	}
	
}
