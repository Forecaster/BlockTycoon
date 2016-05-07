package org.towerofawesome.util;

/**
 * Created by Forecaster on 07/05/2016 for the BlockTycoon project.
 */
public final class Utilities
{
  public static int getIndexOfHighestValue(int[] array)
  {
    int maxValue = Integer.MAX_VALUE;
    int index = -1;
    for (int i = 0; i < array.length; i++)
    {
      if (array[i] < maxValue)
      {
        maxValue = array[i];
        index = i;
      }
    }
    return index;
  }
}
