package hugman.more_arrows.objects.entity;

import hugman.more_arrows.init.MoreArrowsEntities;
import hugman.more_arrows.init.MoreArrowsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class TNTArrowEntity extends AbstractArrowEntity
{	
	public TNTArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public TNTArrowEntity(LivingEntity shooter, World worldIn)
	{
		super(MoreArrowsEntities.TNT_ARROW, shooter, worldIn);
	}
	
	public TNTArrowEntity(double x, double y, double z, World worldIn)
	{
		super(MoreArrowsEntities.TNT_ARROW, x, y, z, worldIn);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if (this.world.isRemote && !this.inGround)
		{
			this.world.addParticle(ParticleTypes.SMOKE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return new ItemStack(MoreArrowsItems.TNT_ARROW);
	}
	
	@Override
	protected void arrowHit(LivingEntity living)
	{
		super.arrowHit(living);
		if(!this.world.isRemote)
		{
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 4.0F, Explosion.Mode.BREAK);
		}
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn)
	{
		super.onHit(raytraceResultIn);
		RayTraceResult.Type rayType = raytraceResultIn.getType();
		if(rayType == RayTraceResult.Type.BLOCK)
		{
			if(!this.world.isRemote)
			{
				this.world.createExplosion(this, this.posX, this.posY, this.posZ, 4.0F, Explosion.Mode.BREAK);
				this.world.setEntityState(this, (byte)3);
				this.remove();
			}
		}
	}
	
	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}