package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SpellbookDAO {

    @Insert
    void insert(Spellbook spellBook);


    @Query("SELECT spellBookID FROM Spellbook")
    List<Integer> getSpellBookIds();

    @Query("SELECT * FROM Spellbook")
    List<Spellbook> getAllSpellBooks();

    @Query("SELECT * FROM Spellbook WHERE characterId = :charId")
    Spellbook getSpellBook(int charId);

    @Query("SELECT spellName FROM Spellbook WHERE characterId = :charId")
    List<String> retrieveSpellNames(int charId);

    @Query("SELECT characterID FROM Spellbook")
    List<Integer> getCharacterIds();

    @Query("SELECT spellID FROM Spellbook WHERE characterID = :charId")
    List<Integer> getCharacterSpells(int charId);

    @Query(("DELETE FROM Spellbook WHERE characterId = :charId AND spellID = :spellId"))
    void deleteEntry(int charId, int spellId);

    @Query("DELETE FROM Spellbook WHERE characterId = :charId")
    void deleteCharacter(int charId);

}
