package hugman.more_arrows.objects.item;

import hugman.more_arrows.objects.entity.TNTArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TNTArrowItem extends ArrowItem
{	
	public TNTArrowItem(Item.Properties builder)
	{
		super(builder);
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
	{
		return new TNTArrowEntity(shooter, worldIn);
	}
}