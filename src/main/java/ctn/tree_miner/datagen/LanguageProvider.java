package ctn.tree_miner.datagen;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.data.PackOutput;
import xiao_jin.api.datagen.LanguageProviderAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.CreativeItemsModeTab.TREE_MINER_TAB;

/**
 * @author 尽
 * @apiNote 语言文件生成器Zh_Cn
 */
public class LanguageProvider extends LanguageProviderAPI {
    public LanguageProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    @Override
    protected void addTranslations() {
        add(TREE_MINER_TAB, "矿树节");
        add(TreeMinerBlocks.LODE_LOG.Block(), "矿脉树原木");
        add(TreeMinerBlocks.LODE_LEAVES.Block(), "矿脉树树叶");
        add(TreeMinerBlocks.LODE_PLANKS.Block(), "矿脉树木板");
        add(TreeMinerBlocks.LODE_SAPLING.Block(), "矿脉树树苗");
    }
}
