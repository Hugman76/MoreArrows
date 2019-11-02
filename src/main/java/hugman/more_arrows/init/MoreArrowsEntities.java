package hugman.more_arrows.init;

import hugman.more_arrows.MoreArrows;
import hugman.more_arrows.objects.entity.SlimeArrowEntity;
import hugman.more_arrows.objects.entity.TNTArrowEntity;
import hugman.more_arrows.objects.entity.renderer.CustomArrowRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class MoreArrowsEntities
{
    public static final EntityType<TNTArrowEntity> TNT_ARROW = register("tnt_arrow", EntityType.Builder.<TNTArrowEntity>create(TNTArrowEntity::new, EntityClassification.MISC).size(0.98F, 0.98F).setTrackingRange(128).setUpdateInterval(1));
    public static final EntityType<SlimeArrowEntity> SLIME_ARROW = register("slime_arrow", EntityType.Builder.<SlimeArrowEntity>create(SlimeArrowEntity::new, EntityClassification.MISC).size(0.98F, 0.98F).setTrackingRange(128).setUpdateInterval(1));
    
	private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder)
	{
	    EntityType<T> entitytype = builder.build(MoreArrows.MOD_PREFIX + id);
	    entitytype.setRegistryName(MoreArrows.MOD_ID, id);
		return entitytype;
	}
	
    public static void registerEntities(IForgeRegistry<EntityType<?>> registry)
    {
    	registry.register(TNT_ARROW);
    	registry.register(SLIME_ARROW);
    }
    
    public static void registerRenders()
    {
    	RenderingRegistry.registerEntityRenderingHandler(TNTArrowEntity.class, CustomArrowRenderer::new);
    	RenderingRegistry.registerEntityRenderingHandler(SlimeArrowEntity.class, CustomArrowRenderer::new);
    }
}