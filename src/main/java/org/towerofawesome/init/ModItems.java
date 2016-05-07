package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.towerofawesome.item.ItemChecker;
import org.towerofawesome.item.ItemCrate;
import org.towerofawesome.item.ItemLinker;
import org.towerofawesome.util.References;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public final class ModItems
{
  public static ItemCrate itemCrate = new ItemCrate();
  public static ItemLinker itemLinker = new ItemLinker();
  public static ItemChecker itemChecker = new ItemChecker();

  public static void init()
  {
    GameRegistry.registerItem(itemCrate, itemCrate.name);
    GameRegistry.registerItem(itemLinker, itemLinker.name);
    GameRegistry.registerItem(itemChecker, itemChecker.name);
  }
}
