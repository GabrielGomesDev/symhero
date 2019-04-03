package teststuff.studio.com.teststuff.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Item", indices = @Index(value = "itemID"))
public class Item {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemID")
    public int itemID;

    public String name;
    public String description;
    public String type;
    public int price;

    public Item() {
    }

    public Item(String name, String description, String type, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    @NonNull
    public int getItemID() {
        return itemID;
    }

    public void setItemID(@NonNull int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
