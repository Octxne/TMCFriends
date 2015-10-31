package me.octxne.tmcfriends.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.octxne.tmcfriends.core.file.files.PlayersFile;
import me.octxne.tmcfriends.utilities.Messages;

public class FriendCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments)
	{
		if (!(sender instanceof Player))
		{
			Messages.inGameCommand(sender);
		}
		else
		{
			Player player = (Player) sender;
			
			if (arguments.length == 0 || arguments.length > 1)
			{
				Messages.usage(player, "/friend <player>");
			}
			else
			{
				Player target = Bukkit.getServer().getPlayer(arguments[0]);
				
				if (target == null || !target.isOnline())
				{
					Messages.playerNotFound(player, arguments[0]);
				}
				else
				{
					List<String> friends = PlayersFile.getFile().getConfiguration().getStringList("tmcfriends.players." + player.getUniqueId() + ".friends");
					
					if (friends.contains(target.getName()))
					{
						player.sendMessage(ChatColor.RED + "Player " + target.getName() + " is already your friend!");
					}
					else
					{
						friends.add(target.getName());
						
						PlayersFile.getFile().getConfiguration().set("tmcfriends.players." + player.getUniqueId() + ".friends", friends);
						PlayersFile.getFile().saveConfiguration();
						
						player.sendMessage(ChatColor.GRAY + "You have " + ChatColor.GREEN + "added " + ChatColor.GRAY + "player " + target.getDisplayName() + ChatColor.GRAY + " to your friends list!");
					}
				}
			}
		}
		
		return false;
	}
}
