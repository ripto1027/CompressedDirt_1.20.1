package stan.ripto.compresseddirt.datagen.client.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;

public class ENUSLanguageProvider extends LanguageProvider {
    public ENUSLanguageProvider(PackOutput output) {
        super(output, CompressedDirt.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(IBlocks._9X_DIRT, "9x Dirt");
        addBlock(IBlocks._81X_DIRT, "81x Dirt");
        addBlock(IBlocks._729X_DIRT, "729x Dirt");
        addBlock(IBlocks._6561X_DIRT, "6561x Dirt");
        addBlock(IBlocks._59049X_DIRT, "59049x Dirt");
        addBlock(IBlocks.DIRT_GENERATOR, "Dirt Generator");
        add("creativetabs.compressed_dirt_tab", "Compressed Dirt");
    }
}
