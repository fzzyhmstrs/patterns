package me.fzzyhmstrs.patterns_api.pattern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractResolver {

    public AbstractResolver(@Nullable PatternPos nextPos, @Nullable Block block, @Nullable Identifier resolvesTo){
        this.nextPos = nextPos;
        this.block = block;
        this.resolvesTo = resolvesTo;
    }

    @Nullable
    final PatternPos nextPos;
    @Nullable
    final Block block;
    @Nullable
    final Identifier resolvesTo;


    abstract boolean testState(BlockState state);

    List<Identifier> resolve(World world, BlockPos refPos){
        if (!testState(world.getBlockState(refPos))){
            return List.of();
        }
        if (resolvesTo != null){
            return List.of(resolvesTo);
        }
        if (nextPos == null || block == null){
            return List.of();
        }
        List<Identifier> list = new LinkedList<>();
        List<AbstractResolver> nextResolvers = PatternData.getResolvers(nextPos,block);
        for (AbstractResolver resolver: nextResolvers){
            list.addAll(resolver.resolve(world,nextPos.addToBlockPos(refPos)));
        }
        return list;
    }

}
