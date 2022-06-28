package net.ethansk13s.symphocraft.core.rhythm;

import java.util.Queue;

public abstract class BaseCombo {
    public abstract void doAction();
    public abstract void whiffAction();
    public abstract Queue<NoteTypes> getInputList();
}
