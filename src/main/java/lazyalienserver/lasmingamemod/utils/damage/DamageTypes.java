package lazyalienserver.lasmingamemod.utils.damage;

import lazyalienserver.lasmingamemod.LasMinGameMod;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface DamageTypes {

    RegistryKey<DamageType> SnowBallHit = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(LasMinGameMod.MOD_ID,"snowball_hit"));

}
