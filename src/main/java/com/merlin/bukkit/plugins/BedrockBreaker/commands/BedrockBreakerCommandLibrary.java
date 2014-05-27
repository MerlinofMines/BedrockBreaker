package com.merlin.bukkit.plugins.BedrockBreaker.commands;

import static com.merlin.bukkit.plugins.core.commands.CommandPieceListBuilder.build;
import static com.merlin.bukkit.plugins.core.commands.pieces.AffirmationCommandPiece.affirm;
import static com.merlin.bukkit.plugins.core.commands.pieces.GroupCommandPiece.group;
import static com.merlin.bukkit.plugins.core.commands.pieces.IntegerAmountCommandPiece.integer;
import static com.merlin.bukkit.plugins.core.commands.pieces.LabelCommandPiece.label;
import static com.merlin.bukkit.plugins.core.commands.pieces.MaterialCommandPiece.material;
import static com.merlin.bukkit.plugins.core.path.ConfigurationPath.configuration;
import static com.merlin.bukkit.plugins.core.path.SimplePath.simple;
import static com.merlin.bukkit.plugins.core.path.pieces.ColoredPathPiece.color;
import static com.merlin.bukkit.plugins.core.path.pieces.ConditionalPathPiece.condition;
import static com.merlin.bukkit.plugins.core.path.pieces.HookPathPiece.hook;
import static com.merlin.bukkit.plugins.core.path.pieces.StaticPathPiece.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.merlin.bukkit.plugins.BedrockBreaker.BedrockBreaker;
import com.merlin.bukkit.plugins.core.commands.ConfigurationDisplayCommand;
import com.merlin.bukkit.plugins.core.commands.ListCommand;
import com.merlin.bukkit.plugins.core.commands.PlayerKillCommand;
import com.merlin.bukkit.plugins.core.commands.PlayerTPCommand;
import com.merlin.bukkit.plugins.core.commands.ReloadConfigurationCommand;
import com.merlin.bukkit.plugins.core.commands.UpdateConfigurationGroupCommand;
import com.merlin.bukkit.plugins.core.commands.UpdateConfigurationPropertyCommand;
import com.merlin.bukkit.plugins.core.commands.hooks.AffirmationHook;
import com.merlin.bukkit.plugins.core.commands.hooks.GroupActionHook;
import com.merlin.bukkit.plugins.core.commands.hooks.IntegerHook;
import com.merlin.bukkit.plugins.core.commands.hooks.MaterialHook;
import com.merlin.bukkit.plugins.core.commands.pieces.CommandPiece;
import com.merlin.bukkit.plugins.core.commands.pieces.MaterialCommandPiece;
import com.merlin.bukkit.plugins.core.commands.pieces.PlayerCommandPiece;
import com.merlin.bukkit.plugins.core.commands.pieces.WorldCommandPiece;
public class BedrockBreakerCommandLibrary {

	public static String successMessage = "Command completed successfully";
	//Label Lists
	public static List<String> miningList = new ArrayList<String>();
	public static List<String> placeList = new ArrayList<String>();
	public static List<String> craftList = new ArrayList<String>();
	public static List<String> debugList = new ArrayList<String>();
	public static List<String> infoList = new ArrayList<String>();
	public static List<Material> toolList = new ArrayList<Material>();
	
	//Mining Commands
	public static UpdateConfigurationPropertyCommand<Boolean> miningEnableCommand;
	public static UpdateConfigurationPropertyCommand<Integer> miningTimeCommand;
	public static UpdateConfigurationPropertyCommand<Integer> miningStrikesCommand;
	public static UpdateConfigurationPropertyCommand<Integer> miningKeepAliveCommand;
	public static UpdateConfigurationPropertyCommand<Integer> miningGapCommand;
	public static UpdateConfigurationPropertyCommand<Boolean> miningDropCommand;
	public static UpdateConfigurationPropertyCommand<Integer> miningDamageCommand;
	public static UpdateConfigurationPropertyCommand<Boolean> miningToolEnableCommand;
	public static ConfigurationDisplayCommand miningInfoCommand;
	public static ConfigurationDisplayCommand miningToolInfoCommand;
	public static UpdateConfigurationGroupCommand miningToolManageCommand;
	
