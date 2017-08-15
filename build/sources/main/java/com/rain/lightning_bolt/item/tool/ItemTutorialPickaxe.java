package com.rain.lightning_bolt.item.tool;

import com.rain.lightning_bolt.LightningBolt;
import com.rain.lightning_bolt.init.ModItems;

import net.minecraft.item.ItemPickaxe;

public class ItemTutorialPickaxe extends ItemPickaxe {
  
  public ItemTutorialPickaxe(String name) {
	super(ModItems.toolMaterial);
    setUnlocalizedName(name);
    setRegistryName(name);
    setCreativeTab(LightningBolt.tabLightningBolt);
  }
  
}
