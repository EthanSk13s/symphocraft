package net.ethansk13s.symphocraft.core.symphogears.combos.dark_gungnir;

import java.util.LinkedList;
import java.util.Queue;

import net.ethansk13s.symphocraft.Symphocraft;
import net.ethansk13s.symphocraft.core.rhythm.BaseCombo;
import net.ethansk13s.symphocraft.core.rhythm.NoteTypes;

public class SpearThrust extends BaseCombo {
    private final Queue<NoteTypes> inputList;

    public SpearThrust() {
        this.inputList = new LinkedList<>();

        this.inputList.add(NoteTypes.UP);
        this.inputList.add(NoteTypes.LEFT);
        this.inputList.add(NoteTypes.DOWN);
    }

    public Queue<NoteTypes> getInputList() {
        return this.inputList;
    }

    @Override
    public void doAction() {
        Symphocraft.LOGGER.info("nice thrust :)");
    }

    @Override
    public void whiffAction() {
        // TODO Auto-generated method stub
        
    }
    
}
