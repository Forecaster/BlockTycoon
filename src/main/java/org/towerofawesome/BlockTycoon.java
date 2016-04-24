package org.towerofawesome;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.towerofawesome.init.ModBlocks;

/**
 * Created by Forecaster on 20/04/2016.
 */
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class BlockTycoon
{
  @Mod.Instance()
  public static BlockTycoon instance;
  public static Logger log;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    log = event.getModLog();
    log.info("PreInit!");
    ModBlocks.init();
    //ModTileEntities.init();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
  }
}
