package net.ethansk13s.symphocraft.core.rhythm;

import net.minecraft.client.util.InputUtil.Key;

public class DirectionalNote implements BaseNote {
    private Key direction;
    private int numOfInputs;
    private static final int DEBOUNCE = 2;

    public DirectionalNote(Key direction) {
        this.direction = direction;
        this.numOfInputs = 0;
    }

    public Key getDirection() {
        return this.direction;
    }

    @Override
    public boolean hit() {
        if (numOfInputs <= DEBOUNCE) {
            this.numOfInputs = 0;
            return true;
        } else {
            this.numOfInputs++;
            return false;
        }
    }

    @Override
    public boolean miss() {
        return false;
    }
    
}
