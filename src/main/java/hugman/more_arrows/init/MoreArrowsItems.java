package hugman.more_arrows.init;

import java.util.ArrayList;
import java.util.List;

import hugman.more_arrows.MoreArrows;
import hugman.more_arrows.objects.item.SlimeArrowItem;
import hugman.more_arrows.objects.item.TNTArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class MoreArrowsItems
{
	/* All Content Bag */
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item TNT_ARROW = register("tnt_arrow", new TNTArrowItem(new Item.Properties().group(ItemGroup.COMBAT)));
    public static final Item SLIME_ARROW = register("slime_arrow", new SlimeArrowItem(new Item.Properties().group(ItemGroup.COMBAT)));
    
    private static Item register(String name, Item item)
    {
    	Item fItem = item.setRegistryName(MoreArrows.MOD_ID, name);
        ITEMS.add(fItem);
		return fItem;
    }
}