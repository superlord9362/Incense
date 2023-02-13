package superlord.incense.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.incense.Incense;

public class ItemInit {
	
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Incense.MOD_ID);

	public static final RegistryObject<BlockItem> INCENSE = REGISTER.register("incense", () -> new BlockItem(BlockInit.INCENSE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

}
