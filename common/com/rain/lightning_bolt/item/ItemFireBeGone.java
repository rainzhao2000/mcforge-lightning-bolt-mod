package com.rain.lightning_bolt.item;

import java.util.List;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.lib.Names;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemFireBeGone extends Item {

  public ItemFireBeGone(String name) {
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(LightningBolt.tabLightningBolt);
	setMaxStackSize(10); // 1 = durability, 1-64 = consumption
  }
 
  public void addRecipes() {
	GameRegistry.addShapedRecipe(new ResourceLocation(LightningBolt.MOD_ID, Names.FIRE_BE_GONE_ITEM), new ResourceLocation(LightningBolt.MOD_ID, Names.FIRE_BE_GONE_ITEM), new ItemStack(this, 10),
	  " p ",
	  "pbp",
	  " p ",
	  'b', Items.WATER_BUCKET, 'p', PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.WATER));
  }
  
  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	ItemStack stack = playerIn.getHeldItem(handIn);
	if (!worldIn.isRemote) {
	  int radius = 10;
	  BlockPos pos = playerIn.getPosition();
	  for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
		for (int y = pos.getY() - radius; y <= pos.getY() + radius; y++) {
		  for (int z = pos.getZ() - radius; z<= pos.getZ() + radius; z++) {
		    BlockPos firePos = new BlockPos(x, y, z);
			if (worldIn.getBlockState(firePos).getBlock() == Blocks.FIRE) {
			  worldIn.setBlockToAir(firePos);
			}
	      }
	    }
	  }
	  List<Entity> entities = worldIn.getEntitiesWithinAABB(Entity.class, playerIn.getEntityBoundingBox().grow(radius));
	  entities.forEach(entity -> entity.extinguish());
	  worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 4.0F, 1.0F);
	  stack.shrink(1);
	  return new ActionResult(EnumActionResult.SUCCESS, stack);
	} else {
	  return new ActionResult(EnumActionResult.FAIL, stack);
	}
  }
  
}
