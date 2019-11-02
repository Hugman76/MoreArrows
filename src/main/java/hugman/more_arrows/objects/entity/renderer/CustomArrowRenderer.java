package hugman.more_arrows.objects.entity.renderer;

import hugman.more_arrows.MoreArrows;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomArrowRenderer extends ArrowRenderer<AbstractArrowEntity>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(MoreArrows.MOD_ID, "textures/entity/projectiles/tnt_arrow.png");

	public CustomArrowRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(AbstractArrowEntity entity)
	{
		ResourceLocation rl = entity.getType().getRegistryName();
		return new ResourceLocation(rl.getNamespace(), "textures/entity/projectiles/" + rl.getPath() + ".png");
	}
}