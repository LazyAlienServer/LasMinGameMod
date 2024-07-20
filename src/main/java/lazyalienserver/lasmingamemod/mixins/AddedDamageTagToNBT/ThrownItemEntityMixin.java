package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownItemEntity.class)
public class ThrownItemEntityMixin {

    private final ThrownItemEntity thrownItemEntityEntity = (ThrownItemEntity) (Object) this;
    @Inject(at = @At(value = "HEAD"),method = "writeCustomDataToNbt")
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putFloat("hitDamage", ((SnowballEntity)thrownItemEntityEntity).lasMinGameMod$getHitDamage());
    }

    @Inject(at = @At(value = "HEAD"),method = "readCustomDataFromNbt")
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){

        if (!(thrownItemEntityEntity instanceof SnowballEntity)) return;

        if (nbt.contains("hitDamage")) {
            ((SnowballEntity) thrownItemEntityEntity).lasMinGameMod$setHitDamage(nbt.getFloat("hitDamage"));
        }
    }
}
