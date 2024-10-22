package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import lazyalienserver.lasmingamemod.utils.ItemStackComponentHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SnowballItem.class)
public class SnowballItemMixin {

    @Inject(at= @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"),method = "use",locals = LocalCapture.CAPTURE_FAILHARD)
    private void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, ItemStack itemStack, SnowballEntity snowballEntity){
        NbtComponent nbtComponent = ItemStackComponentHelper.getNbt(itemStack, DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent==null||!nbtComponent.contains("hitDamage")) return;
        float hitDamage = nbtComponent.copyNbt().getFloat("hitDamage");
        snowballEntity.lasMinGameMod$setHitDamage(hitDamage);
    }
}
