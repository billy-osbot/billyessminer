package org.billy.essminer;

import org.osbot.script.rs2.utility.Area;

public class Constant {

	
	public static final Area VARROCK_BANK = new Area(3250, 3419, 3257, 3423);
	public static final Area VARROCK_RUNE_SHOP = new Area(3250, 3399, 3255, 3404);
	
	public static final String RUNE_ESSENCE_NAME = "Rune essence";
	public static final String PORTAL_NAME = "Portal";
	public static final String BANK_NAME = "Bank booth";
	public static final String AUBURY_NAME = "Aubury";
	
	public static final String MINE_ACTION = "Mine";
	public static final String BANK_ACTION = "Bank";
	public static final String AUBURY_ACTION = "Teleport";
	public static final String[] PORTAL_ACTIONS = new String[] { "Use", "Exit" };
	
	public static final int MINING_ANIMATION = 625;
	
	public static final int[] PICKAXES = new int[] { 1265 };
	
}
