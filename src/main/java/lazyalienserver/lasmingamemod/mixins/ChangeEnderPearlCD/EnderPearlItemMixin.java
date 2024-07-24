package lazyalienserver.lasmingamemod.mixins.ChangeEnderPearlCD;

import lazyalienserver.lasmingamemod.utils.ItemStackComponentHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnderPearlItem.class)
public class EnderPearlItemMixin {
    //用Overwrite写的矢山,不过能跑,不动了(
    /**
     * @author
     * @reason
     */
    @Overwrite
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        int CoolDownTick=20;//默认20tick
        EnderPearlItem enderPearlItem = (EnderPearlItem) (Object)this;//定义this
        ItemStack itemStack = user.getStackInHand(hand);
        NbtComponent nbtComponent = ItemStackComponentHelper.getNbt(itemStack, DataComponentTypes.CUSTOM_DATA);//获得持有物品的custom_data内容
        if (nbtComponent!=null&&nbtComponent.contains("CoolDownTick")){//判断是否不为null和是由有CoolDownTick标签,这俩别互换位置,危险
            CoolDownTick=nbtComponent.copyNbt().getInt("CoolDownTick");
        } else if (nbtComponent!=null&&nbtComponent.contains("CoolDownSecond")) {//判断是否不为null和是由有CoolDownSecond标签,这俩别互换位置,危险
            CoolDownTick=nbtComponent.copyNbt().getInt("CoolDownSecond")*20;
        }
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(enderPearlItem, CoolDownTick);
        if (!world.isClient) {
            EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, user);
            enderPearlEntity.setItem(itemStack);
            enderPearlEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(enderPearlEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(enderPearlItem));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
