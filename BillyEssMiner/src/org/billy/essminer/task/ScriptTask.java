package org.billy.essminer.task;

import org.billy.essminer.Miner;

public interface ScriptTask {
	
	public boolean activate(Miner miner);
	
	public int execute(Miner miner);

}
