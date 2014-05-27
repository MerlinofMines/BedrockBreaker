package com.merlin.bukkit.plugins.BedrockBreaker.commands;

import org.bukkit.command.CommandSender;

import com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreaker;
import com.merlin.bukkit.plugins.core.commands.UpdateConfigurationPropertyCommand;
import com.merlin.bukkit.plugins.core.commands.pieces.AffirmationCommandPiece;

public class RecipeLoaderCommand extends UpdateConfigurationPropertyCommand<AffirmationCommandPiece> {

	private BedrockBreaker breaker;
	
	public RecipeLoaderCommand(BedrockBreaker plugin,AffirmationCommandPiece commandPiece, String propertyPath) {
		super(plugin, commandPiece, propertyPath);
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
