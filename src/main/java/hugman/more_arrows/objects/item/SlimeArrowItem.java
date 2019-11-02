package hugman.more_arrows.objects.item;

import hugman.more_arrows.objects.entity.SlimeArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SlimeArrowItem extends ArrowItem
{	
	public SlimeArrowItem(Item.Properties builder)
	{
		super(builder);
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
	{
		return new SlimeArrowEntity(shooter, worldIn);
	}
}