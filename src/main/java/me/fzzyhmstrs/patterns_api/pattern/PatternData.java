package me.fzzyhmstrs.patterns_api.pattern;

import com.google.common.collect.ArrayListMultimap;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PatternData {

    private static final HashMap<PatternPos, ArrayListMultimap<Block, AbstractResolver>> patternData = new HashMap<>();
    private static final PatternPos ORIGIN = new PatternPos(0,0,0);

    public static List<AbstractResolver> getResolvers(PatternPos pos, Block block){
        if (!patternData.containsKey(pos)){
            return new ArrayList<>();
        } else {
            ArrayListMultimap<Block, AbstractResolver> blockResolvers = patternData.get(pos);
            return blockResolvers.get(block);
        }
    }

    public static List<Identifier> resolvePatterns(World world, BlockPos startingPos){
        List<Identifier> list = new LinkedList<>();
        List<AbstractResolver> resolvers = getResolvers(ORIGIN,world.getBlockState(startingPos).getBlock());
        for (AbstractResolver resolver : resolvers){
            list.addAll(resolver.resolve(world, startingPos));
        }
        return list;
    }
}
