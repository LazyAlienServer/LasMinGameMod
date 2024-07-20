package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import lazyalienserver.lasmingamemod.MixinInterface.SnowballEntitySetHitDamage;
import lazyalienserver.lasmingamemod.utils.damage.DamageTypes;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public class SnowballEntityMixin implements SnowballEntitySetHitDamage {

    private final ThrownItemEntity snowballEntity = (ThrownItemEntity) (Object) this;

    public Float HitDamage=0.0f;

    public void lasMinGameMod$setHitDamage(float damage){
        this.HitDamage=damage;
    }

    @Inject(at = @At(value = "HEAD"),method = "onEntityHit")
    public void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){

        if (snowballEntity==null) return;
        entityHitResult.getEntity().damage(snowballEntity.getDamageSources().create(DamageTypes.SnowBallHit,entityHitResult.getEntity(),snowballEntity.getOwner()!=null?snowballEntity.getOwner():snowballEntity),HitDamage);
    }

}
