package org.towerofawesome.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import org.towerofawesome.Controller;
import org.towerofawesome.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.towerofawesome.BlockTycoon.controllers;

/**
 * Created by Administratör on 2016-05-03.
 */
public class CommandControllers implements ICommand
{
  private final List<String> aliases;

  public CommandControllers()
  {
    aliases = new ArrayList<String>();
    aliases.add("bt-c");
  }

  @Override
  public String getCommandName()
  {
    return "bt-controllers";
  }

  @Override
  public String getCommandUsage(ICommandSender p_71518_1_)
  {
    return "bt-controllers list\nbt-controllers details <address>";
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
      sender.addChatMessage(new ChatComponentTranslation("There are " + controllers.size() + " controller(s)."));
    else if (args[0] != null && args[0].equals("list"))
    {

      for (Map.Entry<UUID, Controller> entry : controllers.entrySet())
      {
        sender.addChatMessage(new ChatComponentTranslation(entry.getValue().getType().displayName + ": " + entry.getKey().toString()));
      }
    }
    else if (args[0] != null && controllers.containsKey(UUID.fromString(args[0])))
    {
      Controller myController = controllers.get(UUID.fromString(args[0]));
      Product[] input = myController.getInput();
      Product[] output = myController.getOutput();

      sender.addChatMessage(new ChatComponentTranslation(myController.getType().displayName));
      if (input != null && input.length > 0)
      {
        for (Product anInput : input)
        {
          sender.addChatMessage(new ChatComponentTranslation("Input: " + anInput.displayName));
        }
      }
      else
        sender.addChatMessage(new ChatComponentTranslation("This building has no inputs."));

      if (input != null && output.length > 0)
      {
        for (Product anOutput : output)
        {
          sender.addChatMessage(new ChatComponentTranslation("Output: " + anOutput.displayName));
        }
      }
      else
        sender.addChatMessage(new ChatComponentTranslation("This building has no outputs."));
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
