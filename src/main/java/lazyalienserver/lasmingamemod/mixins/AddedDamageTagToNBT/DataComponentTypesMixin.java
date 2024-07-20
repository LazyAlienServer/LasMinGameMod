package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import lazyalienserver.lasmingamemod.utils.NBTList;
import net.minecraft.component.DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DataComponentTypes.class)
public class DataComponentTypesMixin {
    static{
        NBTList.init();
    }
}
