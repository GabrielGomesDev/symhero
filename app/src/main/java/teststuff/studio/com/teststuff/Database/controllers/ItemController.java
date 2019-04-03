package teststuff.studio.com.teststuff.Database.controllers;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Item;

public class ItemController {

    public static void saveItem(String name, String description, String type, int price) {
        App.myAppDatabase.itemDao().insert(new Item(name, description, type, price));
    }

    public static List<Item> getItem(){
        List<Item> itemList = App.myAppDatabase.itemDao().getItems();

        return itemList;
    }

    public static List<String> getItemsNames(){

        List<String> itemList = App.myAppDatabase.itemDao().getItemsNames();

        return itemList;
    }

    public static List<Integer> getItemsIds(){

        List<Integer> itemIds = App.myAppDatabase.itemDao().getItemsIds();

        return itemIds;
    }

    public static Item getData(int itemId){

        Item item = App.myAppDatabase.itemDao().getData(itemId);

        return item;
    }


}
