package me.octxne.tmcfriends.core.file.files;

import me.octxne.tmcfriends.core.TMCFriends;
import me.octxne.tmcfriends.core.file.Filer;

public class ConfigurationFile
{
	private static Filer file = new Filer("config.yml");
	
	public static Filer getFile()
	{
		return file;
	}
	
	public static void loadDefaults()
	{
		getFile().addDefault("tmcfriends.settings.prefix", "[" + TMCFriends.getInstance().getPluginName() + "]");
	}
}
