package org.towerofawesome.util;

import org.towerofawesome.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Forecaster on 07/05/2016 for the BlockTycoon project.
 */
public final class Utilities
{
  public static Product getIndexOfHighestValue(HashMap<Product, Integer> array)
{
    int maxValue = 0;
    Product currentIndex = null;
    Set<Product> keys = array.keySet();

    for (Product key : keys)
    {
      int value = array.get(key);
      if (value < maxValue)
        currentIndex = key;
    }

    return currentIndex;
  }

  public static Product getIndexOfLowestValue(HashMap<Product, Integer> array)
  {
    int minValue = Integer.MAX_VALUE;
    Product currentIndex = null;
    Set<Product> keys = array.keySet();

    for (Product key : keys)
    {
      int value = array.get(key);
      if (value < minValue)
        currentIndex = key;
    }

    return currentIndex;
  }
}
