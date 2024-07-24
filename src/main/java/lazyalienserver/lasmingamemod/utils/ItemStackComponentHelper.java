package lazyalienserver.lasmingamemod.utils;

import lazyalienserver.lasmingamemod.LasMinGameMod;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ItemStackComponentHelper {
    public static boolean hasNbt(ItemStack itemStack, String keyName, ComponentType<?> type) {
        return !itemStack.isEmpty() && itemStack.getComponents() != null && itemStack.getComponents().contains(type);
    }

    public static <T> T getNbt(ItemStack stack, ComponentType<T> type) {
        return stack.getComponents().get(type);
    }

    public static <T> boolean putNbt(ItemStack stack,ComponentType<T> type, T value) {
        if (stack.getComponents() != null) {
            stack.set(type, value);
            return true;
        }
        return false;
    }

    public static <T> boolean removeNbt(ItemStack stack,ComponentType<?> type) {
        if (stack.getComponents() != null) {
            stack.remove(type);
            return true;
        }
        return false;
    }

    public static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(LasMinGameMod.MOD_ID,id), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }
}
