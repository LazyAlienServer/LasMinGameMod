package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import lazyalienserver.lasmingamemod.MixinInterface.SnowballEntitySetHitDamage;
import lazyalienserver.lasmingamemod.utils.damage.DamageTypes;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public abstract class SnowballEntityMixin implements SnowballEntitySetHitDamage {

    private final SnowballEntity snowballEntity = (SnowballEntity) (Object) this;

    public Float HitDamage=0.0f;

    public void lasMinGameMod$setHitDamage(float damage){
        this.HitDamage=damage;
    }

    public float lasMinGameMod$getHitDamage(){
        return this.HitDamage;
    }

    @Inject(at = @At(value = "HEAD"),method = "onEntityHit")
    public void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (snowballEntity==null) return;
        entityHitResult.getEntity().damage(snowballEntity.getDamageSources().create(DamageTypes.SnowBallHit,entityHitResult.getEntity(),snowballEntity.getOwner()!=null?snowballEntity.getOwner():snowballEntity),HitDamage);
    }


}
