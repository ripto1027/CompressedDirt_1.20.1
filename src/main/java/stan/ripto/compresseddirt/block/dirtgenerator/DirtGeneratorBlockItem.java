package stan.ripto.compresseddirt.block.dirtgenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;
import stan.ripto.compresseddirt.block.IBlocks;

public class DirtGeneratorBlockItem extends BlockItem {
    private static final String linkedKey = "message.compresseddirt.linked";
    private static final String unlinkedKey = "message.compresseddirt.unlinked";

    public DirtGeneratorBlockItem(Properties pProperties) {
        super(IBlocks.DIRT_GENERATOR.get(), pProperties);
    }

    @NotNull
    @Override
    public InteractionResult useOn(@NotNull UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        BlockEntity be = level.getBlockEntity(pos);

        if (be != null && player != null && player.isShiftKeyDown() && be.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent()) {
            CompoundTag tag = pContext.getItemInHand().getOrCreateTag();
            tag.putLong("Target", pos.asLong());
            if (level.isClientSide()) {
                String name = be.getBlockState().getBlock().getName().getString();
                pContext.getPlayer().displayClientMessage(Component.translatable(linkedKey, name, pos.getX(), pos.getY(), pos.getZ()), true);
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.useOn(pContext);
        }
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = stack.getTag();
        if (pPlayer.isShiftKeyDown() && tag != null && tag.contains("Target")) {
            tag.remove("Target");
            if (pLevel.isClientSide()) {
                pPlayer.displayClientMessage(Component.translatable(unlinkedKey), true);
            }
            return InteractionResultHolder.success(stack);
        } else {
            return InteractionResultHolder.fail(stack);
        }
    }

    @Override
    protected boolean placeBlock(@NotNull BlockPlaceContext pContext, @NotNull BlockState pState) {
        boolean placed = super.placeBlock(pContext, pState);
        CompoundTag tag = pContext.getItemInHand().getTag();
        if (placed && tag != null && tag.contains("Target")) {
            BlockPos pos = pContext.getClickedPos();
            BlockEntity be = pContext.getLevel().getBlockEntity(pos);
            if (be instanceof DirtGeneratorBlockEntity ibe) {
                BlockPos target = BlockPos.of(tag.getLong("Target"));
                ibe.setTarget(target);
            }
        }
        return placed;
    }

    public static String getLinkedKey() {
        return linkedKey;
    }

    public static String getUnlinkedKey() {
        return unlinkedKey;
    }
}
