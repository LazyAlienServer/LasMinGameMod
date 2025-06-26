package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import lazyalienserver.lasmingamemod.MixinInterface.SnowballEntitySetHitDamage;
import lazyalienserver.lasmingamemod.utils.ItemStackComponentHelper;
import lazyalienserver.lasmingamemod.utils.damage.DamageTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

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
        if (snowballEntity==null || snowballEntity.getWorld().isClient()) return;
        entityHitResult.getEntity().damage((ServerWorld) snowballEntity.getWorld(),snowballEntity.getDamageSources().create(DamageTypes.SnowBallHit,entityHitResult.getEntity(),snowballEntity.getOwner()!=null?snowballEntity.getOwner():snowballEntity),HitDamage);
    }

    @Inject(at = @At(value = "TAIL"),method = "Lnet/minecraft/entity/projectile/thrown/SnowballEntity;<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V")
    private void init(World world, LivingEntity owner, ItemStack stack, CallbackInfo ci){
        NbtComponent nbtComponent = ItemStackComponentHelper.getNbt(stack, DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent==null||!nbtComponent.contains("hitDamage")) return;
        Optional<Float> hitDamage = nbtComponent.copyNbt().getFloat("hitDamage");
        snowballEntity.lasMinGameMod$setHitDamage(hitDamage.orElse(0.0f));
    }
}
