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

        BlockEntity above = pLevel.getBlockEntity(pPos.above());
        if (above != null) {
            above.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.DOWN).ifPresent(target -> {
                ItemStack remainder = ItemHandlerHelper.insertItem(target, inventory.getStackInSlot(0), false);
                inventory.setStackInSlot(0, remainder);
            });
        }
    }

    private static final String SAVE_KEY_INVENTORY = "inventory";

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound(SAVE_KEY_INVENTORY));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put(SAVE_KEY_INVENTORY, inventory.serializeNBT());
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
