package org.billy.essminer;

import org.billy.essminer.task.ScriptTask;

public class StatusTracker {
	
	private final Miner miner;
	private final Long startTime;
	
	private ScriptTask currentTask;
	private int totalEssenceMined;
	private long startAmount;
	
	public StatusTracker(Miner miner) {
		this.miner = miner;
		this.startTime = System.currentTimeMillis();
	}
	
	public void update() {
		if(getMiner().isInMine()) {
			long amount = getMiner().client.getInventory().getAmount(Constant.RUNE_ESSENCE_NAME);
			if(startAmount != amount) {
				totalEssenceMined += amount - startAmount;
				startAmount = amount;
			}
		}
	}
	
	public Miner getMiner() {
		return miner;
	}
	
	public Long getStartTime() {
		return startTime;
	}
	
	public String getFormattedRunTime() {
		Long runtime = System.currentTimeMillis() - getStartTime();
		return String.format("%02d:%02d:%02d", (runtime / (1000 * 60 * 60)) % 24, (runtime / (1000 * 60)) % 60, (runtime / 1000) % 60);
	}

	public ScriptTask getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(ScriptTask currentTask) {
		this.currentTask = currentTask;
	}

	public int getTotalEssenceMined() {
		return totalEssenceMined;
	}

}