	private static MaterialHook toolHook = new MaterialHook();
	private static GroupActionHook groupHook = new GroupActionHook();
	private static MaterialHook possibleToolHook = new MaterialHook();
	
	private static MaterialCommandPiece possibleToolCommandPiece = new MaterialCommandPiece(possibleToolHook,Material.STONE_PICKAXE,Material.DIAMOND_PICKAXE,Material.IRON_PICKAXE,Material.WOOD_PICKAXE,Material.GOLD_PICKAXE);
	
	//Placing Commands
	public static UpdateConfigurationPropertyCommand<Boolean> placingEnableCommand;
	public static ConfigurationDisplayCommand placingInfoCommand;
	
	//Crafting Commands
	public static UpdateConfigurationPropertyCommand<Boolean> craftingEnableCommand;
	public static ConfigurationDisplayCommand craftingInfoCommand;
	
	//Debug Commands
	public static UpdateConfigurationPropertyCommand<Boolean> debugEnableCommand;
	public static ConfigurationDisplayCommand debugInfoCommand;

	//Reload command
	public static ReloadConfigurationCommand reloadConfigCommand;
	
	//Kill Player Command
	public static PlayerKillCommand playerKillCommand;
	public static List<CommandPiece<?>> playerKillCommandPieces;

	//TP Player Command
	public static PlayerTPCommand playerTPCommand;
	public static List<CommandPiece<?>> playerTPCommandPieces;
	
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
	public static List<CommandPiece<?>> miningToolInfoPattern;
	public static List<CommandPiece<?>> miningToolManagePattern;
	public static List<CommandPiece<?>> miningToolEnablePattern;
	
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
		
		initializeMiningPatterns(plugin);
		initializePlacingPatterns();
		initializeCraftingPatterns();
		initializeDebugPatterns();
		initializeMiscPatterns();
		initializeDisplayPatterns();
		
		initializeDisplayCommands();

