package org.billy.essminer.async.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.async.AsyncTask;

public class RunTask extends AsyncTask {

	public RunTask(Miner miner) {
		super(miner, 10, 0);
	}

	@Override
	public void execute() {
		if(getMiner().client.getRunEnergy() >= 50) {
			if(!getMiner().isRunning()) {
				try {
					getMiner().setRunning(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
