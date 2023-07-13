package ru.endienasg.worldwar.registry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.endienasg.worldwar.Main;
import ru.endienasg.worldwar.items.BaseEntityItem;
import ru.endienasg.worldwar.items.BaseItem;

import java.rmi.registry.Registry;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ItemRegistry {
    public static final Item
        SturmShovel = new BaseItem("sturmshovel", Main.ITEMS_WW_CREATIVE_TAB),
        KillerItem = new BaseEntityItem("killeritem",  Main.ITEMS_WW_CREATIVE_TAB);

    public static final Item[] ITEMS = new Item[]{
            SturmShovel,
            KillerItem
    };

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ITEMS);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        setRenderForAll(ITEMS);
    }

    @SideOnly(Side.CLIENT)
    private static void setRenderForAll(Item... items){
        for(int i = 0; i < items.length; i++){
            ModelLoader.setCustomModelResourceLocation(items[i],0,new ModelResourceLocation(items[i].getRegistryName(),"inventory"));
        }
    }
}

