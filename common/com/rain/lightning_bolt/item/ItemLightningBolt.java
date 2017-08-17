package com.rain.lightning_bolt.item;

import java.util.List;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.lib.Names;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLightningBolt extends Item {
	
  public ItemLightningBolt(String name) {
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(LightningBolt.tabLightningBolt);
	setMaxStackSize(1); // 1 = durability, 1-64 = consumption
	setMaxDamage(42); // <-- + 1 = #uses
  }
	
  public void addRecipes() {
    GameRegistry.addShapedRecipe(new ResourceLocation(LightningBolt.MOD_ID, Names.LIGHTNING_BOLT_ITEM), new ResourceLocation(LightningBolt.MOD_ID, Names.LIGHTNING_BOLT_ITEM), new ItemStack(this),
	  "n b",
	  " b ",
	  "b n",
	  'b', Items.BLAZE_ROD, 'n', Items.GOLD_NUGGET);
  }
  
  @Override
  public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
	  tooltip.add("\u00A7a" + "Right click to");
	  tooltip.add("\u00A7a" + "strike lightning.");
  }
  
  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	ItemStack itemstack = playerIn.getHeldItem(handIn);
    if (!worldIn.isRemote) {
      BlockPos pos = rayTrace(playerIn, 256, 1.0F).getBlockPos();
      worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false));
      itemstack.damageItem(1, playerIn);
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }
    return new ActionResult(EnumActionResult.FAIL, itemstack);
  }
  
  public RayTraceResult rayTrace(EntityPlayer playerIn, double blockReachDistance, float partialTicks) {
    Vec3d vec3d = playerIn.getPositionEyes(partialTicks);
    Vec3d vec3d1 = playerIn.getLook(partialTicks);
    Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
    return playerIn.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
  }
	
}
