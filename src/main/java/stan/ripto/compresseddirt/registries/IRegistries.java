package stan.ripto.compresseddirt.registries;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.compresseddirt.block.IBlocks;
import stan.ripto.compresseddirt.block.dirtgenerator.DirtGeneratorBlock;
import stan.ripto.compresseddirt.item.IItems;
import stan.ripto.compresseddirt.tab.ITab;

public class IRegistries {
    public static RegistryObject<Block> registerBlock(String name) {
        RegistryObject<Block> block = IBlocks.BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
        IItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static RegistryObject<Block> registerEntityBlock(String name) {
        RegistryObject<Block> block = IBlocks.BLOCKS.register(name, () -> new DirtGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(2.0F)));
        IItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static RegistryObject<CreativeModeTab> registerTab(String name) {
        RegistryObject<CreativeModeTab> tab = ITab.TABS.register(
                name,
                () -> CreativeModeTab.builder()
                        .title(Component.translatable("creativetabs." + name))
                        .icon(Items.DIRT::getDefaultInstance)
                        .displayItems(
                                (pParameters, pOutput) -> {
                                    pOutput.accept(IBlocks._9X_DIRT.get());
                                    pOutput.accept(IBlocks._81X_DIRT.get());
                                    pOutput.accept(IBlocks._729X_DIRT.get());
                                    pOutput.accept(IBlocks._6561X_DIRT.get());
                                    pOutput.accept(IBlocks._59049X_DIRT.get());
                                    pOutput.accept(IBlocks.DIRT_GENERATOR.get());
                                }
                        )
                        .build()
                );
        return tab;
    }
}
