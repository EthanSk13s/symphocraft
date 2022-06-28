package net.ethansk13s.symphocraft.core.rhythm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import net.ethansk13s.symphocraft.Symphocraft;

public class ComboTracker {
    private Queue<NoteTypes> inputs;
    private Queue<NoteTypes> comparableInputs;
    private ArrayList<BaseCombo> combos;

    public ComboTracker(ArrayList<BaseCombo> combos) {
        this.inputs = new LinkedList<>();
        this.comparableInputs = new LinkedList<>();
        this.combos = combos;
    }

    public Queue<NoteTypes> getInputs() {
        return this.comparableInputs;
    }

    public void addNote(NoteTypes note) {
        this.inputs.add(note);
    }

    public void readNotes() {
        if (!this.inputs.isEmpty()) {
            NoteTypes currentNote = this.inputs.remove();
            this.eliminateCombo(currentNote);
            this.comparableInputs.add(currentNote);
            Symphocraft.LOGGER.info(this.comparableInputs.toString());
        }

        if (this.combos.size() != 0) {
            if (this.combos.get(0).getInputList().equals(this.comparableInputs)) {
                this.combos.get(0).doAction();
                this.comparableInputs.clear();
                this.inputs.clear();
            }
        }
    }

    private void eliminateCombo(NoteTypes note) {
        for (int i = 0; i < this.combos.size(); i++) {
            Queue<NoteTypes> currentCombo = this.combos.get(i).getInputList();
            NoteTypes input = currentCombo.remove();
            currentCombo.add(input);
            if (!input.equals(note)) {
                this.combos.remove(i);
            }
        }
    }
}
