package com.nyfaria.nyfsquiversexpanded;

import com.nyfaria.nyfsquiver.NyfsQuivers;
import com.nyfaria.nyfsquiver.config.QuiverInfo;
import com.nyfaria.nyfsquiver.item.QuiverItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NyfsQuiversExpanded implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("modid");
	public static final String MODID = "nyfsquiversexpanded";
	public static final List<Item> QUIVERS = new ArrayList<>();
	public static final Tag<Item> PIGLIN_GOLD = TagFactory.ITEM.create(new Identifier(MODID,"piglin_gold"));
	public static final Tag<Item> ENDER_MASK = TagFactory.ITEM.create(new Identifier(MODID,"ender_mask"));

	@Override
	public void onInitialize() {
		registerQuivers();
	}
	private void registerQuivers(){
		Item.Settings settings = new Item.Settings().group(NyfsQuivers.GROUP).maxCount(1);
		QuiverItem IRON_NETHERITE_QUIVER = Registry.register(Registry.ITEM, new Identifier(MODID , "iron_netherite_quiver"), new QuiverItem(new QuiverInfo("iron_netherite_quiver",9,6,true, "minecraft:item.armor.equip_netherite"),settings.fireproof()));
		QuiverItem GOLD_NETHERITE_QUIVER = Registry.register(Registry.ITEM, new Identifier(MODID , "gold_netherite_quiver"), new QuiverItem(new QuiverInfo("gold_netherite_quiver",9,6,true, "minecraft:item.armor.equip_netherite"),settings.fireproof()));
		QuiverItem EMERALD_NETHERITE_QUIVER = Registry.register(Registry.ITEM, new Identifier(MODID , "emerald_netherite_quiver"), new QuiverItem(new QuiverInfo("emerald_netherite_quiver",9,6,true, "minecraft:item.armor.equip_netherite"),settings.fireproof()));
		QuiverItem DIAMOND_NETHERITE_QUIVER = Registry.register(Registry.ITEM, new Identifier(MODID , "diamond_netherite_quiver"), new QuiverItem(new QuiverInfo("diamond_netherite_quiver",9,6,true, "minecraft:item.armor.equip_netherite"),settings.fireproof()));
		QUIVERS.add(IRON_NETHERITE_QUIVER);
		QUIVERS.add(GOLD_NETHERITE_QUIVER);
		QUIVERS.add(EMERALD_NETHERITE_QUIVER);
		QUIVERS.add(DIAMOND_NETHERITE_QUIVER);
	}
}
