package stan.ripto.compresseddirt.datagen.server.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import stan.ripto.compresseddirt.block.IBlocks;

import java.util.function.Consumer;

public class IRecipeProvider extends RecipeProvider{
    public IRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {
        nineBlockStorageRecipes(pWriter, Blocks.DIRT, IBlocks.COMPRESSED_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks.COMPRESSED_DIRT.get(), IBlocks.DOUBLE_COMPRESSED_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks.DOUBLE_COMPRESSED_DIRT.get(), IBlocks.TRIPLE_COMPRESSED_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks.TRIPLE_COMPRESSED_DIRT.get(), IBlocks.QUADRUPLE_COMPRESSED_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks.QUADRUPLE_COMPRESSED_DIRT.get(), IBlocks.QUINTUPLE_COMPRESSED_DIRT.get());
        shaped(pWriter, IBlocks.DIRT_GENERATOR.get(), IBlocks.DOUBLE_COMPRESSED_DIRT.get());
    }

    protected void nineBlockStorageRecipes(Consumer<FinishedRecipe> pWriter, Block pUnpacked, Block pPacked) {
        Item unpack = pUnpacked.asItem();
        Item pack = pPacked.asItem();
        String unpackName = this.getName(pUnpacked);
        String packName = this.getName(pPacked);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, unpack, 9).requires(pack).group("unpack").unlockedBy(getHasName(pack), has(pack)).save(pWriter, "unpack_" + unpackName);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pack).define('#', unpack).pattern("###").pattern("###").pattern("###").group("pack").unlockedBy(getHasName(unpack), has(unpack)).save(pWriter, "pack_" + packName);
    }

    public void shaped(Consumer<FinishedRecipe> pWriter, Block result, Block material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.asItem()).define('#', material.asItem()).pattern("##").pattern("##").group("dirt_generator").unlockedBy(getHasName(material.asItem()), has(material.asItem())).save(pWriter, "dirt_generator");
    }

    private String getName(Block block) {
        String name;
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
        } else {
            name = "dirt";
        }
        return name;
    }
}
