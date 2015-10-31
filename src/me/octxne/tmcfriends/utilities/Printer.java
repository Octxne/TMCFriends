package me.octxne.tmcfriends.utilities;

import me.octxne.tmcfriends.core.TMCFriends;
import me.octxne.tmcfriends.utilities.type.PrinterType;

public class Printer
{
	public static void printToConsole(String text, PrinterType type)
	{
		switch (type)
		{
		case NORMAL:
			System.out.println(TMCFriends.getInstance().getPrefix() + " " + text);

			break;

		case WARNING:
			System.out.println(TMCFriends.getInstance().getPrefix() + " (WARNING) " + text);

			break;

		case ERROR:
			System.out.println(TMCFriends.getInstance().getPrefix() + " (ERROR) " + text);
		}
	}
}
