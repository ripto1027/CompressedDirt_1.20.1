package stan.ripto.compresseddirt.datagen.server.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;

import java.util.concurrent.CompletableFuture;

public class IBlockTagProvider extends BlockTagsProvider {
    public IBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CompressedDirt.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(IBlocks._9X_DIRT.get(), IBlocks._81X_DIRT.get(), IBlocks._729X_DIRT.get(), IBlocks._6561X_DIRT.get(), IBlocks._59049X_DIRT.get(), IBlocks.DIRT_GENERATOR.get());
        this.tag(BlockTags.DIRT).add(IBlocks._9X_DIRT.get(), IBlocks._81X_DIRT.get(), IBlocks._729X_DIRT.get(), IBlocks._6561X_DIRT.get(), IBlocks._59049X_DIRT.get());
    }
}
