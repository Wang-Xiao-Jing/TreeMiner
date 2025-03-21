package ctn.tree_miner.common.items;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class CookedNetherPodGlowstoneItem extends Item {
    public CookedNetherPodGlowstoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Collection<MobEffectInstance> effects = livingEntity.getActiveEffects();
        for (MobEffectInstance effectInstance : effects) {
            Holder<MobEffect> effect = effectInstance.getEffect();
            int duration = effectInstance.getDuration() / 2;
            int amplifier = effectInstance.getAmplifier();
            if (amplifier != 255) {
                amplifier++;
            }
            boolean ambient = effectInstance.isAmbient();
            boolean visible = effectInstance.isVisible();
            boolean showIcon = effectInstance.showIcon();
            MobEffectInstance set = new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon);
            effectInstance.update(set);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
