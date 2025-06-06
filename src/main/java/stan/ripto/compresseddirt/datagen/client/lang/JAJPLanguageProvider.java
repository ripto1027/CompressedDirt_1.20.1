package stan.ripto.compresseddirt.datagen.client.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;

public class JAJPLanguageProvider extends LanguageProvider {
    public JAJPLanguageProvider(PackOutput output) {
        super(output, CompressedDirt.MOD_ID, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        addBlock(IBlocks._9X_DIRT, "9倍土");
        addBlock(IBlocks._81X_DIRT, "81倍土");
        addBlock(IBlocks._729X_DIRT, "729倍土");
        addBlock(IBlocks._6561X_DIRT, "6561倍土");
        addBlock(IBlocks._59049X_DIRT, "59049倍土");
        addBlock(IBlocks.DIRT_GENERATOR, "土生成機");
        add("creativetabs.compressed_dirt_tab", "圧縮土");
    }
}
