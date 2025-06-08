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
        addBlock(IBlocks.COMPRESSED_DIRT, "圧縮土");
        addBlock(IBlocks.DOUBLE_COMPRESSED_DIRT, "2倍圧縮土");
        addBlock(IBlocks.TRIPLE_COMPRESSED_DIRT, "3倍圧縮土");
        addBlock(IBlocks.QUADRUPLE_COMPRESSED_DIRT, "4倍圧縮土");
        addBlock(IBlocks.QUINTUPLE_COMPRESSED_DIRT, "5倍圧縮土");
        addBlock(IBlocks.DIRT_GENERATOR, "土生成機");
        add("creativetabs.compressed_dirt_tab", "圧縮土");
    }
}
