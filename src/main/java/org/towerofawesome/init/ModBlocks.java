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
  private static final BlockGenerator generator = new BlockGenerator("generator", Material.iron);

  public static void init()
  {
    GameRegistry.registerBlock(generator, generator.getUnlocalizedName());
    BlockTycoon.log.info("Registering blocks!");
    BlockTycoon.log.info("Register " + generator.getUnlocalizedName());
  }
}
