package me.octxne.tmcfriends.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.octxne.tmcfriends.core.file.files.PlayersFile;
import me.octxne.tmcfriends.utilities.Messages;

public class FriendsCommand implements CommandExecutor
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
			
			if (arguments.length > 0)
			{
				Messages.usage(player, "/friends");
			}
			else
			{
				List<String> friends = PlayersFile.getFile().getConfiguration().getStringList("tmcfriends.players." + player.getUniqueId() + ".friends");
				
				if (!PlayersFile.getFile().getConfiguration().contains("tmcfriends.players." + player.getUniqueId() + ".friends") || friends.isEmpty())
				{
					player.sendMessage(ChatColor.RED + "Your friends list is empty!");
				}
				else
				{
					StringBuilder builder = new StringBuilder();
					
					for (String friend : friends)
					{
						builder.append(friend).append("\n");
					}
					
					String playerFriends = builder.toString();
					
					player.sendMessage(ChatColor.GRAY + "Your friends" + ChatColor.DARK_GRAY + ":\n" + ChatColor.RED + playerFriends);
				}
			}
		}
		
		return false;
	}
}