		initializeKillCommand(plugin);
		initializeTPCommand(plugin);
	}
	
	private static void initializeLists() {
		//Mining List
		miningList.add("mine");

		//Placing List
		placeList.add("place");
		
		//Crafing List
		craftList.add("craft");
		
		//Debug List
		debugList.add("debug");
		
		//Info List
		infoList.add("info");
		
		toolList.add(Material.IRON_PICKAXE);
		toolList.add(Material.DIAMOND_PICKAXE);
	}
	
	private static void initializeMiningCommands(BedrockBreaker plugin) {

		miningEnableCommand = new UpdateConfigurationPropertyCommand<Boolean>(plugin,configuration(constant("mining"),constant("enabled")),new AffirmationHook());
		miningEnableCommand.setPermission("bedrock.mining.enable");
		miningEnableCommand.setDescription("Enable Bedrock Mining");
		miningEnableCommand.setSuccessMessage(simple(constant("Mining has been "),condition(miningEnableCommand.getHook(),"enabled","disabled")));
		
		miningTimeCommand = new UpdateConfigurationPropertyCommand<Integer>(plugin,configuration(constant("mining"),constant("tools"),hook(toolHook),constant("time")),new IntegerHook());
		miningTimeCommand.setPermission("bedrock.mining.time");
		miningTimeCommand.setDescription("Mining Time (ms)");
		miningTimeCommand.setSuccessMessage(successMessage);
		
		miningStrikesCommand = new UpdateConfigurationPropertyCommand<Integer>(plugin, configuration(constant("mining"),constant("tools"),hook(toolHook),constant("strikes")), new IntegerHook()); 
		miningStrikesCommand.setPermission("bedrock.mining.strikes");
		miningStrikesCommand.setDescription("Mining Strikes");
		miningStrikesCommand.setSuccessMessage(successMessage);

		miningKeepAliveCommand = new UpdateConfigurationPropertyCommand<Integer>(plugin, configuration(constant("mining"),constant("tools"),constant("keepAlive")), new IntegerHook()); 
		miningKeepAliveCommand.setPermission("bedrock.mining.keepAlive");
		miningKeepAliveCommand.setDescription("Mining Strike KeepAlive");
		miningKeepAliveCommand.setSuccessMessage(successMessage);
		
		miningGapCommand = new UpdateConfigurationPropertyCommand<Integer>(plugin, configuration(constant("mining"),constant("gap")), new IntegerHook()); 
		
		miningGapCommand.setPermission("bedrock.mining.gap");
		miningGapCommand.setDescription("Min strike gap (ms)");
		miningGapCommand.setSuccessMessage(successMessage);

		miningDropCommand = new UpdateConfigurationPropertyCommand<Boolean>(plugin, configuration(constant("mining"),constant("drop")), new AffirmationHook());
		miningDropCommand.setPermission("bedrock.mining.drop");
		miningDropCommand.setDescription("Enable drops");
		miningDropCommand.setSuccessMessage(successMessage);
		
		miningDamageCommand = new UpdateConfigurationPropertyCommand<Integer>(plugin, configuration(constant("mining"),constant("tools"),hook(toolHook),constant("damage")), new IntegerHook());
		miningDamageCommand.setPermission("bedrock.mining.damage");
		miningDamageCommand.setDescription("Tool damage");
		miningDamageCommand.setSuccessMessage(successMessage);
		
		miningToolEnableCommand = new UpdateConfigurationPropertyCommand<Boolean>(plugin, configuration(constant("mining"),constant("tools"),hook(toolHook),constant("enabled")),new AffirmationHook());
		miningToolEnableCommand.setPermission("bedrock.mining.enable");
		miningToolEnableCommand.setDescription("Enables mining for a given tool");
		miningToolEnableCommand.setSuccessMessage(simple(constant("Mining has been "),condition(miningToolEnableCommand.getHook(),"enabled","disable"),constant(" for "),hook(toolHook)));
		
		
		miningInfoCommand = new ConfigurationDisplayCommand("Bedrock Mining Info:",plugin);
		miningInfoCommand.addProperty("Enabled",configuration(constant("mining"),constant("enabled")),ChatColor.YELLOW);
		miningInfoCommand.addProperty("Drops Enabled",configuration(constant("mining"),constant("drop")),ChatColor.YELLOW);
		miningInfoCommand.addProperty("Mining Strike Gap(ms)",configuration(constant("mining"),constant("gap")),ChatColor.BLUE);
		miningInfoCommand.addProperty("Mining Strike KeepAlive(ms)",configuration(constant("mining"),constant("keepAlive")),ChatColor.BLUE);
		miningInfoCommand.setPermission("bedrock.mining.info");
		miningInfoCommand.setDescription("Displays bedrock mining settings");
		
		miningToolInfoCommand = new ConfigurationDisplayCommand(simple(constant("Bedrock Mining Info for "),color(ChatColor.DARK_AQUA,hook(toolHook)),constant(":")),plugin);
		miningToolInfoCommand.addProperty("Enabled",configuration(constant("mining"),constant("tools"),hook(toolHook),constant("enabled")),ChatColor.YELLOW);
		miningToolInfoCommand.addProperty("Drops Enabled",configuration(constant("mining"),constant("tools"),hook(toolHook),constant("drop")),ChatColor.YELLOW);
		miningToolInfoCommand.addProperty("Mining Time(ms)",configuration(constant("mining"),constant("tools"),hook(toolHook),constant("time")),ChatColor.BLUE);
		miningToolInfoCommand.addProperty("Mining Strikes",configuration(constant("mining"),constant("tools"),hook(toolHook),constant("strikes")),ChatColor.BLUE);
		miningToolInfoCommand.addProperty("Mining Tool Damage",configuration(constant("mining"),constant("tools"),hook(toolHook),constant("damage")),ChatColor.BLUE);
		miningToolInfoCommand.setPermission("bedrock.mining.info");
		miningToolInfoCommand.setDescription("Displays bedrock mining tool settings");

		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("enabled", true);
		map.put("time", 10000);
		map.put("strikes", 10);
		map.put("damage",10);
		map.put("drop",true);
		
		miningToolManageCommand = new UpdateConfigurationGroupCommand(plugin,configuration(constant("mining"),constant("tools")), groupHook,possibleToolHook,map);
	}
	
	private static void initializePlacingCommands(BedrockBreaker plugin) {
		
		placingEnableCommand = new UpdateConfigurationPropertyCommand<Boolean>(plugin, configuration(constant("placement"),constant("enabled")), new AffirmationHook());	
		placingEnableCommand.setPermission("bedrock.place.enable");
		placingEnableCommand.setDescription("Enables bedrock placement");
		placingEnableCommand.setSuccessMessage(successMessage);
		
		placingInfoCommand = new ConfigurationDisplayCommand("Bedrock Placement Info:",plugin);
		placingInfoCommand.addProperty("Enabled", configuration(constant("placement"),constant("enabled")),ChatColor.YELLOW);
		placingInfoCommand.setPermission("bedrock.place.info");
		placingInfoCommand.setDescription("Displays bedrock placement settings");
	}
	
	private static void initializeCraftingCommands(BedrockBreaker plugin) {
		craftingEnableCommand = new RecipeLoaderCommand(plugin, new AffirmationHook(),configuration(constant("crafting"),constant("enabled")));	
		craftingEnableCommand.setPermission("bedrock.craft.enable");
		craftingEnableCommand.setDescription("Enables bedrock crafting");
		craftingEnableCommand.setSuccessMessage(successMessage);
		
		craftingInfoCommand = new ConfigurationDisplayCommand("Bedrock Crafting Info:",plugin);
		craftingInfoCommand.addProperty("Enabled", configuration(constant("crafting"),constant("enabled")),ChatColor.YELLOW);
		craftingInfoCommand.setPermission("bedrock.craft.info");
		craftingInfoCommand.setDescription("Displays bedrock crafting settings");
	}
	
	private static void initializeDebugCommands(BedrockBreaker plugin) {
		debugEnableCommand = new UpdateConfigurationPropertyCommand<Boolean>(plugin, configuration(constant("debug"),constant("enabled")), new AffirmationHook());	
		debugEnableCommand.setPermission("bedrock.debug.enable");
		debugEnableCommand.setDescription("Enable Debug");
		debugEnableCommand.setSuccessMessage(successMessage);
		
		debugInfoCommand = new ConfigurationDisplayCommand("Bedrock Debug Info:",plugin);
		debugInfoCommand.addProperty("Enabled", configuration(constant("debug"),constant("enabled")),ChatColor.YELLOW);
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
		showMineCommands.addPossibility(miningToolInfoCommand,miningToolInfoPattern);
		showMineCommands.addPossibility(miningTimeCommand, miningTimePattern);
		showMineCommands.addPossibility(miningToolEnableCommand, miningToolEnablePattern);
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
		showBedrockCommands.setDescription("Lists Bedrock Commands");
		showBedrockCommands.addPossibility(showMineCommands, minePattern);
		showBedrockCommands.addPossibility(showPlaceCommands, placePattern);
		showBedrockCommands.addPossibility(showCraftCommands, craftPattern);
		showBedrockCommands.addPossibility(showDebugCommands, debugPattern);
		showBedrockCommands.addPossibility(reloadConfigCommand, reloadConfigPattern);
	}

	private static void initializeMiningPatterns(BedrockBreaker plugin) {
		miningEnablePattern = build(label("bedrock"),label(miningList),affirm(miningEnableCommand.getHook(),"enable","disable"));
		miningKeepAlivePattern = build(label("bedrock"),label(miningList),label("keepAlive"),integer(miningKeepAliveCommand.getHook()));
		miningStrikesPattern = build(label("bedrock"),label(miningList),label("strikes"),material(plugin.getConfig(),configuration(constant("mining"),constant("tools")),toolHook),integer(miningStrikesCommand.getHook()));
		miningDamagePattern = build(label("bedrock"),label(miningList),label("damage"),material(plugin.getConfig(),configuration(constant("mining"),constant("tools")),toolHook),integer(miningDamageCommand.getHook()));
		miningDropPattern = build(label("bedrock"),label(miningList),label("drop"),affirm(miningDropCommand.getHook(),"enable","disable"));
		miningGapPattern = build(label("bedrock"),label(miningList),label("gap"),integer(miningGapCommand.getHook()));
		miningInfoPattern = build(label("bedrock"),label(miningList),label(infoList));
		miningTimePattern = build(label("bedrock"),label(miningList),label("time","length"),material(plugin.getConfig(),configuration(constant("mining"),constant("tools")),toolHook),integer(miningTimeCommand.getHook()));
		miningToolInfoPattern = build(label("bedrock"),label(miningList),label(infoList),material(plugin.getConfig(),configuration(constant("mining"),constant("tools")),toolHook));
		miningToolManagePattern = build(label("bedrock"),label(miningList),group(groupHook),possibleToolCommandPiece);
		miningToolEnablePattern = build(label("bedrock"),label(miningList),affirm(miningToolEnableCommand.getHook(),"enable","disable"),material(plugin.getConfig(),configuration(constant("mining"),constant("tools")),toolHook));
	}
	
	private static void initializePlacingPatterns() {
		placingEnablePattern = build(label("bedrock"),label(placeList),affirm(placingEnableCommand.getHook(),"enable","disable"));
		placingInfoPattern = build(label("bedrock"),label(placeList),label(infoList));
	}
	
	private static void initializeCraftingPatterns() {
		craftingEnablePattern = build(label("bedrock"),label(craftList),affirm(craftingEnableCommand.getHook(),"enable","disable"));
		craftingInfoPattern = build(label("bedrock"),label(craftList),label(infoList));
	}
	
	private static void initializeDebugPatterns() {
		debugEnablePattern = build(label("bedrock"),label(debugList),affirm(debugEnableCommand.getHook(),"enable","disable"));
		debugInfoPattern = build(label("bedrock"),label(debugList),label(infoList));
	}
	
	private static void initializeMiscPatterns() {
		reloadConfigPattern = build(label("bedrock"),label("reload"));
	}
	
	private static void initializeDisplayPatterns() {
		bedrockPattern = build(label("bedrock"));
		minePattern = build(label("bedrock"),label(miningList));
		placePattern = build(label("bedrock"),label(placeList));
		craftPattern = build(label("bedrock"),label(craftList));
		debugPattern = build(label("bedrock"),label(debugList));
	}
	
	private static void initializeKillCommand(BedrockBreaker plugin) {

		PlayerCommandPiece playerHook = new PlayerCommandPiece();
		playerKillCommandPieces = build(label("bedrock"),label("kill"),playerHook);
		playerKillCommand = new PlayerKillCommand(playerHook.getHook());
		showBedrockCommands.addPossibility(playerKillCommand, playerKillCommandPieces);

	}
	
	private static void initializeTPCommand(BedrockBreaker plugin) {

		WorldCommandPiece playerHook = new WorldCommandPiece();
		playerTPCommandPieces = build(label("bedrock"),label("tp"),playerHook);
		playerTPCommand = new PlayerTPCommand(playerHook.getHook());
		showBedrockCommands.addPossibility(playerTPCommand, playerTPCommandPieces);

	}
	
}
