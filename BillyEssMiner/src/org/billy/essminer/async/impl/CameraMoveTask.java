package org.billy.essminer.async.impl;

import org.billy.essminer.Miner;
import org.billy.essminer.async.AsyncTask;
import org.osbot.script.MethodProvider;

public class CameraMoveTask extends AsyncTask {

	public CameraMoveTask(Miner miner) {
		super(miner, 10, .95);
	}

	@Override
	public void execute() {
		try {
			getMiner().client.rotateCameraPitch(MethodProvider.random(22, 67));
			getMiner().client.rotateCameraToAngle(MethodProvider.random(0, 360));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
