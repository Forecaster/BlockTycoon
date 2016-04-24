package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.block.BlockGenerator;

/**
 * Created by Forecaster on 22/04/2016.
 */
public class ModBlocks
{
  private static BlockGenerator generator;

  public static void init()
  {
    BlockTycoon.log.info("Registering blocks!");
    GameRegistry.registerBlock(generator = new BlockGenerator("generator", Material.iron), generator.getUnlocalizedName());
    BlockTycoon.log.info("Register " + generator.getUnlocalizedName());
  }
}
