package hugman.more_arrows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hugman.more_arrows.init.MoreArrowsEntities;
import hugman.more_arrows.init.MoreArrowsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(MoreArrows.MOD_ID)
public class MoreArrows
{
	public static final String MOD_ID = "more_arrows";
	public static final String MOD_PREFIX = MOD_ID + ":";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	public MoreArrows()
	{
		MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
	}
    
    private void clientSetup(final FMLClientSetupEvent event)
    {
    	MoreArrowsEntities.registerRenders();
    	LOGGER.info("Registered entities renders");
    }
	
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModRegistryEvents
    {
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
        {
            event.getRegistry().registerAll(MoreArrowsItems.ITEMS.toArray(new Item[0]));
        	LOGGER.info("Registered " + MoreArrowsItems.ITEMS.size() + " items");
        }
        
        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event)
        {
        	IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        	MoreArrowsEntities.registerEntities(registry);
        	LOGGER.info("Registered entities");
        }
    }
}