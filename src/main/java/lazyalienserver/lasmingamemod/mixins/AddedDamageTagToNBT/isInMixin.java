package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageSource.class)

public class isInMixin {
    @Unique
    DamageSource damageSource=(DamageSource)(Object)this;
    @Inject(method ="isIn",at = @At("HEAD"),cancellable = true)
    void damageSourceisInMixin(TagKey<DamageType> tag, CallbackInfoReturnable<Boolean> cir){
        if(tag.toString().contains("minecraft:bypasses_cooldown")&&(damageSource.toString().contains("thrown")||damageSource.toString().contains("snowball_hit"))){
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
