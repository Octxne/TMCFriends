package me.octxne.tmcfriends.core.file.files;

import me.octxne.tmcfriends.core.file.Filer;

public class PlayersFile
{
	private static Filer file = new Filer("players.yml");
	
	public static Filer getFile()
	{
		return file;
	}
}
