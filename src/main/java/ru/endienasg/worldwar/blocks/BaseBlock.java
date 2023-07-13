package ru.endienasg.worldwar.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseBlock extends Block{
    public BaseBlock(String name, Material material, float hardness, float resistanse, SoundType soundType, CreativeTabs tab){
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(hardness);
        this.setResistance(resistanse);
        this.setSoundType(soundType);
        this.setCreativeTab(tab);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        System.out.println("sadasd");
        try{
            EntityPlayer player = (EntityPlayer) worldIn.getEntityByID(entityIn.getEntityId());
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 200, 1,true,true));
        }
        catch (Exception e){

        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
