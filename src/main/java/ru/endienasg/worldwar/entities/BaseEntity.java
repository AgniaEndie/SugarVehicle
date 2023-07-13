package ru.endienasg.worldwar.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import org.lwjgl.Sys;
import org.slf4j.Logger;
import ru.endienasg.worldwar.registry.ItemRegistry;

import javax.annotation.Nullable;


public class BaseEntity extends Entity {

    private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float>createKey(BaseEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.<Integer>createKey(BaseEntity.class, DataSerializers.VARINT);
    private AbstractAttributeMap attributeMap;

    public BaseEntity(World worldIn) {
        super(worldIn);
        this.setSize(2F, 2F);
    }

    public BaseEntity(World worldIn, double x, double y, double z) {
        super(worldIn);
        this.setPosition(x, y, z);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
    }


    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        System.out.println("sadsa" + entityIn.getName());
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(DAMAGE_TAKEN, Float.valueOf(0.0F));
        this.dataManager.register(TIME_SINCE_HIT, Integer.valueOf(0));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    public void setDead()
    {
        this.isDead = true;
    }
    /*public void setHealth(float health)
    {
        this.dataManager.set(HEALTH, Float.valueOf(MathHelper.clamp(health, 0.0F, this.getMaxHealth())));
    }*/
    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }



    public AbstractAttributeMap getAttributeMap() {
        if (this.attributeMap == null) {
            this.attributeMap = new AttributeMap();
        }

        return this.attributeMap;
    }

    public IAttributeInstance getEntityAttribute(IAttribute attribute) {
        return this.getAttributeMap().getAttributeInstance(attribute);
    }

    public final float getMaxHealth() {
        return (float) this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
    }

    protected void playHurtSound(DamageSource source) {
        SoundEvent soundevent = this.getHurtSound(source);

        if (soundevent != null) {
            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBox(Entity entityIn) {
        return super.getCollisionBox(entityIn);
    }


    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return super.getCollisionBoundingBox();
    }

    public void applyEntityCollision(Entity entityIn) {
        if (entityIn instanceof EntityBoat) {
            if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
                super.applyEntityCollision(entityIn);
            }
        } else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY) {
            super.applyEntityCollision(entityIn);
        }
    }

    protected float getSoundPitch() {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        playHurtSound(source);
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else if (!this.world.isRemote && !this.isDead) {
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
            this.markVelocityChanged();
            boolean flag = source.getTrueSource() instanceof EntityPlayer && ((EntityPlayer) source.getTrueSource()).capabilities.isCreativeMode;
            if (flag || this.getDamageTaken() > 400.0F) {
                if (!flag && this.world.getGameRules().getBoolean("doEntityDrops")) {
                    this.dropItemWithOffset(this.getEntityItem(), 1, 0.0F);
                }
                this.setDead();
            }
            return true;
        } else {
            return true;
        }
    }

    public void setDamageTaken(float damageTaken) {
        this.dataManager.set(DAMAGE_TAKEN, Float.valueOf(damageTaken));
    }


    public void setTimeSinceHit(int timeSinceHit)
    {
        this.dataManager.set(TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
    }

    public int getTimeSinceHit()
    {
        return ((Integer)this.dataManager.get(TIME_SINCE_HIT)).intValue();
    }

    public float getDamageTaken() {
        return ((Float) this.dataManager.get(DAMAGE_TAKEN)).floatValue();
    }

    public Item getEntityItem() {
        return ItemRegistry.KillerItem;
    }

    @Override
    public void onUpdate() {
        if (this.getDamageTaken() > 0.0F) {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }
    }
}
