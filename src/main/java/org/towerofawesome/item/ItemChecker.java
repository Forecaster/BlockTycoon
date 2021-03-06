package org.towerofawesome.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.Controller;
import org.towerofawesome.Product;
import org.towerofawesome.tileentity.TileEntityPort;
import org.towerofawesome.util.References;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Forecaster on 05/05/2016 for the BlockTycoon project.
 */
public class ItemChecker extends Item
{
  public String name;

  public ItemChecker()
  {
    this.name = "checker";
    super.setUnlocalizedName(References.MOD_ID + ":" + this.name);
    super.setCreativeTab(CreativeTabs.tabRedstone);
    super.setTextureName(References.MOD_ID + ":" + this.name);
    super.setMaxStackSize(1);
  }

  //@Override
  //public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
  //{
  //  return stack;
  //}

  @Override
  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int v, float pies, float trump, float birds)
  {
    if (!world.isRemote)
    {
      TileEntity target = world.getTileEntity(x, y, z);
      if (target != null && target instanceof TileEntityPort)
      {
        TileEntityPort port = (TileEntityPort) target;
        if (port.controllerId != null)
        {
          if (BlockTycoon.controllers.containsKey(port.controllerId))
          {
            Controller controller = BlockTycoon.controllers.get(port.controllerId);
            List<Product> input = controller.type.inputs;
            List<Product> output = controller.type.outputs;
            HashMap<Product, Integer> inputs = controller.inputs;
            HashMap<Product, Integer> outputs = controller.outputs;

            player.addChatMessage(new ChatComponentTranslation("Type: " + controller.type.displayName));
            player.addChatMessage(new ChatComponentTranslation("Address: " + port.controllerId.toString()));
            player.addChatMessage(new ChatComponentTranslation("Inputs:"));
            if (input != null)
            {
              for (Product anInput : input)
              {
                player.addChatMessage(new ChatComponentTranslation(anInput.displayName + ": " + inputs.get(anInput)));
              }
            }
            else
              player.addChatMessage(new ChatComponentTranslation("No inputs for this controller."));
            player.addChatMessage(new ChatComponentTranslation("Outputs:"));
            if (outputs != null)
            {
              for (Product anOutput : output)
              {
                player.addChatMessage(new ChatComponentTranslation(anOutput.displayName + ": " + outputs.get(anOutput)));
              }
            }
            else
              player.addChatMessage(new ChatComponentTranslation("No outputs for this controller."));
          }
          else
            player.addChatMessage(new ChatComponentTranslation("Port linked to invalid address."));
        }
        else
          player.addChatMessage(new ChatComponentTranslation("This port is not linked."));
      }
    }
    return true;
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
  {
    list.add("Use on controller to print info");
    super.addInformation(stack, player, list, b);
  }
}
