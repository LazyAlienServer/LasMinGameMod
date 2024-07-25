package lazyalienserver.lasmingamemod.mixins.AddedDamageTagToNBT;

import lazyalienserver.lasmingamemod.utils.ItemStackComponentList;
import net.minecraft.component.DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DataComponentTypes.class)
public class DataComponentTypesMixin {
    static{
        ItemStackComponentList.init();
    }
}
