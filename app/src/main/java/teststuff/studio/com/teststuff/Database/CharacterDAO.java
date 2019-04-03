package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CharacterDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long insert(Character character);

    @Update
    void update(Character character);

    @Query("SELECT * FROM Character")
    List<Character> getCharacters();

    @Query("SELECT characterID FROM Character WHERE name = :id")
    int getCharacterId(int id);

    @Query("SELECT name FROM Character WHERE (name = :name) AND (occupation = :occupation) AND (race = :race)")
    String getCurrentCharacter(String name, String occupation, String race);

    @Query("SELECT funds FROM Character WHERE characterID=:charId")
    int getFunds(int charId);

    @Query("UPDATE Character SET funds=:funds WHERE characterID=:charId")
    void updateFunds(int funds, int charId);

    @Query ("SELECT name FROM Character")
    List<String> getNames();

    @Query("DELETE FROM Character WHERE characterId = :charId")
    void deleteCharacter(int charId);

    @Query("UPDATE Character SET toughness=:toughness WHERE characterID =:charId")
    void updateToughess(int toughness, int charId);

}
