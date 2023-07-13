package ru.endienasg.worldwar.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseItem extends Item {
    public BaseItem(String name, CreativeTabs tab){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
