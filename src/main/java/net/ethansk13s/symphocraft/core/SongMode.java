package net.ethansk13s.symphocraft.core;

import java.util.ArrayList;
import java.util.HashMap;

import net.ethansk13s.symphocraft.core.rhythm.BaseCombo;
import net.ethansk13s.symphocraft.core.rhythm.ComboTracker;
import net.ethansk13s.symphocraft.core.rhythm.NoteTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

public class SongMode {
    private boolean isInSongMode;
    private ComboTracker comboTracker;
    private NoteTypes up;
    private NoteTypes down;
    private NoteTypes left;
    private NoteTypes right;
    private int currentTicks;
    private InputThread inputThread;

    private static final int SONG_TICKS = 200;

    public SongMode(ArrayList<BaseCombo> combos) {
        this.up = NoteTypes.UP;
        this.down = NoteTypes.DOWN;
        this.left = NoteTypes.LEFT;
        this.right = NoteTypes.RIGHT;

        this.isInSongMode = false;
        this.comboTracker = new ComboTracker(combos);
        this.currentTicks = 0;
        this.inputThread = new InputThread();
        this.inputThread.startThread();
    }

    @Environment(EnvType.CLIENT)
    public void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.isInSingleplayer() && client.isPaused()) {
            return;
        }

        if (!this.isInSongMode) {
            return;
        } else {
            if (this.currentTicks == SONG_TICKS) {
                this.isInSongMode = false;
                this.comboTracker.getInputs().clear();
                this.currentTicks = 0;
            } else {
                // TODO: Handle input debounce
                long handle = client.getWindow().getHandle();
                HashMap<String, Integer> queue = inputThread.getQueue();

                if (InputUtil.isKeyPressed(handle, this.up.getNote().getDirection().getCode()) && !queue.containsKey("input")) {
                    this.handleInput(this.up);
                } else if (InputUtil.isKeyPressed(handle, this.down.getNote().getDirection().getCode()) && !queue.containsKey("input")) {
                    this.handleInput(this.down);
                } else if (InputUtil.isKeyPressed(handle, this.left.getNote().getDirection().getCode()) && !queue.containsKey("input")) {
                    this.handleInput(this.left);
                } else if (InputUtil.isKeyPressed(handle, this.right.getNote().getDirection().getCode()) && !queue.containsKey("input")) {
                    this.handleInput(this.right);
                }

                this.comboTracker.readNotes();
                this.currentTicks++;
            }
        }
    }

    private void handleInput(NoteTypes note) {
        if (note.noteHit()) {
            this.comboTracker.addNote(note);
            this.inputThread.getQueue().put("input", 100);
        }

    }

    public void resetCombos(ArrayList<BaseCombo> combos) {
        this.comboTracker = new ComboTracker(combos);
    }

    public boolean getIsInSongMode() {
        return this.isInSongMode;
    }

    public NoteTypes getUp() {
        return this.up;
    }

    public NoteTypes getDown() {
        return this.down;
    }

    public NoteTypes getLeft() {
        return this.left;
    }

    public NoteTypes getRight() {
        return this.right;
    }

    public InputThread getInputThread() {
        return this.inputThread;
    }

    public void setIsInSongMode(boolean newValue) {
        this.isInSongMode = newValue;
    }

}
