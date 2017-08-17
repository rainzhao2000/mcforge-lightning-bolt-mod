package com.rain.lightning_bolt.block;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.init.ModItems;
import com.rain.lightning_bolt.lib.Names;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BlockLightningCharger extends Block {

  public BlockLightningCharger(String name) {
    super(Material.ROCK);
    setUnlocalizedName(name);
    setRegistryName(name);
    setCreativeTab(LightningBolt.tabLightningBolt);
    setHardness(1.0f);
    setResistance(10.0f);
    setHarvestLevel("pickaxe", 1);
  }
  
  public void addRecipes() {
	GameRegistry.addShapedRecipe(new ResourceLocation(LightningBolt.MOD_ID, Names.LIGHTNING_CHARGER_BLOCK), new ResourceLocation(LightningBolt.MOD_ID, Names.LIGHTNING_CHARGER_BLOCK), new ItemStack(this),
		" l ",
		" d ",
		"ooo",
		'l', new ItemStack(ModItems.lightningBoltItem, 1, OreDictionary.WILDCARD_VALUE), 'd', Blocks.DIAMOND_BLOCK, 'o', Blocks.OBSIDIAN);
  }
  
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	ItemStack itemstack = playerIn.getHeldItem(hand);
	if (!worldIn.isRemote && (itemstack.getItem() == ModItems.lightningBoltItem || itemstack.getItem() == ModItems.superchargedLightningBoltItem)) {
	  worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
	  itemstack.setItemDamage(0);
	}
	return true;
  }

}
