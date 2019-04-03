package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insert(Item item);

    @Query("SELECT * FROM Item")
    List<Item> getItems();

    @Query("SELECT name FROM Item")
    List<String> getItemsNames();

    @Query("SELECT itemID FROM Item")
    List<Integer> getItemsIds();

    @Query("SELECT * FROM Item WHERE itemID = :itemId")
    Item getData(int itemId);


}
