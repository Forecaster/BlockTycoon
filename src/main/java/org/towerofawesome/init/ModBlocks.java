package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import org.towerofawesome.References;
import org.towerofawesome.block.BlockGenerator;

/**
 * Created by Forecaster on 22/04/2016 for the BlockTycoon project.
 */
public class ModBlocks
{
  public static final BlockGenerator generator = new BlockGenerator("generator", Material.iron);

  public static void init()
  {
    References.log("Registering blocks!");
    GameRegistry.registerBlock(generator, generator.getUnlocalizedName());
  }
}
