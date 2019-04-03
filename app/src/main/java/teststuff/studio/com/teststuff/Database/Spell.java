package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Spell", indices = @Index(value = "spellID"))
public class Spell {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "spellID")
    public int spellID;
    public String spellName;
    public String spellDescription;

    public Spell() {
    }

    public Spell(String spellName, String spellDescription) {
        this.spellName = spellName;
        this.spellDescription = spellDescription;
    }

    @NonNull
    public int getSpellID() {
        return spellID;
    }

    public void setSpellID(@NonNull int spellID) {
        this.spellID = spellID;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSpellDescription() {
        return spellDescription;
    }

    public void setSpellDescription(String spellDescription) {
        this.spellDescription = spellDescription;
    }

}
