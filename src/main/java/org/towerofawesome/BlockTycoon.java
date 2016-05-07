package org.towerofawesome;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import org.towerofawesome.commands.CommandControllers;
import org.towerofawesome.commands.CommandDebug;
import org.towerofawesome.commands.CommandTest;
import org.towerofawesome.init.ModBlocks;
import org.towerofawesome.init.ModItems;
import org.towerofawesome.init.ModTileEntities;
import org.towerofawesome.util.Config;
import org.towerofawesome.util.References;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Forecaster on 20/04/2016.
 */
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class BlockTycoon
{
  @Mod.Instance()
  public static BlockTycoon instance;
  public static Logger log;
  public static HashMap<UUID, Controller> controllers = new HashMap<UUID, Controller>();

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    log = event.getModLog();
    log.info("PreInit!");

    Config.init(event);
    ModItems.init();
    ModBlocks.init();
    ModTileEntities.init();

    UUID id1 = UUID.fromString("b6165c5a-8bc0-40e3-a81d-d30ca49036a9");
    UUID id2 = UUID.fromString("BDE46388-139E-11E6-BBC1-B207C39F6DE8");
    UUID id3 = UUID.fromString("C877E612-139E-11E6-BF7E-B207C39F6DE8");
    try
    {
      controllers.put(id1, new Controller(id1, References.getBuidingType("lumberyard")));
      controllers.put(id2, new Controller(id2, References.getBuidingType("sawmill")));
      controllers.put(id3, new Controller(id3, References.getBuidingType("furniturefactory")));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
  }

  @Mod.EventHandler
  public void serverLoad(FMLServerStartingEvent event)
  {
    event.registerServerCommand(new CommandTest());
    event.registerServerCommand(new CommandControllers());
    event.registerServerCommand(new CommandDebug());
  }
}
