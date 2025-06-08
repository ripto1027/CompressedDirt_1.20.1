package stan.ripto.compresseddirt.datagen.server.tag.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;
import stan.ripto.compresseddirt.datagen.server.tag.ITags;

import java.util.concurrent.CompletableFuture;

public class IBlockTagsProvider extends BlockTagsProvider {
    public IBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CompressedDirt.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(IBlocks.COMPRESSED_DIRT.get(), IBlocks.DOUBLE_COMPRESSED_DIRT.get(), IBlocks.TRIPLE_COMPRESSED_DIRT.get(), IBlocks.QUADRUPLE_COMPRESSED_DIRT.get(), IBlocks.QUINTUPLE_COMPRESSED_DIRT.get(), IBlocks.DIRT_GENERATOR.get());
        tag(BlockTags.DIRT).add(IBlocks.COMPRESSED_DIRT.get(), IBlocks.DOUBLE_COMPRESSED_DIRT.get(), IBlocks.TRIPLE_COMPRESSED_DIRT.get(), IBlocks.QUADRUPLE_COMPRESSED_DIRT.get(), IBlocks.QUINTUPLE_COMPRESSED_DIRT.get());
        tag(ITags.Blocks.STORAGE_BLOCKS_DIRT).add(IBlocks.COMPRESSED_DIRT.get(), IBlocks.DOUBLE_COMPRESSED_DIRT.get(), IBlocks.TRIPLE_COMPRESSED_DIRT.get(), IBlocks.QUADRUPLE_COMPRESSED_DIRT.get(), IBlocks.QUINTUPLE_COMPRESSED_DIRT.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(ITags.Blocks.STORAGE_BLOCKS_DIRT);
        tag(ITags.Blocks.GENERATORS).add(IBlocks.DIRT_GENERATOR.get());
    }
}
