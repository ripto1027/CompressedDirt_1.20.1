package stan.ripto.compresseddirt.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.registries.IRegistries;

public class IBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CompressedDirt.MOD_ID);

    public static final String _9X_DIRT_NAME = "9x_dirt";
    public static final String _81X_DIRT_NAME = "81x_dirt";
    public static final String _729X_DIRT_NAME = "729x_dirt";
    public static final String _6561X_DIRT_NAME = "6561x_dirt";
    public static final String _59049X_DIRT_NAME = "59049x_dirt";

    public static final String DIRT_GENERATOR_NAME = "dirt_generator";

    public static final RegistryObject<Block> _9X_DIRT = IRegistries.registerBlock(_9X_DIRT_NAME);
    public static final RegistryObject<Block> _81X_DIRT = IRegistries.registerBlock(_81X_DIRT_NAME);
    public static final RegistryObject<Block> _729X_DIRT = IRegistries.registerBlock(_729X_DIRT_NAME);
    public static final RegistryObject<Block> _6561X_DIRT = IRegistries.registerBlock(_6561X_DIRT_NAME);
    public static final RegistryObject<Block> _59049X_DIRT = IRegistries.registerBlock(_59049X_DIRT_NAME);

    public static final RegistryObject<Block> DIRT_GENERATOR = IRegistries.registerEntityBlock(DIRT_GENERATOR_NAME);

    public static String getName(Block block) {
        String name = "";
        if (block == IBlocks._9X_DIRT.get()) {
            name = IBlocks._9X_DIRT_NAME;
        } else if (block == IBlocks._81X_DIRT.get()) {
            name = IBlocks._81X_DIRT_NAME;
        } else if (block == IBlocks._729X_DIRT.get()) {
            name = IBlocks._729X_DIRT_NAME;
        } else if (block == IBlocks._6561X_DIRT.get()) {
            name = IBlocks._6561X_DIRT_NAME;
        } else if (block == IBlocks._59049X_DIRT.get()) {
            name = IBlocks._59049X_DIRT_NAME;
        } else if (block == IBlocks.DIRT_GENERATOR.get()) {
            name = IBlocks.DIRT_GENERATOR_NAME;
        }
        return name;
    }
}
