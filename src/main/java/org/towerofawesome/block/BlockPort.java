package org.towerofawesome.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.tileentity.TileEntityPort;
import org.towerofawesome.util.References;

import java.util.UUID;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public class BlockPort extends BlockTycoonBlock
{
  public BlockPort(String unlocalizedName, Material material)
  {
    super(material);
    this.setBlockName(unlocalizedName);
    this.setBlockTextureName(References.MOD_ID + ":" + unlocalizedName);
    this.setBlockUnbreakable();
    this.setCreativeTab(CreativeTabs.tabRedstone);

    this.isBlockContainer = true;

    BlockTycoon.log.info("Create new instance block with texture name: " + References.MOD_ID + ":" + unlocalizedName);
  }

  @Override
  public boolean hasTileEntity(int metadata)
  {
    return true;
  }

  @Override
  public TileEntity createTileEntity(World world, int metadata)
  {
    TileEntityPort te = new TileEntityPort();
    te.controllerId = UUID.randomUUID();
    return te;
  }

  @Override
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float facing, float angle, float angle2)
  {
    TileEntityPort port = (TileEntityPort) world.getTileEntity(x, y, z);
    port.onClick(world, player);
    return true;
  }
}
