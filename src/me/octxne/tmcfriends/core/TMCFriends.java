package me.octxne.tmcfriends.core;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.octxne.tmcfriends.commands.FriendCommand;
import me.octxne.tmcfriends.commands.FriendsCommand;
import me.octxne.tmcfriends.commands.UnfriendCommand;
import me.octxne.tmcfriends.core.file.files.ConfigurationFile;
import me.octxne.tmcfriends.listeners.EntityDamageByEntityListener;
import me.octxne.tmcfriends.listeners.PlayerJoinListener;
import me.octxne.tmcfriends.utilities.Printer;
import me.octxne.tmcfriends.utilities.type.PrinterType;

public class TMCFriends extends JavaPlugin
{
	private static TMCFriends instance;
	
	private String name, author;
	
	private double version;
	
	@Override
	public void onEnable()
	{
		this.setPlugin(this);
		this.setPluginName("TMCFriends");
		this.setPluginAuthor("Octxne");
		this.setPluginVersion(0.1D);
		this.registerCommands();
		this.registerListeners();
		this.setupFiles();
		
		Printer.printToConsole("v" + this.getPluginVersion() + " developed by " + this.getPluginAuthor() + " has been enabled!", PrinterType.NORMAL);
	}
	
	@Override
	public void onDisable()
	{
		Printer.printToConsole("v" + this.getPluginVersion() + " developed by \"" + this.getPluginAuthor() + "\" has been disabled!", PrinterType.NORMAL);
		
		this.setPlugin(null);
	}
	
	public static TMCFriends getInstance()
	{
		return instance;
	}
	
	public String getPluginName()
	{
		return this.name;
	}
	
	public String getPluginAuthor()
	{
		return this.author;
	}
	
	public double getPluginVersion()
	{
		return this.version;
	}
	
	public String getPrefix()
	{
		return ConfigurationFile.getFile().getConfiguration().getString("tmcfriends.settings.prefix");
	}
	
	public void setPlugin(TMCFriends plugin)
	{
		instance = plugin;
	}
	
	public void setPluginName(String name)
	{
		this.name = name;
	}
	
	public void setPluginAuthor(String author)
	{
		this.author = author;
	}
	
	public void setPluginVersion(Double version)
	{
		this.version = version;
	}
	
	public void setPrefix(String prefix)
	{
		ConfigurationFile.getFile().getConfiguration().set("tmcfriends.settings.prefix", prefix);
		ConfigurationFile.getFile().saveConfiguration();
	}
	
	public void registerCommand(String name, CommandExecutor executor)
	{
		this.getCommand(name).setExecutor(executor);
	}
	
	public void registerListener(Listener listener)
	{
		this.getServer().getPluginManager().registerEvents(listener, this);
	}
	
	private void registerCommands()
	{
		this.registerCommand("friend", new FriendCommand());
		this.registerCommand("friends", new FriendsCommand());
		this.registerCommand("unfriend", new UnfriendCommand());
	}
	
	private void registerListeners()
	{
		this.registerListener(new EntityDamageByEntityListener());
		this.registerListener(new PlayerJoinListener());
	}
	
	private void setupFiles()
	{
		ConfigurationFile.loadDefaults();
	}
}
