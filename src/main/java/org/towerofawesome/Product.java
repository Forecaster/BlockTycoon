package org.towerofawesome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2016-05-03.
 */
public class Product
{
  public int volume;
  public double productionTime;
  public String name;
  public String displayName;

  public Product(String name, String displayName, int volume, double productionTime)
  {
    this.name = name;
    this.displayName = displayName;
    this.volume = volume;
    this.productionTime = productionTime;
  }
}
