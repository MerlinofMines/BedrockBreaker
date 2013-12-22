package com.merlin.bukkit.plugins.BedrockBreaker;

/*
    This file is part of BedrockBreaker

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import static com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreakerTickTracker.addDamageTick;
import static com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreakerTickTracker.clearDamageTicks;
import static com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreakerTickTracker.getDamageTicks;
import static com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreakerTickTracker.removeOldDamageTicks;

import java.util.Date;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.merlin.bukkit.plugins.merlin.core.permissions.PermissionsUtil;

public class BedrockBreakerEventListener implements Listener {

	private BedrockBreaker plugin;
	
	public BedrockBreakerEventListener(BedrockBreaker plugin) {
		this.plugin = plugin;
	}

	// This is just one possible event you can hook.
	// See http://jd.bukkit.org/apidocs/ for a full event list.

	// All event handlers must be marked with the @EventHandler annotation 
	// The method name does not matter, only the type of the event parameter
	// is used to distinguish what is handled.

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getBlockPlaced().getType()==Material.BEDROCK) {
			if(!plugin.getConfig().getBoolean(plugin.PLACEMENT_ENABLED)) {
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('\u0026',"&4Placing Bedrock is not allowed."));
				event.setCancelled(true);
				return;
			}
			
			if(!PermissionsUtil.hasPermission(event.getPlayer(),"bedrock.place")) {
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('\u0026',"&4You do not have permission to place bedrock"));
				event.setCancelled(true);
				return;
			}
		}	
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent event) {
		if(event.getBlock().getType().equals(Material.BEDROCK) && event.getPlayer().getItemInHand().getType()==Material.DIAMOND_PICKAXE) {
			if(!plugin.getConfig().getBoolean(plugin.MINING_ENABLED)) {
				return;
			}
			
			if(!!PermissionsUtil.hasPermission(event.getPlayer(),"bedrock.mine")) {
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('\u0026',"&4You do not have permission to mine bedrock."));
				return;
			}

			boolean debug = plugin.getConfig().getBoolean(plugin.DEBUG_ENABLED);
			List<Date> oldTicks = getDamageTicks(event.getBlock(),event.getPlayer());

			//Clear ticks if player has stopped mining for too long.
			if(oldTicks.size()>0) {
				long lastTickTime = oldTicks.get(oldTicks.size()-1).getTime();
				long now = new Date().getTime();
				if(debug) {
					event.getPlayer().sendMessage("Time since last mined (ms): " + (now - lastTickTime));
				}
				
				if(now - lastTickTime >plugin.getConfig().getInt(plugin.MINING_GAP)) {
					clearDamageTicks(event.getBlock(),event.getPlayer());
				}
			}
			

			
			addDamageTick(event.getBlock(), event.getPlayer());
			removeOldDamageTicks(event.getBlock(),event.getPlayer(),plugin.getConfig().getInt(plugin.MINING_KEEPALIVE));
			List<Date> ticks = getDamageTicks(event.getBlock(),event.getPlayer());
			int damageTicks = ticks.size();
			
			//Since we just added one, this is a safe call.
			long youngestTick = ticks.get(0).getTime();
			long oldestTick = ticks.get(ticks.size()-1).getTime();
			
			long timeMining = (oldestTick - youngestTick);

			if(debug) {
				event.getPlayer().sendMessage("Damage Ticks: " +damageTicks);
				event.getPlayer().sendMessage("Mining Time (ms): " + timeMining);
			}

			if(damageTicks>=plugin.getConfig().getInt(plugin.MINING_STRIKES) && timeMining >= plugin.getConfig().getInt(plugin.MINING_TIME)) {
				BlockBreakEvent blockBreakEvent = new BlockBreakEvent(event.getBlock(), event.getPlayer());
				plugin.getServer().getPluginManager().callEvent(blockBreakEvent);
				if(!blockBreakEvent.isCancelled()) {
					if(plugin.getConfig().getBoolean(plugin.MINING_DROP_ENABLED)) {
						event.getBlock().breakNaturally();
					} else {
						event.getBlock().breakNaturally(null);
					}
					
					event.getPlayer().getWorld().playEffect(event.getBlock().getLocation(),Effect.STEP_SOUND,7,30);
					
					clearDamageTicks(event.getBlock());
					
					ItemStack itemstack = event.getItemInHand();
					itemstack.setDurability((short) (itemstack.getDurability()+getBedrockDamage(event)));
					if(itemstack.getDurability()>=Material.DIAMOND_PICKAXE.getMaxDurability()) {
						itemstack.setAmount(0);
						event.getPlayer().setItemInHand(itemstack);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onRecipeCraft(CraftItemEvent event) {
		if(event.getRecipe().getResult().getType()==Material.BEDROCK) {
			if(!event.getWhoClicked().hasPermission("bedrock.craft") && !event.getWhoClicked().hasPermission("bedrock.*")) {
				if(event.getWhoClicked() instanceof Player) {
					((Player)event.getWhoClicked()).sendMessage(ChatColor.translateAlternateColorCodes('\u0026', "&4You do not have permission to craft bedrock."));
				}
				event.setCancelled(true);
			}
		}
	}
	
	private short getBedrockDamage(BlockDamageEvent event) {
		return (short)plugin.getConfig().getInt(plugin.MINING_DAMAGE);
	}
}
