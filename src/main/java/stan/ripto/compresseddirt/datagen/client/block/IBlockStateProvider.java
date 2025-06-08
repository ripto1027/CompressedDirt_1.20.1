package stan.ripto.compresseddirt.datagen.client.block;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;

public class IBlockStateProvider extends BlockStateProvider {
    public IBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CompressedDirt.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(IBlocks.COMPRESSED_DIRT.get());
        simpleBlockWithItem(IBlocks.DOUBLE_COMPRESSED_DIRT.get());
        simpleBlockWithItem(IBlocks.TRIPLE_COMPRESSED_DIRT.get());
        simpleBlockWithItem(IBlocks.QUADRUPLE_COMPRESSED_DIRT.get());
        simpleBlockWithItem(IBlocks.QUINTUPLE_COMPRESSED_DIRT.get());
        simpleBlockWithItem(IBlocks.DIRT_GENERATOR.get());
    }

    private void simpleBlockWithItem(Block block) {
        if (block == IBlocks.DIRT_GENERATOR.get()) {
            bottomTop(block);
        } else {
            all(block);
        }
    }

    private void bottomTop(Block block) {
        simpleBlockWithItem(block, cubeBottomTop(block));
    }

    private void all(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    private ModelFile cubeBottomTop(Block block) {
        String name = IBlocks.getName(block);
        return models().cubeBottomTop(name, getLoc(name, "side"), getLoc(name, "bottom"), getLoc(name, "top"));
    }

    private ResourceLocation getLoc(String name, String surface) {
        return modLoc("block/" + name + "_" + surface);
    }
}
