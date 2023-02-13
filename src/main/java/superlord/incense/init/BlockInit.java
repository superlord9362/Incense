package superlord.incense.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.incense.Incense;
import superlord.incense.common.block.IncenseBlock;
import superlord.incense.common.block.LitIncenseBlock;

public class BlockInit {
	
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Incense.MOD_ID);

	public static final RegistryObject<Block> INCENSE = REGISTER.register("incense", () -> new IncenseBlock(Properties.of(Material.WOOD).strength(1.0F, 1.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<Block> LIT_INCENSE = REGISTER.register("lit_incense", () -> new LitIncenseBlock(Properties.of(Material.WOOD).strength(1.0F, 1.5F).sound(SoundType.WOOD).noOcclusion()));

}
