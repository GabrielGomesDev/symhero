package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Spellbook"/*, indices = @Index(value = "spellBookID"), foreignKeys = @ForeignKey(entity = Spell.class, parentColumns = "spellID", childColumns = "spellID", onDelete = CASCADE)*/)
public class Spellbook {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "spellBookID")
    public int spellBookID;

    public int characterId;

    public int spellID;

    public String spellName;

    public Spellbook() {
    }

    public Spellbook(int characterId, int spellID, String spellName) {
        this.characterId = characterId;
        this.spellID = spellID;
        this.spellName = spellName;
    }

    public int getSpellBookID() {
        return spellBookID;
    }

    public void setSpellBookID(@NonNull int spellBookID) {
        this.spellBookID = spellBookID;
    }

    public int getSpellID() {
        return spellID;
    }

    public void setSpellID(int spellID) {
        this.spellID = spellID;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }
}
