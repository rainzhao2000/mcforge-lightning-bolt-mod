package com.rain.lightning_bolt.item;

import java.util.List;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.init.ModItems;
import com.rain.lightning_bolt.lib.Names;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class ItemSuperchargedLightningBolt extends Item {

  public float maxExplosionStrength;

  public ItemSuperchargedLightningBolt(String name) {
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(LightningBolt.tabLightningBolt);
	setMaxStackSize(1); // 1 = durability, 1-64 = consumption
	setMaxDamage(42); // <-- + 1 = #uses
	this.maxExplosionStrength = 20.0F;
  }
		
  public void addRecipes() {
	GameRegistry.addShapedRecipe(new ResourceLocation(LightningBolt.MOD_ID, Names.SUPERCHARGED_LIGHTNING_BOLT_ITEM), new ResourceLocation(LightningBolt.MOD_ID, Names.SUPERCHARGED_LIGHTNING_BOLT_ITEM), new ItemStack(this),
	  "ttt",
	  "tlt",
	  "ttt",
	  't', Blocks.TNT, 'l', ModItems.lightningBoltItem);
  }
  
  @Override
  public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
    tooltip.add("\u00A7a" + "Hold right click to");
    tooltip.add("\u00A7a" + "charge item.");
    tooltip.add("\u00A7a" + "Release to strike");
    tooltip.add("\u00A7a" + "lightning explosion.");
    tooltip.add("\u00A7c" + "Explosion size depends");
    tooltip.add("\u00A7c" + "on duration of hold.");
  }
  
  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    playerIn.setActiveHand(handIn);
    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
  }
  
  @Override
  public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
    if (!worldIn.isRemote) {
      int charge = getMaxItemUseDuration(stack) - timeLeft;
      if (charge < 0) return;
      float explosionStrength = (float)charge / 5.0F;
      if (explosionStrength > maxExplosionStrength) return;
      BlockPos pos = rayTrace(entityLiving, 256, 1.0F).getBlockPos();
      worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false));
      worldIn.newExplosion(entityLiving, pos.getX(), pos.getY(), pos.getZ(), explosionStrength, true, true);
      stack.damageItem(1, entityLiving);
    }
  }
  
  public RayTraceResult rayTrace(Entity entity, double blockReachDistance, float partialTicks) {
    Vec3d vec3d = entity.getPositionEyes(partialTicks);
    Vec3d vec3d1 = entity.getLook(partialTicks);
    Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
    return entity.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
  }
  
  @Override
  public int getMaxItemUseDuration(ItemStack stack) {
    return 72000;
  }

}
