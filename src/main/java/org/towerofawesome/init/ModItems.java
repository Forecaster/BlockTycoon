package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.towerofawesome.util.References;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public final class ModItems
{
  public static Item itemCrate;

  public static void init()
  {
    itemCrate = new Item().setUnlocalizedName("crate").setCreativeTab(CreativeTabs.tabRedstone).setTextureName(References.MOD_ID + ":crate");
    GameRegistry.registerItem(itemCrate, "crate");
  }
}
