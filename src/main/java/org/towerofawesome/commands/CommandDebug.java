package org.towerofawesome.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import org.towerofawesome.BlockTycoon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2016-05-03.
 */
public class CommandDebug implements ICommand
{
  private final List<String> aliases;

  public CommandDebug()
  {
    aliases = new ArrayList<String>();
    aliases.add("btd");
  }

  @Override
  public String getCommandName()
  {
    return "btdebug";
  }

  @Override
  public String getCommandUsage(ICommandSender p_71518_1_)
  {
    return "btd item";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender sender, String[] args)
  {
    if (args.length == 0)
    {
      sender.addChatMessage(new ChatComponentTranslation("btdebug help:"));
      sender.addChatMessage(new ChatComponentTranslation("/btdebug item - Displays info about held item"));
    }
    else if (args.length > 0)
    {
      if (args[0].equals("item"))
      {
        ItemStack item = sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).getHeldItem();
        if (item != null)
        {
          sender.addChatMessage(new ChatComponentTranslation(item.getUnlocalizedName()));
        }
        else
          sender.addChatMessage(new ChatComponentTranslation("Equip an item."));
      }
    }
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
