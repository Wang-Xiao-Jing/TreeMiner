package ctn.tree_miner.datagen;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;

import java.util.Optional;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class TreeMinerModels extends TexturedModel {
    public static final ModelTemplate CROSS = createBlockModel("cross", TextureSlot.CROSS);

    public TreeMinerModels(TextureMapping mapping, ModelTemplate template) {
        super(mapping, template);
    }

    public static ModelTemplate createBlockModel(String name, TextureSlot... requiredSlots) {
        return new ModelTemplate(Optional.of(fromNamespaceAndPath(MOD_ID, "block/"+name)), Optional.empty(), requiredSlots);
    }

}
