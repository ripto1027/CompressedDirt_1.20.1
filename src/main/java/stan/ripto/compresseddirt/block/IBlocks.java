package stan.ripto.compresseddirt.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.dirtgenerator.DirtGeneratorBlock;
import stan.ripto.compresseddirt.block.dirtgenerator.DirtGeneratorBlockItem;
import stan.ripto.compresseddirt.item.IItems;

import java.util.function.Supplier;

public class IBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CompressedDirt.MOD_ID);

    public static final String COMPRESSED_DIRT_NAME = "compressed_dirt";
    public static final String DOUBLE_COMPRESSED_DIRT_NAME = "double_compressed_dirt";
    public static final String TRIPLE_COMPRESSED_DIRT_NAME = "triple_compressed_dirt";
    public static final String QUADRUPLE_COMPRESSED_DIRT_NAME = "quadruple_compressed_dirt";
    public static final String QUINTUPLE_COMPRESSED_DIRT_NAME = "quintuple_compressed_dirt";
    public static final String SEXTUPLE_COMPRESSED_DIRT_NAME = "sextuple_compressed_dirt";
    public static final String DIRT_GENERATOR_NAME = "dirt_generator";

    private static RegistryObject<Block> register(String name, Supplier<Block> bSup, Supplier<Item> iSup) {
        RegistryObject<Block> reg = BLOCKS.register(name, bSup);
        IItems.ITEMS.register(name, iSup);
        return reg;
    }

    private static RegistryObject<Block> register(String name) {
        RegistryObject<Block> reg = BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
        IItems.ITEMS.register(name, () -> new BlockItem(reg.get(), new Item.Properties()));
        return reg;
    }

    public static final RegistryObject<Block> COMPRESSED_DIRT = register(COMPRESSED_DIRT_NAME);
    public static final RegistryObject<Block> DOUBLE_COMPRESSED_DIRT = register(DOUBLE_COMPRESSED_DIRT_NAME);
    public static final RegistryObject<Block> TRIPLE_COMPRESSED_DIRT = register(TRIPLE_COMPRESSED_DIRT_NAME);
    public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_DIRT = register(QUADRUPLE_COMPRESSED_DIRT_NAME);
    public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_DIRT = register(QUINTUPLE_COMPRESSED_DIRT_NAME);
    public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_DIRT = register(SEXTUPLE_COMPRESSED_DIRT_NAME);

    public static final RegistryObject<Block> DIRT_GENERATOR = register(
            DIRT_GENERATOR_NAME,
            () -> new DirtGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.STONE)),
            () -> new DirtGeneratorBlockItem(new Item.Properties().stacksTo(1))
    );

    public static String getName(Block block) {
        String name = "";
        if (block == IBlocks.COMPRESSED_DIRT.get()) {
            name = IBlocks.COMPRESSED_DIRT_NAME;
        } else if (block == IBlocks.DOUBLE_COMPRESSED_DIRT.get()) {
            name = IBlocks.DOUBLE_COMPRESSED_DIRT_NAME;
        } else if (block == IBlocks.TRIPLE_COMPRESSED_DIRT.get()) {
            name = IBlocks.TRIPLE_COMPRESSED_DIRT_NAME;
        } else if (block == IBlocks.QUADRUPLE_COMPRESSED_DIRT.get()) {
            name = IBlocks.QUADRUPLE_COMPRESSED_DIRT_NAME;
        } else if (block == IBlocks.QUINTUPLE_COMPRESSED_DIRT.get()) {
            name = IBlocks.QUINTUPLE_COMPRESSED_DIRT_NAME;
        } else if (block == IBlocks.SEXTUPLE_COMPRESSED_DIRT.get()) {
            name = IBlocks.SEXTUPLE_COMPRESSED_DIRT_NAME;
        } else if (block == IBlocks.DIRT_GENERATOR.get()) {
            name = IBlocks.DIRT_GENERATOR_NAME;
        }
        return name;
    }
}
