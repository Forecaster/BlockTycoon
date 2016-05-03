package org.towerofawesome.util;

import org.towerofawesome.BuildingType;

/**
 * Created by Forecaster on 22/04/2016.
 */
public abstract class References
{
  public static final String MOD_ID = "blocktycoon";
  public static final String MOD_NAME = "Block Tycoon";
  public static final String VERSION = "0.1.0.0";

  public static BuildingType getBuidingType(String type) throws Exception
  {
    BuildingType buildingType = Config.buildingTypes.get(type);

    if (buildingType != null)
      return buildingType;
    else
      throw new Exception("No such building type.");
  }
}
