package com.rain.lightning_bolt.init;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.block.BlockLightningCharger;
import com.rain.lightning_bolt.lib.Names;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

  public static BlockLightningCharger lightningChargerBlock;
  public static Item lightningChargerBlockItem;
	
  public static void init() {
    lightningChargerBlock = new BlockLightningCharger(LightningBolt.RESOURCE_PREFIX + Names.LIGHTNING_CHARGER_BLOCK);
    lightningChargerBlockItem = new ItemBlock(lightningChargerBlock).setRegistryName(lightningChargerBlock.getRegistryName());
  }
  
  public static void initRecipes() {
	lightningChargerBlock.addRecipes();
  }
  
  @Mod.EventBusSubscriber(modid = LightningBolt.MOD_ID)
  public static class RegistrationHandler {
	  
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
	  event.getRegistry().registerAll(lightningChargerBlock);
	} 
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
	  event.getRegistry().registerAll(lightningChargerBlockItem);
	}
	
  }
  
  @SideOnly(Side.CLIENT)
  public static void initClient(ItemModelMesher mesher) {
	ModelResourceLocation model = new ModelResourceLocation(LightningBolt.RESOURCE_PREFIX + Names.LIGHTNING_CHARGER_BLOCK, "inventory");
	ModelLoader.registerItemVariants(lightningChargerBlockItem, model);
	mesher.register(lightningChargerBlockItem, 0, model);
  }
	
}
