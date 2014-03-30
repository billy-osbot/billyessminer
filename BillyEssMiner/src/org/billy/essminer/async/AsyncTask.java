package org.billy.essminer.async;

import org.billy.essminer.Miner;
import org.osbot.script.MethodProvider;

public abstract class AsyncTask implements Runnable {
	
	private final Miner miner;
	private final int secondsToWait;
	private final double threshold;
	private long lastActivated;

	public AsyncTask(Miner miner, int secondsToWait, double threshold) {
		this.miner = miner;
		this.secondsToWait = secondsToWait;
		this.threshold = threshold;
		this.lastActivated = System.currentTimeMillis();
	}
	
	public final boolean activate() {
		if(Math.random() >= getThreshold()) {
			if((System.currentTimeMillis() - getLastActivated()) >= (getSecondsToWait() * 1000)) {
				setLastActivated(System.currentTimeMillis());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public final void run() {
		execute();
	}
	
	public abstract void execute();
	
	public Miner getMiner() {
		return miner;
	}
	
	public int getSecondsToWait() {
		return MethodProvider.random(secondsToWait - 1, secondsToWait + 1);
	}
	
	public double getThreshold() {
		return threshold;
	}
	
	public long getLastActivated() {
		return lastActivated;
	}

	public void setLastActivated(long lastActivated) {
		this.lastActivated = lastActivated;
	}
	
}
