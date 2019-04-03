package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (tableName = "Inventory"/*, indices = @Index(value = "inventoryID"), foreignKeys = @ForeignKey(entity = Item.class, parentColumns = "itemID", childColumns = "itemID", onDelete = CASCADE)*/)
public class Inventory {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "inventoryID")
    public int inventoryID;
    public int characterId;
    public int itemID;
    public String itemName;
    public String itemType;
    public int quantity;

    public Inventory() {
    }

    public Inventory(int characterId, int itemID, String itemName, String itemType, int quantity) {
        this.characterId = characterId;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemType = itemType;
        this.quantity = quantity;
    }

    @NonNull
    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(@NonNull int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
