package org.towerofawesome.util;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.towerofawesome.BuildingType;
import org.towerofawesome.Product;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Forecaster on 30/04/2016 for the BlockTycoon project.
 */
public class Config
{
  public static File configDirectory;
  public static File configFile;

  public static HashMap<String, BuildingType> buildingTypes = new HashMap<String, BuildingType>();
  public static HashMap<String, Product> products = new HashMap<String, Product>();

  //Config values
  public static double globalProductionRate = 1.0;

  public static void init(FMLPreInitializationEvent event)
  {
    configDirectory = new File(event.getModConfigurationDirectory(), References.MOD_ID);
    configFile = new File(configDirectory, References.MOD_ID + ".cfg");

    createProduct("timber", "Timber", 1, 2000);
    createProduct("planks", "Planks", 1, 2000);
    createProduct("furniture", "Furniture", 2, 2000);

    createBuildingType("lumberyard", "Lumber Yard", 1, null, new Product[] {products.get("timber")}, null, new int[] {1});
    createBuildingType("sawmill", "Sawmill", 1, new Product[] {products.get("timber")}, new Product[] {products.get("planks")}, new int[] {1}, new int[] {1});
    createBuildingType("furniturefactory", "Furniture Factory", 1, new Product[] {products.get("planks")}, new Product[] {products.get("furniture")}, new int[] {1}, new int[] {1});
  }

  public static void createProduct(String name, String displayName, int volume, double productionTime)
  {
    products.put(name, new Product(name, displayName, volume, productionTime));
  }

  public static void createBuildingType(String name, String displayName, int productionRate, Product[] inputs, Product[] outputs, int[] inputAmounts, int[] outputAmounts)
  {
    buildingTypes.put(name, new BuildingType(name, displayName, productionRate, inputs, outputs, inputAmounts, outputAmounts));
  }
}
