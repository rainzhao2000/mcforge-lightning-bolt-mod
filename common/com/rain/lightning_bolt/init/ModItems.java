package com.rain.lightning_bolt.init;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.item.ItemFireBeGone;
import com.rain.lightning_bolt.item.ItemLightningBolt;
import com.rain.lightning_bolt.item.tool.ItemTutorialPickaxe;
import com.rain.lightning_bolt.lib.Names;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

  public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial(LightningBolt.RESOURCE_PREFIX + "tut_mat", 4, 2048, 10.0f, 4, 16);
  public static ItemLightningBolt lightningBoltItem;
  public static ItemFireBeGone fireBeGoneItem;
  public static ItemTutorialPickaxe tutorialPickaxe;
	
  public static void init() {
    lightningBoltItem = new ItemLightningBolt(LightningBolt.RESOURCE_PREFIX + Names.LIGHTNING_BOLT_ITEM);
    fireBeGoneItem = new ItemFireBeGone(LightningBolt.RESOURCE_PREFIX + Names.FIRE_BE_GONE_ITEM);
    tutorialPickaxe = new ItemTutorialPickaxe(LightningBolt.RESOURCE_PREFIX + Names.TUTORIAL_PICKAXE);
  }
  
  public static void initRecipes() {
	lightningBoltItem.addRecipes();
	fireBeGoneItem.addRecipes();
  }
  
  @Mod.EventBusSubscriber(modid = LightningBolt.MOD_ID)
  public static class RegistrationHandler {
	  
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
	  event.getRegistry().registerAll(lightningBoltItem, fireBeGoneItem, tutorialPickaxe);
	} 
	
  }
	
  @SideOnly(Side.CLIENT)
  public static void initClient(ItemModelMesher mesher) {
	ModelResourceLocation model = new ModelResourceLocation(LightningBolt.RESOURCE_PREFIX + Names.LIGHTNING_BOLT_ITEM, "inventory");
	ModelLoader.registerItemVariants(lightningBoltItem, model);
	mesher.register(lightningBoltItem, 0, model);
	
	model = new ModelResourceLocation(LightningBolt.RESOURCE_PREFIX + Names.FIRE_BE_GONE_ITEM, "inventory");
	ModelLoader.registerItemVariants(fireBeGoneItem, model);
	mesher.register(fireBeGoneItem, 0, model);
	
	model = new ModelResourceLocation(LightningBolt.RESOURCE_PREFIX + Names.TUTORIAL_PICKAXE, "inventory");
	ModelLoader.registerItemVariants(tutorialPickaxe, model);
	mesher.register(tutorialPickaxe, 0, model);
  }
  
}
