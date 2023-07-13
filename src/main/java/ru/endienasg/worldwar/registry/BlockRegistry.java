package ru.endienasg.worldwar.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.endienasg.worldwar.Main;
import ru.endienasg.worldwar.blocks.BaseBlock;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class BlockRegistry {
    public static final Block
        BarbedWire = new BaseBlock("barbed_wire", Material.WEB,5.0F,5.0F, SoundType.METAL, Main.Blocks_WW_CREATIVE_TAB);

    public static final Block[] BLOCKS = new Block[]{
            BarbedWire
    };
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(BLOCKS);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

        event.getRegistry().registerAll(getItemBlocks(BLOCKS));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {

        setRenderForAll(BLOCKS);
    }

    private static Item[] getItemBlocks(Block... blocks) {

        Item[] items = new Item[blocks.length];

        for (int i = 0; i < blocks.length; i++) {

            items[i] = new ItemBlock(blocks[i]).setRegistryName(blocks[i].getRegistryName());
        }

        return items;
    }

    @SideOnly(Side.CLIENT)
    private static void setRenderForAll(Block... blocks) {

        for (int i = 0; i < blocks.length; i++) {

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blocks[i]), 0, new ModelResourceLocation(blocks[i].getRegistryName(), "inventory"));
        }
    }
}
