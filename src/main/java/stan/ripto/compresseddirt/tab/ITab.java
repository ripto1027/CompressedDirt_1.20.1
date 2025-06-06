package stan.ripto.compresseddirt.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.registries.IRegistries;

public class ITab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CompressedDirt.MOD_ID);
    public static final RegistryObject<CreativeModeTab> COMPRESSED_DIRT_TAB = IRegistries.registerTab("compressed_dirt_tab");
}
