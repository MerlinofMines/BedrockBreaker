package com.merlin.bukkit.plugins.BedrockBreaker.commands;

import java.util.ArrayList;
import java.util.List;

import com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreaker;
import com.merlin.bukkit.plugins.merlin.commands.CommandPieceListBuilder;
import com.merlin.bukkit.plugins.merlin.commands.ConfigurationDisplayCommand;
import com.merlin.bukkit.plugins.merlin.commands.ListCommand;
import com.merlin.bukkit.plugins.merlin.commands.ReloadConfigurationCommand;
import com.merlin.bukkit.plugins.merlin.commands.UpdateConfigurationPropertyCommand;
import com.merlin.bukkit.plugins.merlin.core.commands.pieces.AffirmationCommandPiece;
import com.merlin.bukkit.plugins.merlin.core.commands.pieces.CommandPiece;
import com.merlin.bukkit.plugins.merlin.core.commands.pieces.IntegerAmountCommandPiece;

public class BedrockCommandLibrary {

	public static String successMessage = "Command completed successfully";
	//Label Lists
	public static List<String> miningList = new ArrayList<String>();
	public static List<String> placeList = new ArrayList<String>();
	public static List<String> craftList = new ArrayList<String>();
	public static List<String> debugList = new ArrayList<String>();
	public static List<String> infoList = new ArrayList<String>();
	
	//Mining Commands
	public static UpdateConfigurationPropertyCommand<AffirmationCommandPiece> miningEnableCommand;
	public static UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece> miningTimeCommand;
	public static UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece> miningStrikesCommand;
	public static UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece> miningKeepAliveCommand;
	public static UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece> miningGapCommand;
	public static UpdateConfigurationPropertyCommand<AffirmationCommandPiece> miningDropCommand;
	public static UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece> miningDamageCommand;
	public static ConfigurationDisplayCommand miningInfoCommand;
	
	//Placing Commands
	public static UpdateConfigurationPropertyCommand<AffirmationCommandPiece> placingEnableCommand;
	public static ConfigurationDisplayCommand placingInfoCommand;
	
	//Crafting Commands
	public static UpdateConfigurationPropertyCommand<AffirmationCommandPiece> craftingEnableCommand;
	public static ConfigurationDisplayCommand craftingInfoCommand;
	
	//Debug Commands
	public static UpdateConfigurationPropertyCommand<AffirmationCommandPiece> debugEnableCommand;
	public static ConfigurationDisplayCommand debugInfoCommand;

	//Reload command
	public static ReloadConfigurationCommand reloadConfigCommand;
	
	//Display Commands
	public static ListCommand showBedrockCommands;
	public static ListCommand showMineCommands;
	public static ListCommand showPlaceCommands;
	public static ListCommand showCraftCommands;
	public static ListCommand showDebugCommands;
	
	//Mining Patterns
	public static List<CommandPiece<?>> miningEnablePattern;
	public static List<CommandPiece<?>> miningTimePattern;
	public static List<CommandPiece<?>> miningStrikesPattern;
	public static List<CommandPiece<?>> miningKeepAlivePattern;
	public static List<CommandPiece<?>> miningGapPattern;
	public static List<CommandPiece<?>> miningDropPattern;
	public static List<CommandPiece<?>> miningDamagePattern;
	public static List<CommandPiece<?>> miningInfoPattern;
	
	//Placing Patterns
	public static List<CommandPiece<?>> placingEnablePattern;
	public static List<CommandPiece<?>> placingInfoPattern;
	
	//Crafting Patterns
	public static List<CommandPiece<?>> craftingEnablePattern;
	public static List<CommandPiece<?>> craftingInfoPattern;
	
	//Debug Patterns
	public static List<CommandPiece<?>> debugEnablePattern;
	public static List<CommandPiece<?>> debugInfoPattern;

	//Reload Pattern
	public static List<CommandPiece<?>> reloadConfigPattern;
	
