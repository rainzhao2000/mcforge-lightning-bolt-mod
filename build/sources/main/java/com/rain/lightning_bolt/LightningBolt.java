package com.rain.lightning_bolt;

import java.util.Random;

import com.rain.lightning_bolt.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LightningBolt.MOD_ID, name = LightningBolt.MOD_NAME, version = LightningBolt.VERSION, dependencies = LightningBolt.DEPENDENCIES)
public class LightningBolt {

  // Constants
  public static final String MOD_ID = "lightning_bolt";
  public static final String MOD_NAME = "Lightning Bolt Mod";
  public static final String VERSION = "1.0";
  public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2423,)";
  public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ":"; // lightning_bolt:
  
  // Variables
  public static Random random = new Random();
  
  @Instance(MOD_ID)
  public static LightningBolt instance;
  
  @SidedProxy(clientSide = "com.rain.lightning_bolt.ClientProxy", serverSide = "com.rain.lightning_bolt.CommonProxy")
  public static CommonProxy proxy;
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.preInit(event);
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
	proxy.init(event);
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
	proxy.postInit(event);
  }
  
  public static CreativeTabs tabLightningBolt = new CreativeTabs(LightningBolt.RESOURCE_PREFIX + "tab_lightning_bolt") {
	
	@Override
    public ItemStack getTabIconItem() {
   	  return new ItemStack(ModItems.lightningBoltItem);
	}
	
  };
  
}
