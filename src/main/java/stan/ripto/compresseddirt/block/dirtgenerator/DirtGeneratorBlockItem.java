package stan.ripto.compresseddirt.block.dirtgenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;
import stan.ripto.compresseddirt.block.IBlocks;

public class DirtGeneratorBlockItem extends BlockItem {
    private static final String key = "message.compresseddirt.linked";

    public DirtGeneratorBlockItem(Properties pProperties) {
        super(IBlocks.DIRT_GENERATOR.get(), pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockEntity block = level.getBlockEntity(pos);
        if (block != null) {
            if (block.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent()) {
                if (pContext.getPlayer() != null && pContext.getPlayer().isShiftKeyDown()) {
                    CompoundTag tag = pContext.getItemInHand().getOrCreateTag();
                    tag.putLong("Target", pos.asLong());
                    if (level.isClientSide) {
                        String name = block.getBlockState().getBlock().getName().getString();
                        pContext.getPlayer().displayClientMessage(Component.translatable(key, name, pos.getX(), pos.getY(), pos.getZ()), true);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(pContext);
    }

    @Override
    protected boolean placeBlock(@NotNull BlockPlaceContext pContext, @NotNull BlockState pState) {
        boolean placed = super.placeBlock(pContext, pState);
        if (placed && pContext.getItemInHand().hasTag()) {
            CompoundTag tag = pContext.getItemInHand().getTag();
            if (tag != null && tag.contains("Target")) {
                BlockPos pos = pContext.getClickedPos();
                BlockEntity blockEntity = pContext.getLevel().getBlockEntity(pos);
                if (blockEntity instanceof DirtGeneratorBlockEntity iBlockEntity) {
                    BlockPos target = BlockPos.of(tag.getLong("Target"));
                    iBlockEntity.setTarget(target);
                }
            }
        }
        return placed;
    }

    public static String getKey() {
        return key;
    }
}
