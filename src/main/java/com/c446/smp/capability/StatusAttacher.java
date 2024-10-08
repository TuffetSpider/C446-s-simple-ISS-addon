package com.c446.smp.capability;

import com.c446.smp.IssSmpAddon;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StatusAttacher {
    public static class StatusProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation IDENTIFIER = new ResourceLocation(IssSmpAddon.MOD_ID, "resistance");
        public static final Capability<StatusResistanceCap> STATUS_RESISTANCE_CAP = CapabilityManager.get(new CapabilityToken<StatusResistanceCap>() {
        });
        private StatusResistanceCap cap = null;
        private final LazyOptional<StatusResistanceCap> optional = LazyOptional.of(this::createStatusResistanceCap);

        private StatusResistanceCap createStatusResistanceCap() {
            if (this.cap == null) {
                return new StatusResistanceCap();
            } else {
                return this.cap;
            }
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (cap == STATUS_RESISTANCE_CAP) {
                return optional.cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(CompoundTag compoundTag) {

        }

        public static void Attach(final AttachCapabilitiesEvent<Entity> event){
            final StatusProvider provider = new StatusProvider();
            event.addCapability(StatusProvider.IDENTIFIER, provider);

        }




    }
}
