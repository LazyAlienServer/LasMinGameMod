package lazyalienserver.lasmingamemod.MixinInterface;

public interface SnowballEntitySetHitDamage {

    default void lasMinGameMod$setHitDamage(float damage){}

    default float lasMinGameMod$getHitDamage(){
        return 0;
    }
}
