package superlord.incense.common.block;

import java.util.Queue;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.incense.common.entity.block.LitIncenseBlockEntity;
import superlord.incense.init.BlockEntityInit;
import superlord.incense.init.BlockInit;

public class LitIncenseBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape AABB_Z = Block.box(6.0D, 0.0D, 2.0D, 10.0D, 2.0D, 14.0D);
	protected static final VoxelShape AABB_X = Block.box(2.0D, 0.0D, 6.0D, 14.0D, 2.0D, 10.0D);

	public LitIncenseBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState p_57568_) {
		return p_57568_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_57568_);
	}

	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState p_57554_, Direction p_57555_, BlockState p_57556_, LevelAccessor p_57557_, BlockPos p_57558_, BlockPos p_57559_) {
		if (p_57554_.getValue(WATERLOGGED)) {
			p_57557_.scheduleTick(p_57558_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57557_));
		}
		return super.updateShape(p_57554_, p_57555_, p_57556_, p_57557_, p_57558_, p_57559_);
	}

	@SuppressWarnings("deprecation")
	public void neighborChanged(BlockState p_56801_, Level p_56802_, BlockPos p_56803_, Block p_56804_, BlockPos p_56805_, boolean p_56806_) {
		this.putOut(p_56802_, p_56803_);
		super.neighborChanged(p_56801_, p_56802_, p_56803_, p_56804_, p_56805_, p_56806_);
	}

	protected void putOut(Level p_56798_, BlockPos p_56799_) {
		BlockState state = p_56798_.getBlockState(p_56799_);
		if (this.removeWaterBreadthFirstSearch(p_56798_, p_56799_)) {
			p_56798_.setBlock(p_56799_, BlockInit.INCENSE.get().defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 2);
		}
	}

	private boolean removeWaterBreadthFirstSearch(Level p_56808_, BlockPos p_56809_) {
		Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
		queue.add(new Tuple<>(p_56809_, 0));
		int i = 0;

		while(!queue.isEmpty()) {
			Tuple<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockpos = tuple.getA();
			int j = tuple.getB();

			for(Direction direction : Direction.values()) {
				BlockPos blockpos1 = blockpos.relative(direction);
				BlockState blockstate = p_56808_.getBlockState(blockpos1);
				FluidState fluidstate = p_56808_.getFluidState(blockpos1);
				if (fluidstate.is(FluidTags.WATER)) {
					++i;
					if (j < 6) {
						queue.add(new Tuple<>(blockpos1, j + 1));
					}
				} else if (blockstate.getBlock() instanceof LiquidBlock) {
					++i;
					if (j < 6) {
						queue.add(new Tuple<>(blockpos1, j + 1));
					}
				}
			}
			if (i > 64) {
				break;
			}
		}

		return i > 0;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_152164_, BlockState p_152165_) {
		return new LitIncenseBlockEntity(p_152164_, p_152165_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152160_, BlockState p_152161_, BlockEntityType<T> p_152162_) {
		return createTickerHelper(p_152162_, BlockEntityInit.LIT_INCENSE.get(), LitIncenseBlockEntity::tick);
	}

	public void animateTick(BlockState p_57494_, Level p_57495_, BlockPos p_57496_, RandomSource p_57497_) {
		Direction direction = p_57494_.getValue(FACING);
		if (direction == Direction.NORTH) {
			double d0 = (double)p_57496_.getX() + 0.5D;
			double d1 = (double)p_57496_.getY() + 0.6D;
			double d2 = (double)p_57496_.getZ() + 0.35D;
			p_57495_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);	
		}
		if (direction == Direction.SOUTH) {
			double d0 = (double)p_57496_.getX() + 0.5D;
			double d1 = (double)p_57496_.getY() + 0.6D;
			double d2 = (double)p_57496_.getZ() + 0.65D;
			p_57495_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);	
		}
		if (direction == Direction.EAST) {
			double d0 = (double)p_57496_.getX() + 0.65D;
			double d1 = (double)p_57496_.getY() + 0.6D;
			double d2 = (double)p_57496_.getZ() + 0.5D;
			p_57495_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);	
		}
		if (direction == Direction.WEST) {
			double d0 = (double)p_57496_.getX() + 0.35D;
			double d1 = (double)p_57496_.getY() + 0.6D;
			double d2 = (double)p_57496_.getZ() + 0.5D;
			p_57495_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);	
		}
	}

	public VoxelShape getShape(BlockState p_51104_, BlockGetter p_51105_, BlockPos p_51106_, CollisionContext p_51107_) {
		Direction direction = p_51104_.getValue(FACING);
		if (direction.getAxis() == Direction.Axis.X) {
			return AABB_X;
		} else {
			return AABB_Z;
		}
	}	

	public RenderShape getRenderShape(BlockState p_49439_) {
		return RenderShape.MODEL;
	}

}
