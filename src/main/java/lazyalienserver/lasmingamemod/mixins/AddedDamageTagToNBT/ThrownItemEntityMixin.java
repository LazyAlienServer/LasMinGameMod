package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownItemEntity.class)
public abstract class ThrownItemEntityMixin {

    @Shadow public abstract ItemStack getStack();

    private final ThrownItemEntity thrownItemEntityEntity = (ThrownItemEntity) (Object) this;
    @Inject(at = @At(value = "HEAD"),method = "writeCustomData")
    public void writeCustomDataToNbt(WriteView view, CallbackInfo ci){
        view.putFloat("hitDamage", ((SnowballEntity)thrownItemEntityEntity).lasMinGameMod$getHitDamage());
    }

    @Inject(at = @At(value = "HEAD"),method = "readCustomData")
    public void readCustomDataFromNbt(ReadView view, CallbackInfo ci){

        if (!(thrownItemEntityEntity instanceof SnowballEntity)) return;

        float hitDamage = view.getFloat("hitDamage",-0.1f);
        if (hitDamage!=-0.1f) {
            ((SnowballEntity) thrownItemEntityEntity).lasMinGameMod$setHitDamage(hitDamage);
        }
    }
}
