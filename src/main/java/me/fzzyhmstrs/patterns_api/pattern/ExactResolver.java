package me.fzzyhmstrs.patterns_api.pattern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

//https://github.com/VazkiiMods/Patchouli/blob/8448615e45de4484ca35921f7107f90fe8a6be1a/Xplat/src/main/java/vazkii/patchouli/common/multiblock/StringStateMatcher.java

public class ExactResolver extends AbstractResolver {

    public ExactResolver(@Nullable PatternPos nextPos, @Nullable Block block,@Nullable Identifier resolvesTo, Map<Property<?>, Comparable<?>> stateProps){
        super(nextPos,block, resolvesTo);
        this.stateProps = stateProps;
    }

    final Map<Property<?>, Comparable<?>> stateProps;


    @Override
    boolean testState(BlockState state) {
        for (Map.Entry<Property<?>, Comparable<?>> e : stateProps.entrySet()) {
            if (!state.get(e.getKey()).equals(e.getValue())) {
                return false;
            }
        }
        return true;
    }
}
