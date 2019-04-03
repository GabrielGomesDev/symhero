package teststuff.studio.com.teststuff.Database.controllers;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Inventory;
import teststuff.studio.com.teststuff.Database.Item;

public class InventoryController {

    public static void saveInventory(int characterId, int itemID, String itemName, String itemType, int quantity){
        App.myAppDatabase.inventoryDao().insert(new Inventory(characterId, itemID, itemName, itemType, quantity));
    }

    public static List<String> retrieveItemsNames(int charId){
        List<String> spellNames = App.myAppDatabase.inventoryDao().retrieveItemsNames(charId);

        return spellNames;
    }

    public static List<Integer> getInventoryIds(int charId){
        List<Integer> inventoryItems = App.myAppDatabase.inventoryDao().getInventoryItems(charId);

        return inventoryItems;
    }

    public static int getItemQuantity(int charId, int itemId){

        int itemQuantity = App.myAppDatabase.inventoryDao().getItemQuantity(charId, itemId);

        return itemQuantity;
    }

    public static void updateQuantity(int charId, int itemId, int quantity){

        App.myAppDatabase.inventoryDao().updateQuantity(charId, itemId, quantity);
    }

    public static void deleteEntry(int charId, int itemId){

        App.myAppDatabase.inventoryDao().deleteEntry(charId, itemId);

    }

    public static void deleteCharacter(int charId){

        App.myAppDatabase.inventoryDao().deleteCharacter(charId);

    }


}
