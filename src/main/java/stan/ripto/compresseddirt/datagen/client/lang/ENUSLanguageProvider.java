package stan.ripto.compresseddirt.datagen.client.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;
import stan.ripto.compresseddirt.block.dirtgenerator.DirtGeneratorBlockItem;

public class ENUSLanguageProvider extends LanguageProvider {
    public ENUSLanguageProvider(PackOutput output) {
        super(output, CompressedDirt.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(IBlocks.COMPRESSED_DIRT, "Compressed Dirt");
        addBlock(IBlocks.DOUBLE_COMPRESSED_DIRT, "Double Compressed Dirt");
        addBlock(IBlocks.TRIPLE_COMPRESSED_DIRT, "Triple Compressed Dirt");
        addBlock(IBlocks.QUADRUPLE_COMPRESSED_DIRT, "Quadruple Compressed Dirt");
        addBlock(IBlocks.QUINTUPLE_COMPRESSED_DIRT, "Quintuple Compressed Dirt");
        addBlock(IBlocks.SEXTUPLE_COMPRESSED_DIRT, "Sextuple Compressed Dirt");
        addBlock(IBlocks.DIRT_GENERATOR, "Dirt Generator");
        add("creativetabs.compressed_dirt_tab", "Compressed Dirt");
        add(DirtGeneratorBlockItem.getLinkedKey(), "Linked to %1$s (%2$d, %3$d, %4$d).");
        add(DirtGeneratorBlockItem.getUnlinkedKey(), "The link has been resolved.");
    }
}
