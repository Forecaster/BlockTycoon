package org.towerofawesome.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.tileentity.TileEntityPort;
import org.towerofawesome.util.References;
import org.towerofawesome.tileentity.TileEntityGenerator;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class ModTileEntities
{
  public static void init()
  {
    GameRegistry.registerTileEntity(TileEntityGenerator.class, References.MOD_ID + ":generator");
    GameRegistry.registerTileEntity(TileEntityPort.class, References.MOD_ID + ":port");
    BlockTycoon.log.info("Register TileEntity \"" + References.MOD_ID + "generator\"");
  }
}
