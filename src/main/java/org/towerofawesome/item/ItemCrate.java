package org.towerofawesome.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.towerofawesome.Product;
import org.towerofawesome.init.ModItems;
import org.towerofawesome.util.Config;
import org.towerofawesome.util.References;

import java.util.List;
import java.util.Set;

/**
 * Created by Forecaster on 05/05/2016 for the BlockTycoon project.
 */
public class ItemCrate extends Item
{
  public String name;

  public ItemCrate()
  {
    this.name = "crate";
    super.setUnlocalizedName(References.MOD_ID + ":" + this.name);
    super.setCreativeTab(CreativeTabs.tabRedstone);
    super.setTextureName(References.MOD_ID + ":" + this.name);
    super.setMaxStackSize(1);
  }

  @Override
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
  {
    if (!world.isRemote)
    {
      NBTTagCompound tag = stack.getTagCompound();

      if (tag != null && tag.hasKey("goods_type"))
      {
        String myTag = tag.getString("goods_type");
        player.addChatMessage(new ChatComponentTranslation("This item says \"" + myTag + "\"!"));
        if (stack.getUnlocalizedName().equals("item." + References.MOD_ID + ":crate"))
          player.addChatMessage(new ChatComponentTranslation("This is a crate!"));
      }
      else
      {
        if (tag == null)
        {
          tag = new NBTTagCompound();
          stack.setTagCompound(tag);
        }
        player.addChatMessage(new ChatComponentTranslation("This item said \"bleeeeh\"!"));
        stack.getTagCompound().setString("goods_type", "spheres");
      }
    }
    return super.onItemRightClick(stack, world, player);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
  {
    NBTTagCompound tag = stack.getTagCompound();
    if (tag != null && tag.hasKey("goods_type"))
    {
      Product product = Config.products.get(tag.getString("goods_type"));
      list.add(product.displayName);
    }
    if (tag != null && tag.hasKey("goods_origin_z") && tag.hasKey("goods_origin_z"))
    {
      list.add("Origin: " + tag.getInteger("goods_origin_x") + ", " + tag.getInteger("goods_origin_z"));
    }
    super.addInformation(stack, player, list, b);
  }

  @Override
  public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
  {
    Set<String> myProducts = Config.products.keySet();
    for (String name : myProducts)
    {
      ItemStack stack = new ItemStack(ModItems.itemCrate);
      stack.setTagCompound(new NBTTagCompound());
      stack.stackTagCompound.setString("goods_type", name);
      list.add(stack);
    }
    super.getSubItems(item, creativeTabs, list);
  }
}
