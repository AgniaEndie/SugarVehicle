package ru.endienasg.worldwar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import ru.endienasg.worldwar.proxy.CommonProxy;
import ru.endienasg.worldwar.registry.BlockRegistry;
import ru.endienasg.worldwar.registry.ItemRegistry;
import ru.endienasg.worldwar.registry.RegisterEntity;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    @Mod.Instance(Main.MODID)
    public static Main instance;

    public static final String MODID = "worldwar";
    public static final String NAME = "World War Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @SidedProxy(clientSide = "ru.endienasg.worldwar.proxy.ClientProxy", serverSide = "ru.endienasg.worldwar.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        RegisterEntity.registerEntites();
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

    public static final CreativeTabs
        Blocks_WW_CREATIVE_TAB = new CreativeTabs("Blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(BlockRegistry.BarbedWire);
        }
    },
        ITEMS_WW_CREATIVE_TAB = new CreativeTabs("Items") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemRegistry.SturmShovel);
            }
        };



}
