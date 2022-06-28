package net.ethansk13s.symphocraft.core.rhythm;

import net.minecraft.client.util.InputUtil;

public enum NoteTypes {
    UP(new DirectionalNote(InputUtil.fromTranslationKey("key.keyboard.w"))),
    DOWN(new DirectionalNote(InputUtil.fromTranslationKey("key.keyboard.s"))),
    LEFT(new DirectionalNote(InputUtil.fromTranslationKey("key.keyboard.a"))),
    RIGHT(new DirectionalNote(InputUtil.fromTranslationKey("key.keyboard.d")));

    private BaseNote note;

    private NoteTypes(BaseNote note) {
        this.note = note;
    }

    public BaseNote getNote() {
        return this.note;
    }

    public boolean noteHit() {
        return this.note.hit();
    }
}
