package org.towerofawesome;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public class Controller
{
  UUID controllerId;
  BuildingType type;
  Date lastInput;

  public Controller(UUID id, BuildingType type)
  {
    this.controllerId = id;
    this.type = type;
  }

  public int getInventorySize()
  {
    return 64;
  }

  public int getItemsInInventory(int index, boolean isInput)
  {
    int items = 0;
    return items;
  }

  public BuildingType getType()
  {
    return this.type;
  }

  public Product[] getInput()
  {
    return this.type.input;
  }

  public Product[] getOutput()
  {
    return this.type.output;
  }
}
