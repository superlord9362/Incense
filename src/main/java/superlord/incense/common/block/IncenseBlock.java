package superlord.incense.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.incense.init.BlockInit;

public class IncenseBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape AABB_Z = Block.box(6.0D, 0.0D, 2.0D, 10.0D, 2.0D, 14.0D);
	protected static final VoxelShape AABB_X = Block.box(2.0D, 0.0D, 6.0D, 14.0D, 2.0D, 10.0D);

	public IncenseBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		if (item == Items.FLINT_AND_STEEL && state.getValue(WATERLOGGED) != true) {
			if (!player.isCreative()) {
				stack.hurtAndBreak(1, player, (p_220009_1_) -> {
					p_220009_1_.broadcastBreakEvent(hand);
				});
			}
			level.setBlock(pos, BlockInit.LIT_INCENSE.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), 2);
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	public VoxelShape getShape(BlockState p_51104_, BlockGetter p_51105_, BlockPos p_51106_, CollisionContext p_51107_) {
		Direction direction = p_51104_.getValue(FACING);
		if (direction.getAxis() == Direction.Axis.X) {
			return AABB_X;
		} else {
			return AABB_Z;
		}
	}	

	public void neighborChanged(BlockState p_57547_, Level p_57548_, BlockPos p_57549_, Block p_57550_, BlockPos p_57551_, boolean p_57552_) {
		if (!p_57548_.isClientSide) {
			if (p_57547_.getValue(WATERLOGGED)) {
				p_57548_.scheduleTick(p_57549_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57548_));
			}
		}
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


}
