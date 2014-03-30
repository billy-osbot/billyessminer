package org.billy.essminer.async.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.async.AsyncTask;

public class MouseMoveTask extends AsyncTask {

	public MouseMoveTask(Miner miner) {
		super(miner, 15, .97);
	}

	@Override
	public void execute() {
		try {
			getMiner().moveMouseOutsideScreen();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