	//Display Patterns
	public static List<CommandPiece<?>> bedrockPattern;
	public static List<CommandPiece<?>> minePattern;
	public static List<CommandPiece<?>> placePattern;
	public static List<CommandPiece<?>> craftPattern;
	public static List<CommandPiece<?>> debugPattern;

	
	public static void initialize(BedrockBreaker plugin) {
		initializeLists();
		initializeMiningCommands(plugin);
		initializePlacingCommands(plugin);
		initializeCraftingCommands(plugin);
		initializeDebugCommands(plugin);
		initializeMiscCommands(plugin);
		
		initializeMiningPatterns();
		initializePlacingPatterns();
		initializeCraftingPatterns();
		initializeDebugPatterns();
		initializeMiscPatterns();
		initializeDisplayPatterns();
		
		initializeDisplayCommands();
	}
	
	private static void initializeLists() {
		//Mining List
		miningList.add("mine");
//		miningList.add("mining");
//		miningList.add("mineable");
//		miningList.add("minable");

		//Placing List
		placeList.add("place");
//		placeList.add("placement");
//		placeList.add("placeable");
//		placeList.add("placing");
		
		//Crafing List
		craftList.add("craft");
//		craftList.add("crafting");
//		craftList.add("craftable");
		
		//Debug List
		debugList.add("debug");
//		debugList.add("debugging");	
		
		//Info List
		infoList.add("info");
//		infoList.add("information");
	}
	
	private static void initializeMiningCommands(BedrockBreaker plugin) {

		miningEnableCommand = new UpdateConfigurationPropertyCommand<AffirmationCommandPiece>(plugin,new AffirmationCommandPiece(),"mining.enabled");
		miningEnableCommand.setPermission("bedrock.mining.enable");
		miningEnableCommand.setDescription("Enable Bedrock Mining");
		miningEnableCommand.setSuccessMessage(successMessage);
		
		miningTimeCommand = new UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece>(plugin,new IntegerAmountCommandPiece(),"mining.time");
		miningTimeCommand.setPermission("bedrock.mining.time");
		miningTimeCommand.setDescription("Mining Time (ms)");
		miningTimeCommand.setSuccessMessage(successMessage);
		
		miningStrikesCommand = new UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece>(plugin, new IntegerAmountCommandPiece(), "mining.strikes"); 
		miningStrikesCommand.setPermission("bedrock.mining.strikes");
		miningStrikesCommand.setDescription("Mining Strikes");
		miningStrikesCommand.setSuccessMessage(successMessage);

		miningKeepAliveCommand = new UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece>(plugin, new IntegerAmountCommandPiece(), "mining.strikes"); 
		miningKeepAliveCommand.setPermission("bedrock.mining.keepAlive");
		miningKeepAliveCommand.setDescription("Mining Strike KeepAlive");
		miningKeepAliveCommand.setSuccessMessage(successMessage);
		
		miningGapCommand = new UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece>(plugin, new IntegerAmountCommandPiece(), "mining.gap"); 
		miningGapCommand.setPermission("bedrock.mining.gap");
		miningGapCommand.setDescription("Min strike gap (ms)");
		miningGapCommand.setSuccessMessage(successMessage);

		miningDropCommand = new UpdateConfigurationPropertyCommand<AffirmationCommandPiece>(plugin, new AffirmationCommandPiece(), "mining.drop");
		miningDropCommand.setPermission("bedrock.mining.drop");
		miningDropCommand.setDescription("Enable drops");
		miningDropCommand.setSuccessMessage(successMessage);
		
		miningDamageCommand = new UpdateConfigurationPropertyCommand<IntegerAmountCommandPiece>(plugin, new IntegerAmountCommandPiece(), "mining.damage");
		miningDamageCommand.setPermission("bedrock.mining.damage");
		miningDamageCommand.setDescription("Tool damage");
		miningDamageCommand.setSuccessMessage(successMessage);
		
		miningInfoCommand = new ConfigurationDisplayCommand(plugin,"Bedrock Mining Info:");
		miningInfoCommand.getPropertyDisplayMap().put("Enabled","mining.enabled");
		miningInfoCommand.getPropertyDisplayMap().put("Drops Enabled", "mining.drop");
		miningInfoCommand.getPropertyDisplayMap().put("Mining Time(ms)","mining.time");
		miningInfoCommand.getPropertyDisplayMap().put("Mining Strikes", "mining.strikes");
		miningInfoCommand.getPropertyDisplayMap().put("Mining Strike Gap(ms)", "mining.gap");
		miningInfoCommand.getPropertyDisplayMap().put("Mining Strike KeepAlive(ms)", "mining.keepAlive");
		miningInfoCommand.getPropertyDisplayMap().put("Mining Tool Damage", "mining.damage");
		miningInfoCommand.setPermission("bedrock.mining.info");
		miningInfoCommand.setDescription("Displays bedrock mining settings");
	}
	
