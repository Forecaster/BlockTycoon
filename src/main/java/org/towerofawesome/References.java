package org.towerofawesome;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

/**
 * Created by Forecaster on 22/04/2016.
 */
public class References
{
  public static final String MOD_ID = "blocktycoon";
  public static final String MOD_NAME = "Block Tycoon";
  public static final String VERSION = "0.1.0.0";

  public static void log(String msg)
  {
    FMLLog.log(Level.INFO, "[" + MOD_ID + "] " + msg);
  }
}
