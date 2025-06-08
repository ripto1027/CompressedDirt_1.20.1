package stan.ripto.compresseddirt.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.block.IBlocks;

public class ITab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CompressedDirt.MOD_ID);

    private static RegistryObject<CreativeModeTab> register() {
        return ITab.TABS.register(
                "compressed_dirt_tab",
                () -> CreativeModeTab
                        .builder()
                        .title(Component.translatable("creativetabs.compressed_dirt_tab"))
                        .icon(Items.DIRT::getDefaultInstance)
                        .displayItems(
                                (pParameters, pOutput) -> {
                                    pOutput.accept(IBlocks.COMPRESSED_DIRT.get());
                                    pOutput.accept(IBlocks.DOUBLE_COMPRESSED_DIRT.get());
                                    pOutput.accept(IBlocks.TRIPLE_COMPRESSED_DIRT.get());
                                    pOutput.accept(IBlocks.QUADRUPLE_COMPRESSED_DIRT.get());
                                    pOutput.accept(IBlocks.QUINTUPLE_COMPRESSED_DIRT.get());
                                    pOutput.accept(IBlocks.DIRT_GENERATOR.get());
                                }
                        )
                        .build());
    }

    public static final RegistryObject<CreativeModeTab> COMPRESSED_DIRT_TAB = register();
}
