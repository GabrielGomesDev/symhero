package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface InventoryDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Inventory inventory);

    @Query("SELECT DISTINCT itemName FROM Inventory WHERE characterId = :charId")
    List<String> retrieveItemsNames(int charId);

    @Query("SELECT itemID FROM Inventory WHERE characterId = :charId")
    List<Integer> getInventoryItems(int charId);

    @Query("SELECT quantity FROM Inventory WHERE characterId = :charId AND itemID = :itemId")
    int getItemQuantity(int charId, int itemId);

    @Query(("UPDATE Inventory SET quantity=:quantity WHERE characterId = :charId AND itemID = :itemId"))
    void updateQuantity(int charId, int itemId, int quantity);

    @Query("SELECT * FROM Inventory")
    List<Inventory> inventory();

    @Query(("DELETE FROM Inventory WHERE characterId = :charId AND itemID = :itemId"))
    void deleteEntry(int charId, int itemId);

    @Query("DELETE FROM Inventory WHERE characterId = :charId")
    void deleteCharacter(int charId);


}