	private static void initializePlacingCommands(BedrockBreaker plugin) {
		
		placingEnableCommand = new UpdateConfigurationPropertyCommand<AffirmationCommandPiece>(plugin, new AffirmationCommandPiece(), "placement.enabled");	
		placingEnableCommand.setPermission("bedrock.place.enable");
		placingEnableCommand.setDescription("Enables bedrock placement");
		placingEnableCommand.setSuccessMessage(successMessage);
		
		placingInfoCommand = new ConfigurationDisplayCommand(plugin,"Bedrock Placement Info:");
		placingInfoCommand.getPropertyDisplayMap().put("Enabled", "placement.enabled");
		placingInfoCommand.setPermission("bedrock.place.info");
		placingInfoCommand.setDescription("Displays bedrock placement settings");
	}
	
	private static void initializeCraftingCommands(BedrockBreaker plugin) {
		craftingEnableCommand = new RecipeLoaderCommand(plugin, new AffirmationCommandPiece(),"crafting.enabled");	
		craftingEnableCommand.setPermission("bedrock.craft.enable");
		craftingEnableCommand.setDescription("Enables bedrock crafting");
		craftingEnableCommand.setSuccessMessage(successMessage);
		
		craftingInfoCommand = new ConfigurationDisplayCommand(plugin,"Bedrock Crafting Info:");
		craftingInfoCommand.getPropertyDisplayMap().put("Enabled", "crafting.enabled");
		craftingInfoCommand.setPermission("bedrock.craft.info");
		craftingInfoCommand.setDescription("Displays bedrock crafting settings");
	}
	
	private static void initializeDebugCommands(BedrockBreaker plugin) {
		debugEnableCommand = new UpdateConfigurationPropertyCommand<AffirmationCommandPiece>(plugin, new AffirmationCommandPiece(), "debug.enabled");	
		debugEnableCommand.setPermission("bedrock.debug.enable");
		debugEnableCommand.setDescription("Enable Debug");
		debugEnableCommand.setSuccessMessage(successMessage);
		
		debugInfoCommand = new ConfigurationDisplayCommand(plugin,"Bedrock Debug Info:");
		debugInfoCommand.getPropertyDisplayMap().put("Enabled", "debug.enabled");
		debugInfoCommand.setPermission("bedrock.debug.info");
		debugInfoCommand.setDescription("Displays bedrock debug settings");
	}

	private static void initializeMiscCommands(BedrockBreaker plugin) {
		reloadConfigCommand = new ReloadConfigurationCommand(plugin);
		reloadConfigCommand.setDescription("Reloads configuration");
		reloadConfigCommand.setPermission("bedrock.reload");
		reloadConfigCommand.setSuccessMessage(successMessage);
		
	}

