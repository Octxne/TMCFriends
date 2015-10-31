package me.octxne.tmcfriends.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.octxne.tmcfriends.core.file.files.PlayersFile;

public class PlayerJoinListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if (PlayersFile.getFile().getConfiguration().contains("tmcfriends.players." + player.getUniqueId() + ".name"))
		{
			if (!PlayersFile.getFile().getConfiguration().getString("tmcfriends.players." + player.getUniqueId() + ".name").equals(player.getName()))
			{
				PlayersFile.getFile().getConfiguration().set("tmcfriends.players." + player.getUniqueId() + ".name", player.getName());
				PlayersFile.getFile().saveConfiguration();
			}
		}
		else
		{
			PlayersFile.getFile().getConfiguration().set("tmcfriends.players." + player.getUniqueId() + ".name", player.getName());
			PlayersFile.getFile().saveConfiguration();
		}
	}
}
