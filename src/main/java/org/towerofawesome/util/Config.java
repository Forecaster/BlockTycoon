package org.towerofawesome.util;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.towerofawesome.BuildingType;
import org.towerofawesome.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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

    ArrayList<Product> inputProducts = new ArrayList<Product>();
    ArrayList<Product> outputProducts = new ArrayList<Product>();
    HashMap<Product, Integer> inputAmounts = new HashMap<Product, Integer>();
    HashMap<Product, Integer> outputAmounts = new HashMap<Product, Integer>();

    outputAmounts.put(products.get("lumber"), 1);
    outputProducts.add(products.get("lumber"));
    createBuildingType("lumberyard", "Lumber Yard", 1, null, outputProducts, null, outputAmounts);

    outputAmounts.clear();
    outputProducts.clear();
    inputProducts.add(products.get("timber"));
    inputAmounts.put(products.get("timber"), 1);
    outputProducts.add(products.get("planks"));
    outputAmounts.put(products.get("planks"), 1);
    createBuildingType("sawmill", "Sawmill", 1, inputProducts, outputProducts, inputAmounts, outputAmounts);

    inputProducts.clear();
    inputAmounts.clear();
    outputProducts.clear();
    outputAmounts.clear();
    inputProducts.add(products.get("planks"));
    inputAmounts.put(products.get("planks"), 1);
    outputProducts.add(products.get("furniture"));
    outputAmounts.put(products.get("furniture"), 1);
    createBuildingType("furniturefactory", "Furniture Factory", 1, inputProducts, outputProducts, inputAmounts, outputAmounts);
  }

  public static void createProduct(String name, String displayName, int volume, double productionTime)
  {
    products.put(name, new Product(name, displayName, volume, productionTime));
  }

  public static void createBuildingType(String name, String displayName, double productionRate, List<Product> inputs, List<Product> outputs, HashMap<Product, Integer> inputAmounts, HashMap<Product, Integer> outputAmounts)
  {
    buildingTypes.put(name, new BuildingType(name, displayName, productionRate, inputs, outputs, inputAmounts, outputAmounts));
  }
}
