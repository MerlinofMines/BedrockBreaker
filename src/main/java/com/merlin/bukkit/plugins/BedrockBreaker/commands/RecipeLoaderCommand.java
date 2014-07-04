package com.merlin.bukkit.plugins.BedrockBreaker.commands;

import org.bukkit.command.CommandSender;

import com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreaker;
import com.merlin.bukkit.plugins.core.commands.UpdateConfigurationPropertyCommand;
import com.merlin.bukkit.plugins.core.commands.hooks.PersistableHook;
import com.merlin.bukkit.plugins.core.path.ConfigurationPath;

public class RecipeLoaderCommand extends UpdateConfigurationPropertyCommand<Boolean> {

	private BedrockBreaker breaker;
	
	public RecipeLoaderCommand(BedrockBreaker plugin,PersistableHook<Boolean> commandPiece, ConfigurationPath propertyPath) {
		super(plugin,propertyPath,commandPiece);
		this.breaker = plugin;
	}
	
	@Override
	public boolean execute(CommandSender sender) {
		super.execute(sender);
		breaker.removeRecipes();
		breaker.loadRecipes();
		return true;
	}

}
