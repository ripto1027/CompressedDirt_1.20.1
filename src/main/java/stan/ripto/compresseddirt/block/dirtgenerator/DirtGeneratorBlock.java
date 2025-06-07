package stan.ripto.compresseddirt.block.dirtgenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.compresseddirt.block.IBlockEntity;

public class DirtGeneratorBlock extends Block implements EntityBlock {
    public DirtGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new DirtGeneratorBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        if (type == IBlockEntity.DIRT_GENERATOR.get()) {
            return (lvl, pos, blockState, be) -> {
                if (be instanceof DirtGeneratorBlockEntity entity) {
                    entity.tick(lvl, pos, blockState);
                }
            };
        }
        return null;
    }
}
