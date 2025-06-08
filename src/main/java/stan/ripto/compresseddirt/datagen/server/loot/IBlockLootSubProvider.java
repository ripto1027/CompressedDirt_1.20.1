package stan.ripto.compresseddirt.datagen.server.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import stan.ripto.compresseddirt.block.IBlocks;

import java.util.Set;

public class IBlockLootSubProvider extends BlockLootSubProvider {
    protected IBlockLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(IBlocks.COMPRESSED_DIRT.get());
        dropSelf(IBlocks.DOUBLE_COMPRESSED_DIRT.get());
        dropSelf(IBlocks.TRIPLE_COMPRESSED_DIRT.get());
        dropSelf(IBlocks.QUADRUPLE_COMPRESSED_DIRT.get());
        dropSelf(IBlocks.QUINTUPLE_COMPRESSED_DIRT.get());
        dropSelf(IBlocks.DIRT_GENERATOR.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return IBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
