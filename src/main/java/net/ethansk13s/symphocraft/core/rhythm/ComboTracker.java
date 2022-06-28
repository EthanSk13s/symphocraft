package net.ethansk13s.symphocraft.core.rhythm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import net.ethansk13s.symphocraft.Symphocraft;

public class ComboTracker {
    private Queue<NoteTypes> inputs;
    private Queue<NoteTypes> comparableInputs;
    private ArrayList<BaseCombo> combos;
    private ArrayList<BaseCombo> filteredCombos;

    public ComboTracker(ArrayList<BaseCombo> combos) {
        this.inputs = new LinkedList<>();
        this.comparableInputs = new LinkedList<>();
        this.combos = combos;
        this.filteredCombos = new ArrayList<>();
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
                this.resetAllCombos();
            }
        } else {
            this.comparableInputs.clear();
            this.inputs.clear();
            this.resetAllCombos();
        }
    }

    private void eliminateCombo(NoteTypes note) {
        for (int i = 0; i < this.combos.size(); i++) {
            Queue<NoteTypes> currentCombo = this.combos.get(i).getInputList();
            NoteTypes input = currentCombo.remove();
            currentCombo.add(input);

            if (!input.equals(note)) {
                BaseCombo combo = this.combos.remove(i);
                this.filteredCombos.add(combo);
            }
        }
    }

    private void resetAllCombos() {
        if (this.combos.size() != 0) {
            for (int i = 0; i < this.combos.size(); i++) {
                this.combos.get(i).resetInput();
    
                if (this.filteredCombos.size() != 0) {
                    this.combos.add(this.filteredCombos.remove(0));
                }
            }
        } else {
            this.combos.addAll(this.filteredCombos);
            this.filteredCombos.clear();
        }
    }
}
