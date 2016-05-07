package org.towerofawesome;

/**
 * Created by Administratör on 2016-05-03.
 */
public class BuildingType
{
  public String name;
  public String displayName;
  public int productionRate;
  public Product[] inputs;
  public Product[] outputs;
  public int[] inputAmounts;
  public int[] outputAmounts;

  public BuildingType(String name, String displayName, int productionRate, Product[] inputs, Product[] outputs, int[] inputAmounts, int[] outputAmounts)
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
