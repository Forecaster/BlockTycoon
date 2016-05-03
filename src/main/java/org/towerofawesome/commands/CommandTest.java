package org.towerofawesome.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import org.towerofawesome.BlockTycoon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2016-05-03.
 */
public class CommandTest implements ICommand
{
  private final List<String> aliases;

  public CommandTest()
  {
    aliases = new ArrayList<String>();
    aliases.add("btt");
  }

  @Override
  public String getCommandName()
  {
    return "bttest";
  }

  @Override
  public String getCommandUsage(ICommandSender p_71518_1_)
  {
    return "bttest <string>";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender sender, String[] args)
  {
    sender.addChatMessage(new ChatComponentTranslation("There are " + BlockTycoon.controllers.size() + " controllers."));
  }

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender sender)
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
  {
    return null;
  }

  @Override
  public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
  {
    return false;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
}
