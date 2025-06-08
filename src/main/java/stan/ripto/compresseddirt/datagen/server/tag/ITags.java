package stan.ripto.compresseddirt.datagen.server.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ITags {
    public static final String FORGE = "forge";

    public static class Blocks {
        public static final TagKey<Block> STORAGE_BLOCKS_DIRT = BlockTags.create(ResourceLocation.fromNamespaceAndPath(FORGE, "storage_blocks/dirt"));
        public static final TagKey<Block> GENERATORS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(FORGE, "generators"));
    }

    public static class Items {
        public static final TagKey<Item> STORAGE_BLOCKS_DIRT = ItemTags.create(ResourceLocation.fromNamespaceAndPath(FORGE, "storage_blocks/dirt"));
        public static final TagKey<Item> GENERATORS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(FORGE, "generators"));
    }
}
