package me.octxne.tmcfriends.listeners;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.octxne.tmcfriends.core.file.files.PlayersFile;

public class EntityDamageByEntityListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityByEntityDamage(EntityDamageByEntityEvent event)
	{
		Entity entity = event.getEntity();
		
		if (entity instanceof Player && event.getDamager() instanceof Player)
		{
			Player player = (Player) entity, damager = (Player) event.getDamager();
			
			List<String> playerFriends = PlayersFile.getFile().getConfiguration().getStringList("tmcfriends.players." + player.getUniqueId() + ".friends"), damagerFriends = PlayersFile.getFile().getConfiguration().getStringList("tmcfriends.players." + damager.getUniqueId() + ".friends");
			
			if (playerFriends.contains(damager.getName()) && damagerFriends.contains(player.getName()))
			{
				event.setCancelled(true);
				
				damager.sendMessage(ChatColor.RED + "You may not attack your friends!");
			}
		}
	}
}
