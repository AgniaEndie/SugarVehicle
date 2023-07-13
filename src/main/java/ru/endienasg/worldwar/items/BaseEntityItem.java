package ru.endienasg.worldwar.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import org.lwjgl.Sys;
import ru.endienasg.worldwar.entities.BaseEntity;

public class BaseEntityItem extends BaseItem{
    public BaseEntityItem(String name, CreativeTabs tab) {
        super(name, tab);
    }



    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        BaseEntity e = new BaseEntity(worldIn, playerIn.posX,playerIn.posY,playerIn.posZ);
        if(!worldIn.isRemote){
            worldIn.spawnEntity(e);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS,ItemStack.EMPTY);
    }


}
