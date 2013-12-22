package com.merlin.bukkit.plugins.BedrockBreaker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BedrockBreakerTickTracker {

	private static Map<Block,Map<Player,List<Date>>> damageTicksMap = new HashMap<Block, Map<Player,List<Date>>>();
	
	public static synchronized List<Date> getDamageTicks(Block block, Player player) {

		//Get damage ticks for this block
		Map<Player,List<Date>> blockDamageTicks = damageTicksMap.get(block);
		
		if(blockDamageTicks == null) {//If null, simply create a new one.
			blockDamageTicks = new HashMap<Player,List<Date>>();
		} 
		
		//Get damage ticks for this player
		List<Date> playerDamageTicks = blockDamageTicks.get(player);

		if(playerDamageTicks == null) {//If null, simply create a new one and update damageTicks so that we don't have to keep re-creating.
			playerDamageTicks = new ArrayList<Date>();
			blockDamageTicks.put(player, playerDamageTicks);
			damageTicksMap.put(block,blockDamageTicks);
		}
		
		return playerDamageTicks;
	}
	
	public static synchronized void setDamageTicks(Block block, Player player,List<Date> damageTicks) {

		//Get damage ticks for this block
		Map<Player,List<Date>> blockDamageTicks = damageTicksMap.get(block);
		
		if(blockDamageTicks == null) {//If null, simply create a new one.
			blockDamageTicks = new HashMap<Player,List<Date>>();
		} 
		
		blockDamageTicks.put(player,damageTicks);
		damageTicksMap.put(block,blockDamageTicks);
	}
	
	public static synchronized void addDamageTick(Block block, Player player) {
		List<Date> damageTicks = getDamageTicks(block, player);
		damageTicks.add(new Date());
		setDamageTicks(block,player,damageTicks);
	}
	
	
	public static synchronized void clearDamageTicks(Block block, Player player) {
		setDamageTicks(block,player,new ArrayList<Date>());
	}
	
	public static synchronized void clearDamageTicks(Block block) {
		damageTicksMap.put(block, new HashMap<Player, List<Date>>());
	}
	
	public static synchronized void reset() {
		damageTicksMap = new HashMap<Block, Map<Player,List<Date>>>();
	}
	
	public static synchronized void removeOldDamageTicks(Block block, Player player, int millisecondsOld) {
		List<Date> oldTicks = getDamageTicks(block, player);
		List<Date> newTicks = new ArrayList<Date>();
		long cutoffTime = new Date().getTime()-millisecondsOld;
		for(int i = 0;i<oldTicks.size();i++) {
			if(oldTicks.get(i).getTime()>cutoffTime) {
				newTicks.add(oldTicks.get(i));
			}
		}
		setDamageTicks(block, player, newTicks);
	}

}
