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
  int[] inputs;
  int[] outputs;

  public Controller(UUID id, BuildingType type)
  {
    this.controllerId = id;
    this.type = type;

    for (int i = 0; i < type.input.length; i++)
    {
      this.inputs[i] = 0;
    }

    for (int i = 0; i < type.output.length; i++)
    {
      this.outputs[i] = 0;
    }
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
