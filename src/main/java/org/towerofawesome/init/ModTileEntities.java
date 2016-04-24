package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.References;
import org.towerofawesome.tileentity.TileEntityGenerator;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class ModTileEntities
{
  public static void init()
  {
    GameRegistry.registerTileEntity(TileEntityGenerator.class, References.MOD_ID + ":generator");
    BlockTycoon.log.info("Register TileEntity \"generator\"");
  }
}
