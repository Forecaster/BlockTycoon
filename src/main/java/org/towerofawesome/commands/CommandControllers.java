package org.towerofawesome.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.BuildingType;
import org.towerofawesome.Controller;
import org.towerofawesome.Product;
import org.towerofawesome.tileentity.TileEntityPort;
import org.towerofawesome.util.Config;

import java.util.*;

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
    aliases.add("btc");
  }

  @Override
  public String getCommandName()
  {
    return "btcontrollers";
  }

  @Override
  public String getCommandUsage(ICommandSender p_71518_1_)
  {
    return "btcontrollers OR btcontrollers help OR btcontrollers list OR btcontrollers details <address> OR btcontrollers link <x> <y> <z> <address>";
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
    else if (args.length > 0 && args[0].equals("help"))
    {
      sender.addChatMessage(new ChatComponentTranslation("btcontrollers help:"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers - Shows current controller count"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers help - Shows this help"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers list - Shows list of controllers with types and addresses"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers types - Lists valid building types"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers create - Creates new controller and returns it's address"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers details <address> - Displays detailed info about controller"));
      sender.addChatMessage(new ChatComponentTranslation("/btcontrollers link <x> <y> <z> <address> - Links port at position with controller"));
    }
    else if (args.length > 0 && args[0].equals("types"))
    {
      Set<String> keys = Config.buildingTypes.keySet();

      sender.addChatMessage(new ChatComponentTranslation("Available building types:"));
      for (String key : keys)
      {
        sender.addChatMessage(new ChatComponentTranslation(key));
      }
    }
    else if (args.length > 0 && args[0].equals("create"))
    {
      if (args.length > 1)
      {
        BuildingType type = Config.buildingTypes.get(args[1]);

        if (type == null)
        {
          sender.addChatMessage(new ChatComponentTranslation("Type \"" + args[1] + "\" is invalid."));
          return;
        }

        UUID address = UUID.randomUUID();
        try { controllers.put(address, new Controller(address, type)); } catch (Exception e) { e.printStackTrace(); }
        sender.addChatMessage(new ChatComponentTranslation(address.toString()));
      }
      else
        sender.addChatMessage(new ChatComponentTranslation("No building type provided."));
    }
    else if (args.length > 0 && args[0].equals("list"))
    {
      int counter = 0;
      for (Map.Entry<UUID, Controller> entry : controllers.entrySet())
      {
        counter++;
        sender.addChatMessage(new ChatComponentTranslation(entry.getValue().getType().displayName + ": " + entry.getKey().toString()));
      }

      if (counter == 0)
        sender.addChatMessage(new ChatComponentTranslation("No controllers to list."));
    }
    else if (args.length > 0 && args[0].equals("link"))
    {
      int x = Integer.parseInt(args[1]);
      int y = Integer.parseInt(args[2]);
      int z = Integer.parseInt(args[3]);
      UUID address = UUID.fromString(args[4]);

      TileEntityPort port = (TileEntityPort) sender.getEntityWorld().getTileEntity(x, y, z);

      if (controllers.containsKey(address) && port != null)
      {
        Boolean result = port.setAddress(address);
        if (result)
          sender.addChatMessage(new ChatComponentTranslation("Port linked to controller!"));
        else
          sender.addChatMessage(new ChatComponentTranslation("Failed to link port to controller."));
      }
      else if (!controllers.containsKey(address))
        sender.addChatMessage(new ChatComponentTranslation("No controller with this address."));
      else if (port == null)
        sender.addChatMessage(new ChatComponentTranslation("No port at this position."));
      else
        sender.addChatMessage(new ChatComponentTranslation("Unknown error!"));
    }
    else if (args.length > 0 && args[0].equals("details"))
    {
      if (args.length > 1)
      {
        UUID address = UUID.fromString(args[1]);
        if (controllers.containsKey(address))
        {
          Controller myController = controllers.get(UUID.fromString(args[1]));
          List<Product> input = myController.getInputs();
          List<Product> output = myController.getOutputs();

          sender.addChatMessage(new ChatComponentTranslation(myController.getType().displayName));
          if (input != null && input.size() > 0)
          {
            sender.addChatMessage(new ChatComponentTranslation("Input:"));
            for (int i = 0; i < input.size(); i++)
            {
              sender.addChatMessage(new ChatComponentTranslation(input.get(i).displayName + ": " + input.get(i)));
            }
          }
          else
            sender.addChatMessage(new ChatComponentTranslation("This building has no inputs."));

          if (output != null && output.size() > 0)
          {
            sender.addChatMessage(new ChatComponentTranslation("Output:"));
            for (int i = 0; i < output.size(); i++)
            {
              sender.addChatMessage(new ChatComponentTranslation(output.get(i).displayName + ": " + output.get(i)));
            }
          }
          else
            sender.addChatMessage(new ChatComponentTranslation("This building has no outputs."));
        }
        else
          sender.addChatMessage(new ChatComponentTranslation("No controller with given address found."));
      }
      else
        sender.addChatMessage(new ChatComponentTranslation("No address given!"));
    }
  }

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender sender)
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions(ICommandSender player, String[] strings)
  {
    List<String> options = new ArrayList<String>();
    if (strings.length == 1)
    {
      options.add("help");
      options.add("list");
      options.add("types");
      options.add("create");
      options.add("details");
      options.add("link");
      return options;
    }
    else if (strings.length == 2)
    {
      if (strings[0].equals("help"))
        return null;
      else if (strings[0].equals("list"))
        return null;
      else if (strings[0].equals("types"))
        return null;
      else if (strings[0].equals("create"))
      {
        for (BuildingType type : Config.buildingTypes.values())
        {
          options.add(type.name);
        }
        return options;
      }
      else if (strings[0].equals("details"))
      {
        for (UUID address : BlockTycoon.controllers.keySet())
        {
          options.add(address.toString());
        }
        return options;
      }
      else
        return null;
    }
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
