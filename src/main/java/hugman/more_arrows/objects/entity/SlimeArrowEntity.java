package hugman.more_arrows.objects.entity;

import hugman.more_arrows.init.MoreArrowsEntities;
import hugman.more_arrows.init.MoreArrowsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SlimeArrowEntity extends AbstractArrowEntity
{
	private int rebounducabilityLevel = 3;
	
	public SlimeArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public SlimeArrowEntity(LivingEntity shooter, World worldIn)
	{
		super(MoreArrowsEntities.SLIME_ARROW, shooter, worldIn);
	}
	
	public SlimeArrowEntity(double x, double y, double z, World worldIn)
	{
		super(MoreArrowsEntities.SLIME_ARROW, x, y, z, worldIn);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if (this.world.isRemote && !this.inGround)
		{
			this.world.addParticle(ParticleTypes.ITEM_SLIME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return new ItemStack(MoreArrowsItems.SLIME_ARROW);
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn)
	{
		super.onHit(raytraceResultIn);
		RayTraceResult.Type rayType = raytraceResultIn.getType();
		if(rayType == RayTraceResult.Type.BLOCK && rebounducabilityLevel > 0)
		{
			this.rebounducabilityLevel -= 1;
			this.arrowShake = 0;
			this.setMotion(this.getMotion().scale(-2.0D));
		}
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		if(compound.contains("RebounducabilityLevel"))
		{
			this.rebounducabilityLevel = compound.getInt("RebounducabilityLevel");
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putInt("RebounducabilityLevel", this.rebounducabilityLevel);
	}
	
	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}