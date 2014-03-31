package org.billy.essminer.util;

import java.awt.Color;

import org.osbot.script.rs2.utility.Area;

public class Constant {

	
	public static final Area VARROCK_BANK = new Area(3250, 3419, 3257, 3423);
	public static final Area VARROCK_RUNE_SHOP = new Area(3248, 3397, 3257, 3406);
	
	public static final String[] RUNE_ESSENCE_NAMES = new String[] { "Rune essence", "Pure essence" };
	public static final String BANK_NAME = "Bank booth";
	public static final String AUBURY_NAME = "Aubury";
	public static final String DOOR_NAME = "Door";
	
	public static final String MINE_ACTION = "Mine";
	public static final String BANK_ACTION = "Bank";
	public static final String AUBURY_ACTION = "Teleport";
	public static final String DOOR_ACTION = "Open";
	
	public static final int MINING_ANIMATION = 625;
	
	public static final int[] PORTAL_IDS = new int[] { 15638, 14918 };
	public static final int[] PICKAXES = new int[] { 1275, 1273, 1271, 1269, 1267, 1265 };
	
	public static final Color BLACK_OPAQUE = new Color(0, 0, 0, 170);
	
	
}
