package org.towerofawesome;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import scala.util.parsing.json.JSON;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public class Controller
{
  UUID controllerId;
  String type;
  Date lastInput;
  public List<InventoryBasic> inputs;
  public List<InventoryBasic> outputs;

  public Controller(UUID id, String type)
  {
    this.controllerId = id;
    this.type = type;
    this.inputs = new ArrayList<InventoryBasic>(4);
    this.outputs = new ArrayList<InventoryBasic>(4);
  }

  public int getInventorySize()
  {
    return 64;
  }

  public int getItemsInInventory(int index, boolean isInput)
  {
    InventoryBasic inv;
    int items = 0;

    if (isInput)
      inv = inputs.get(index);
    else
      inv = outputs.get(index);

    if (inv != null)
    {
      int size = inv.getSizeInventory();

      for (int i = 0; i < size; i++)
      {
        ItemStack stack = inv.getStackInSlot(i);

        if (stack != null)
          items += stack.stackSize;
      }
    }
    return items;
  }
}
