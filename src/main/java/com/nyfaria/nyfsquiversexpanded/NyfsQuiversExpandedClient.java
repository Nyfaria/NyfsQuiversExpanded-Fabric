package com.nyfaria.nyfsquiversexpanded;

import com.nyfaria.nyfsquiver.NyfsQuivers;
import com.nyfaria.nyfsquiver.client.NyfsQuiversKeybinds;
import com.nyfaria.nyfsquiver.config.QuiverInfo;
import com.nyfaria.nyfsquiver.item.QuiverItem;
import com.nyfaria.nyfsquiver.ui.ExtendedSimpleContainer;
import com.nyfaria.nyfsquiver.ui.QuiverHandledScreen;
import com.nyfaria.nyfsquiver.util.InventoryUtils;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class NyfsQuiversExpandedClient implements ClientModInitializer {

    public static final List<Item> QUIVERS = NyfsQuiversExpanded.QUIVERS;

    @Override
    public void onInitializeClient() {
        registerQuivers();
    }
    private void registerQuivers() {
        for (Item registered : QUIVERS) {
            FabricModelPredicateProviderRegistry.register(registered, new Identifier("nyfsquiver", "equipped"), (itemStack, clientWorld, livingEntity, i) -> {
                Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
                if (component.isPresent()) {
                    List<Pair<SlotReference, ItemStack>> allEquipped = component.get().getAllEquipped();
                    for (Pair<SlotReference, ItemStack> entry : allEquipped) {
                        ItemStack beep = entry.getRight();
                        if (beep == itemStack) {
                            return 1.0f;
                        }
                    }
                }
                return 0.0f;
            });
            FabricModelPredicateProviderRegistry.register(registered, new Identifier("nyfsquiver", "arrows"), (itemStack, clientWorld, livingEntity, i) -> {
                Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
                if(component.isPresent()) {
                    List<Pair<SlotReference, ItemStack>> allEquipped = component.get().getAllEquipped();
                    for (Pair<SlotReference, ItemStack> entry : allEquipped) {
                        ItemStack beep = entry.getRight();
                        if (entry.getRight().getItem() instanceof QuiverItem) {
                            QuiverInfo meow = ((QuiverItem)beep.getItem()).getTier();
                            NbtList tag = beep.getOrCreateNbt().getList("Inventory", NbtType.COMPOUND);

                            ExtendedSimpleContainer inventory = new ExtendedSimpleContainer(beep, meow.getRowWidth() * meow.getNumberOfRows());

                            InventoryUtils.fromTag(tag, inventory);
                            ItemStack itemStack4 = inventory.getStack(entry.getRight().getOrCreateNbt().getInt("current_slot"));
                            if(itemStack4.getCount() > 0)
                                return 0.0f;

                        }
                    }
                }

                return 1.0f;

            });

            TrinketRendererRegistry.registerRenderer(registered, (TrinketRenderer) registered);
        }

    }

    public static Identifier id(String name) {
        return new Identifier("nyfsquiversexpanded", name);

    }

}
