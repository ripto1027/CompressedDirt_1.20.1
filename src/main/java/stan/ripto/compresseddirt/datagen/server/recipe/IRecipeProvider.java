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
        nineBlockStorageRecipes(pWriter, Blocks.DIRT, IBlocks._9X_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks._9X_DIRT.get(), IBlocks._81X_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks._81X_DIRT.get(), IBlocks._729X_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks._729X_DIRT.get(), IBlocks._6561X_DIRT.get());
        nineBlockStorageRecipes(pWriter, IBlocks._6561X_DIRT.get(), IBlocks._59049X_DIRT.get());
        shaped(pWriter, IBlocks.DIRT_GENERATOR.get(), IBlocks._81X_DIRT.get());
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
        } else {
            name = "dirt";
        }
        return name;
    }
}
