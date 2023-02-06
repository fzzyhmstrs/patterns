package me.fzzyhmstrs.patterns_api.pattern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

//https://github.com/VazkiiMods/Patchouli/blob/8448615e45de4484ca35921f7107f90fe8a6be1a/Xplat/src/main/java/vazkii/patchouli/common/multiblock/StringStateMatcher.java

public class TagResolver extends AbstractResolver {

    public TagResolver(@Nullable PatternPos nextPos, @Nullable Block block, @Nullable Identifier resolvesTo, Map<String,String> tagProps){
        super(nextPos,block, resolvesTo);
        this.tagProps = tagProps;
    }

    final Map<String,String> tagProps;


    @Override
    boolean testState(BlockState state) {
        for (Map.Entry<String, String> entry : tagProps.entrySet()) {
            Property<?> prop = state.getBlock().getStateManager().getProperty(entry.getKey());
            if (prop == null) {
                return false;
            }

            Comparable<?> value = prop.parse(entry.getValue()).orElse(null);
            if (value == null) {
                return false;
            }

            if (!state.get(prop).equals(value)) {
                return false;
            }
        }
        return true;
    }
}
