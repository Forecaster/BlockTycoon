package org.towerofawesome.util;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created by Forecaster on 30/04/2016 for the BlockTycoon project.
 */
public class Config
{
  public static File configDirectory;
  public static File configFile;

  public static void init(FMLPreInitializationEvent event)
  {
    configDirectory = new File(event.getModConfigurationDirectory(), References.MOD_ID);
    configFile = new File(configDirectory, References.MOD_ID + ".cfg");
  }
}
