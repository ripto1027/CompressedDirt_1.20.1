package stan.ripto.compresseddirt.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stan.ripto.compresseddirt.CompressedDirt;
import stan.ripto.compresseddirt.datagen.client.block.IBlockStateProvider;
import stan.ripto.compresseddirt.datagen.client.lang.ENUSLanguageProvider;
import stan.ripto.compresseddirt.datagen.client.lang.JAJPLanguageProvider;
import stan.ripto.compresseddirt.datagen.server.recipe.IRecipeProvider;
import stan.ripto.compresseddirt.datagen.server.loot.ILootTables;
import stan.ripto.compresseddirt.datagen.server.tag.IBlockTagProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CompressedDirt.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IDataGenerator {
    @SubscribeEvent
    public static void gatherData (GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new IBlockStateProvider(output, efh));
        generator.addProvider(event.includeClient(), new ENUSLanguageProvider(output));
        generator.addProvider(event.includeClient(), new JAJPLanguageProvider(output));

        generator.addProvider(event.includeServer(), new IRecipeProvider(output));
        generator.addProvider(event.includeServer(), ILootTables.create(output));
        generator.addProvider(event.includeServer(), new IBlockTagProvider(output, lookupProvider, efh));
    }
}
