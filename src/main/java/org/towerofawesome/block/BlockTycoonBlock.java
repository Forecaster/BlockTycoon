package org.towerofawesome.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import org.towerofawesome.util.References;

/**
 * Created by Forecaster on 22/04/2016.
 */
public class BlockTycoonBlock extends Block
{
  public BlockTycoonBlock(Material material)
  {
    super(material);
  }

  public BlockTycoonBlock()
  {
    this(Material.iron);
  }

  @Override
  public String getUnlocalizedName()
  {
    return String.format("tile.%s:%s", References.MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }

  private String getUnwrappedUnlocalizedName(String unlocalizedName)
  {
    return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister)
  {
    blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
  }
}
