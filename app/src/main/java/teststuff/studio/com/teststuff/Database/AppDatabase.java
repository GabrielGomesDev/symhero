package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Character.class, Inventory.class, Spellbook.class, Item.class, Spell.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CharacterDAO charDao();
    public abstract ItemDAO itemDao();
    public abstract SpellDAO spellDao();
    public abstract InventoryDAO inventoryDao();
    public abstract SpellbookDAO spellBookDao();

}
