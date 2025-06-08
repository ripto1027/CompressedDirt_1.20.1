package stan.ripto.compresseddirt;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import stan.ripto.compresseddirt.block.IBlockEntity;
import stan.ripto.compresseddirt.block.IBlocks;
import stan.ripto.compresseddirt.item.IItems;
import stan.ripto.compresseddirt.tab.ITab;

@Mod(CompressedDirt.MOD_ID)
public class CompressedDirt {
    public static final String MOD_ID = "compresseddirt";

    public CompressedDirt(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        IItems.ITEMS.register(bus);
        IBlocks.BLOCKS.register(bus);
        ITab.TABS.register(bus);
        IBlockEntity.BLOCK_ENTITY.register(bus);
    }
    //コンテナのスロットの位置は34, 34
}
