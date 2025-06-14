package stan.ripto.compresseddirt.block.dirtgenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.compresseddirt.block.IBlockEntity;

import java.util.Objects;

public class DirtGeneratorBlockEntity extends BlockEntity {
    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!Objects.requireNonNull(level).isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1024;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.is(Items.DIRT);
        }
    };

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> inventory);
    private BlockPos target;

    public DirtGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(IBlockEntity.DIRT_GENERATOR.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel) {
        if (pLevel.isClientSide) {
            return;
        }

        ItemStack stack = inventory.getStackInSlot(0);
        if (stack.isEmpty()) {
            inventory.setStackInSlot(0, new ItemStack(Items.DIRT));
        } else if (stack.getCount() < 1024) {
            stack.grow(1);
        }

        if (target != null && pLevel.isLoaded(target)) {
            BlockEntity targetEntity = pLevel.getBlockEntity(target);
            if (targetEntity != null) {
                targetEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(targetHandler -> {
                    ItemStack extracted = ItemHandlerHelper.insertItem(targetHandler, inventory.getStackInSlot(0), false);
                    inventory.setStackInSlot(0, extracted);
                });
            }
        }
    }

    public void setTarget(BlockPos pos) {
        target = pos;
        setChanged();
    }

    public void removeTarget() {
        if (target != null) {
            this.getUpdateTag().remove("Target");
            target = null;
            setChanged();
        }
    }

    public void dropItems() {
        ItemStack stack = inventory.getStackInSlot(0);
        int count = inventory.getStackInSlot(0).getCount();
        double result = (double) count / 64;
        double roundResult = Math.floor(result);
        int size = (int) roundResult + 1;
        int mod = count % 64;
        SimpleContainer inv = new SimpleContainer(size);
        for (int i = 0; i < inv.getContainerSize(); i++) {
            if (i != inv.getContainerSize() - 1) {
                inv.setItem(i, new ItemStack(stack.getItem(), 64));
            } else {
                inv.setItem(i, new ItemStack(stack.getItem(), mod));
            }
        }
        Containers.dropContents(Objects.requireNonNull(level), worldPosition, inv);
    }

    private static final String SAVE_KEY_INVENTORY = "inventory";
    private static final String SAVE_KEY_TARGET = "Target";

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound(SAVE_KEY_INVENTORY));
        if (pTag.contains(SAVE_KEY_TARGET)) {
            target = BlockPos.of(pTag.getLong(SAVE_KEY_TARGET));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put(SAVE_KEY_INVENTORY, inventory.serializeNBT());
        if (target != null) {
            pTag.putLong(SAVE_KEY_TARGET, target.asLong());
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handler.invalidate();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
