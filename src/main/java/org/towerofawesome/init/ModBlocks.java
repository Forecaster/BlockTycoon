package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.block.BlockPort;

/**
 * Created by Forecaster on 22/04/2016.
 */
public class ModBlocks
{
  private static BlockPort port;

  public static void init()
  {
    BlockTycoon.log.info("Registering blocks!");
    GameRegistry.registerBlock(port = new BlockPort("port", Material.iron), port.getUnlocalizedName());
    BlockTycoon.log.info("Register " + port.getUnlocalizedName());
  }
}
