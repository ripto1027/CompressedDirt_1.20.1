package stan.ripto.compresseddirt.datagen.server.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.datagen.server.tag.ITags;

import java.util.concurrent.CompletableFuture;

public class IItemTagsProvider extends ItemTagsProvider {
    public IItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, CompressedDirt.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider pProvider) {
        copy(BlockTags.DIRT, ItemTags.DIRT);
        copy(ITags.Blocks.STORAGE_BLOCKS_DIRT, ITags.Items.STORAGE_BLOCKS_DIRT);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(ITags.Blocks.GENERATORS, ITags.Items.GENERATORS);
    }
}
