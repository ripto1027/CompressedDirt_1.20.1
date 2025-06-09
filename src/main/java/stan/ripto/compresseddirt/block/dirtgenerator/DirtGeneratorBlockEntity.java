package stan.ripto.compresseddirt.block.dirtgenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.compresseddirt.block.IBlockEntity;

public class DirtGeneratorBlockEntity extends BlockEntity {
    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1024;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.is(Items.DIRT);
        }
    };

    private BlockPos target;

    public DirtGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(IBlockEntity.DIRT_GENERATOR.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel, BlockPos pPos) {
        if (pLevel.isClientSide) return;

        ItemStack stack = inventory.getStackInSlot(0);
        if (stack.isEmpty()) {
            inventory.setStackInSlot(0, new ItemStack(Items.DIRT));
        } else if (stack.getCount() < 1024) {
            stack.grow(1);
        }

        if (target != null) {
            if (pLevel.isLoaded(target)) {
                BlockEntity targetEntity = pLevel.getBlockEntity(target);
                if (targetEntity != null) {
                    targetEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(targetHandler -> {
                        ItemStack extracted = ItemHandlerHelper.insertItem(targetHandler, inventory.getStackInSlot(0), false);
                        inventory.setStackInSlot(0, extracted);
                    });
                }
            }
        }
    }

    public void setTarget(BlockPos pos) {
        target = pos;
        setChanged();
    }

    private static final String SAVE_KEY_INVENTORY = "inventory";

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound(SAVE_KEY_INVENTORY));
        if (pTag.contains("Target")) {
            target = BlockPos.of(pTag.getLong("Target"));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put(SAVE_KEY_INVENTORY, inventory.serializeNBT());
        if (target != null) {
            pTag.putLong("Target", target.asLong());
        }
    }

    public LazyOptional<ItemStackHandler> getInventoryCapability() {
        return LazyOptional.of(() -> inventory);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return getInventoryCapability().cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        getInventoryCapability().invalidate();
        super.setRemoved();
    }
}
