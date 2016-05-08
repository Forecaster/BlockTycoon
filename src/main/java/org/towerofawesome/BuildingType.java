package org.towerofawesome;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administratör on 2016-05-03.
 */
public class BuildingType
{
  public String name;
  public String displayName;
  public double productionRate;
  public List<Product> inputs;
  public List<Product> outputs;
  public HashMap<Product, Integer> inputAmounts;
  public HashMap<Product, Integer> outputAmounts;

  public BuildingType(String name, String displayName, double productionRate, List<Product> inputs, List<Product> outputs, HashMap<Product, Integer> inputAmounts, HashMap<Product, Integer> outputAmounts)
  {
    this.name = name;
    this.displayName = displayName;
    this.productionRate = productionRate;
    this.inputs = inputs;
    this.outputs = outputs;
    this.inputAmounts = inputAmounts;
    this.outputAmounts = outputAmounts;
  }
}
