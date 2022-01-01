package com.nyfaria.nyfsquiversexpanded.mixin;

import com.nyfaria.nyfsquiversexpanded.NyfsQuiversExpanded;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(PiglinBrain.class)
public class GoldTrinketMixin {

    @Inject(method = "wearsGoldArmor", at=@At("HEAD"), cancellable = true)
    private static void wearsGoldTrinket(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){

        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
        if(component.isPresent()) {
            List<Pair<SlotReference, ItemStack>> allEquipped = component.get().getAllEquipped();
            for (Pair<SlotReference, ItemStack> entry : allEquipped) {
                if(entry.getRight().isIn(NyfsQuiversExpanded.PIGLIN_GOLD)){
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
