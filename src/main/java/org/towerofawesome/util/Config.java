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

  public static void init(FMLPreInitializationEvent event)
  {
    configDirectory = new File(event.getModConfigurationDirectory(), References.MOD_ID);
    configFile = new File(configDirectory, References.MOD_ID + ".cfg");

    createProduct("timber", "Timber", 1);
    createProduct("planks", "Planks", 1);
    createProduct("furniture", "Furniture", 2);

    Product[] input;
    Product[] output;

    output = new Product[] {products.get("timber")};
    createBuildingType("lumberyard", "Lumber Yard", 1, null, output);

    input = new Product[] {products.get("timber")};
    output = new Product[] {products.get("planks")};
    createBuildingType("sawmill", "Sawmill", 1, input, output);

    input = new Product[] {products.get("planks")};
    output = new Product[] {products.get("furniture")};
    createBuildingType("furniturefactory", "Furniture Factory", 1, input, output);
  }

  public static void createProduct(String name, String displayName, int volume)
  {
    products.put(name, new Product(name, displayName, volume));
  }

  public static void createBuildingType(String name, String displayName, int productionRate, Product[] input, Product[] output)
  {
    buildingTypes.put(name, new BuildingType(name, displayName, productionRate, input, output));
  }
}
