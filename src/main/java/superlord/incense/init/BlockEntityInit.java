package superlord.incense.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.incense.Incense;
import superlord.incense.common.entity.block.LitIncenseBlockEntity;

public class BlockEntityInit {
	
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Incense.MOD_ID);

	public static final RegistryObject<BlockEntityType<LitIncenseBlockEntity>> LIT_INCENSE = REGISTER.register("lit_incense", () -> BlockEntityType.Builder.of(LitIncenseBlockEntity::new,
    		BlockInit.LIT_INCENSE.get()
    	).build(null));
	
}
