package org.towerofawesome;

import java.util.ArrayList;

/**
 * Created by Administratör on 2016-05-03.
 */
public class BuildingType
{
  public String name;
  public String displayName;
  public int productionRate;
  public Product[] input;
  public Product[] output;

  public BuildingType(String name, String displayName, int productionRate, Product[] input, Product[] output)
  {
    this.name = name;
    this.displayName = displayName;
    this.productionRate = productionRate;
    this.input = input;
    this.output = output;
  }
}