	private static void initializeDisplayCommands() {

		showMineCommands = new ListCommand("List of Bedrock Mine Commands:");
		showMineCommands.setDescription("Lists Mining Commands");
		showMineCommands.addPossibility(miningEnableCommand, miningEnablePattern);
		showMineCommands.addPossibility(miningKeepAliveCommand,miningKeepAlivePattern);
		showMineCommands.addPossibility(miningStrikesCommand, miningStrikesPattern);
		showMineCommands.addPossibility(miningDamageCommand, miningDamagePattern);
		showMineCommands.addPossibility(miningDropCommand, miningDropPattern);
		showMineCommands.addPossibility(miningGapCommand,miningGapPattern);
		showMineCommands.addPossibility(miningInfoCommand, miningInfoPattern);
		showMineCommands.addPossibility(miningTimeCommand, miningTimePattern);
		
		showPlaceCommands = new ListCommand("List of Bedrock Placement Commands:");
		showPlaceCommands.setDescription("Lists Placement Commands");
		showPlaceCommands.addPossibility(placingEnableCommand, placingEnablePattern);
		showPlaceCommands.addPossibility(placingInfoCommand, placingInfoPattern);
		
		showCraftCommands = new ListCommand("List of Bedrock Crafting Commands:");
		showCraftCommands.setDescription("Lists Crafting Commands");
		showCraftCommands.addPossibility(craftingEnableCommand,craftingEnablePattern);
		showCraftCommands.addPossibility(craftingInfoCommand,craftingInfoPattern);
		
		showDebugCommands = new ListCommand("List of Bedrock Debug Commands");
		showDebugCommands.setDescription("Lists Debug Commands");
		showDebugCommands.addPossibility(debugEnableCommand,debugEnablePattern);
		showDebugCommands.addPossibility(debugInfoCommand, debugInfoPattern);
		
		showBedrockCommands = new ListCommand("List of Bedrock Commands");
		showBedrockCommands.addPossibility(showMineCommands, minePattern);
		showBedrockCommands.addPossibility(showPlaceCommands, placePattern);
		showBedrockCommands.addPossibility(showCraftCommands, craftPattern);
		showBedrockCommands.addPossibility(showDebugCommands, debugPattern);
		showBedrockCommands.addPossibility(reloadConfigCommand, reloadConfigPattern);
	}

	private static void initializeMiningPatterns() {
		miningEnablePattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).affirmation(miningEnableCommand.getCommandPiece()).getPieces();
		miningKeepAlivePattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label("keepAlive").integer(miningKeepAliveCommand.getCommandPiece()).getPieces();
		miningStrikesPattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label("strikes").integer(miningStrikesCommand.getCommandPiece()).getPieces();
		miningDamagePattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label("damage").integer(miningDamageCommand.getCommandPiece()).getPieces();
		miningDropPattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label("drop").affirmation(miningDropCommand.getCommandPiece()).getPieces();
		miningGapPattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label("gap").integer(miningGapCommand.getCommandPiece()).getPieces();
		miningInfoPattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label(infoList).getPieces();
		miningTimePattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).label("time","length").integer(miningTimeCommand.getCommandPiece()).getPieces();
	}
	
	private static void initializePlacingPatterns() {
		placingEnablePattern = CommandPieceListBuilder.build().label("bedrock").label(placeList).affirmation(placingEnableCommand.getCommandPiece()).getPieces();
		placingInfoPattern = CommandPieceListBuilder.build().label("bedrock").label(placeList).label(infoList).getPieces();
	}
	
	private static void initializeCraftingPatterns() {
		craftingEnablePattern = CommandPieceListBuilder.build().label("bedrock").label(craftList).affirmation(craftingEnableCommand.getCommandPiece()).getPieces();
		craftingInfoPattern = CommandPieceListBuilder.build().label("bedrock").label(craftList).label(infoList).getPieces();
	}
	
	private static void initializeDebugPatterns() {
		debugEnablePattern = CommandPieceListBuilder.build().label("bedrock").label(debugList).affirmation(debugEnableCommand.getCommandPiece()).getPieces();
		debugInfoPattern = CommandPieceListBuilder.build().label("bedrock").label(debugList).label(infoList).getPieces();
	}
	
	private static void initializeMiscPatterns() {
		reloadConfigPattern = CommandPieceListBuilder.build().label("bedrock").label("reload").getPieces();
	}
	
	private static void initializeDisplayPatterns() {
		bedrockPattern = CommandPieceListBuilder.build().label("bedrock").getPieces();
		minePattern = CommandPieceListBuilder.build().label("bedrock").label(miningList).getPieces();
		placePattern = CommandPieceListBuilder.build().label("bedrock").label(placeList).getPieces();
		craftPattern = CommandPieceListBuilder.build().label("bedrock").label(craftList).getPieces();
		debugPattern = CommandPieceListBuilder.build().label("bedrock").label(debugList).getPieces();
	}
}
