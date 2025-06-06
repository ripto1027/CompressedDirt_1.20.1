package stan.ripto.compresseddirt.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.dirtgenerator.DirtGeneratorBlockEntity;

public class IBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CompressedDirt.MOD_ID);

    public static final RegistryObject<BlockEntityType<DirtGeneratorBlockEntity>> DIRT_GENERATOR = BLOCK_ENTITY.register(
            "dirt_generator",
            () -> BlockEntityType
                    .Builder.of(
                            DirtGeneratorBlockEntity::new,
                            IBlocks.DIRT_GENERATOR.get()
                    )
                    .build(null)
    );
}
