package ru.endienasg.worldwar.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import ru.endienasg.worldwar.Main;
import ru.endienasg.worldwar.entities.BaseEntity;

import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;
import static net.minecraftforge.registries.GameData.registerEntity;
public class RegisterEntity {
    /*public static void registerEntities(String name, Class<? extends Entity> entity,){
        registerEntity(250, new ResourceLocation(Main.MODID + ":" + name),entity,name);
        //registerModEntity()
    }*/
    private static int BASE_MOB_ID = 0;/*
    public static EntityEntry BASE_MOB = EntityEntryBuilder.create().entity(BaseEntity.class).name("Base_Entity").id("base_entity",BASE_MOB_ID++).build();

    @SubscribeEvent
    public static void registryEntity(RegistryEvent.Register<EntityEntry> event){
        event.getRegistry().registerAll(
                BASE_MOB
        );
    }*/

    public static void registerEntites(){
        registerEntity("base_entity", BaseEntity.class, BASE_MOB_ID, 150, 213123, 312312313);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity , int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }

}
