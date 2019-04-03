package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SpellDAO {

    @Insert
    void insert(Spell spell);

    @Query("SELECT spellName FROM Spell")
    List<String> getSpellsNames();

    @Query("SELECT spellID FROM Spell")
    List<Integer> getSpellsIds();

    @Query("SELECT * FROM Spell WHERE spellID = :spellId")
    Spell getData(int spellId);


}
