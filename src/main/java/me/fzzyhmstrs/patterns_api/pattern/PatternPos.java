package me.fzzyhmstrs.patterns_api.pattern;

import net.minecraft.util.math.BlockPos;

public class PatternPos {

    public PatternPos(int x,int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.hashCode = x + 101 * y + 10007 * z;
    }

    final int x;
    final int y;
    final int z;
    final int hashCode;

    BlockPos addToBlockPos(BlockPos ref){
        return ref.add(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatternPos that = (PatternPos) o;
        return hashCode == that.hashCode;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
