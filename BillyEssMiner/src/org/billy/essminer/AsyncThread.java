package org.billy.essminer;

import org.billy.essminer.async.AsyncTask;
import org.billy.essminer.async.impl.CameraMoveTask;
import org.billy.essminer.async.impl.MouseMoveTask;
import org.billy.essminer.async.impl.RunTask;

public class AsyncThread extends Thread {
	
	private final AsyncTask[] asyncTasks;
	public boolean running = true;
	
	public AsyncThread(Miner miner) {
		this.asyncTasks = new AsyncTask[] {
				new CameraMoveTask(miner),
				new MouseMoveTask(miner),
				new RunTask(miner)
		};
	}
	
	@Override
	public void run() {
		while(running) {
			try {
				for(AsyncTask task : asyncTasks) {
					if(task.activate()) {
						task.execute();
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
