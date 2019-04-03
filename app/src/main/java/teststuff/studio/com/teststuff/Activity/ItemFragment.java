package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.Inventory;
import teststuff.studio.com.teststuff.Database.Item;
import teststuff.studio.com.teststuff.Database.controllers.CharacterController;
import teststuff.studio.com.teststuff.Database.controllers.InventoryController;
import teststuff.studio.com.teststuff.Database.controllers.ItemController;
import teststuff.studio.com.teststuff.R;

public class ItemFragment extends Fragment {

    //Character access keys
    private static final String characterKey = "new_char";
    private static final String type = "item";
    private static Character mCharacter;
    private ItemFragment.OnFragmentInteractionListener mListener;
    //UI Elements
    //private TextView dbTest;
    private ImageView finishCreationButton;
    private ListView itemsListView, inventoryListView;
    private TextView characterFundsTextView;
    //List Adapters
    private ArrayAdapter<String> itemsAdapter;
    private ArrayAdapter<String> inventorykAdapter;
    //Data members
    private int id, funds, newfunds, quantity;
    private List<String> itemsNamesDatabase;
    private List<Integer> itemsIdsDatabase;
    private List<String> inventoryNames;
    private List<Integer> itemsOnInventoryIds;
    private List<Item> itemDatabase;

    public ItemFragment() {
        // Required empty public constructor
    }

    public static ItemFragment newInstance(Character character) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(characterKey, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacter = (Character)getArguments().getSerializable(characterKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemsListView = view.findViewById(R.id.itemsListView);
        inventoryListView = view.findViewById(R.id.inventoryListView);
        finishCreationButton = view.findViewById(R.id.finishCreationBtn);
        characterFundsTextView = view.findViewById(R.id.characterFundsTextView);

        id = mCharacter.getId();
        funds = CharacterController.getFunds(id);
        characterFundsTextView.setText(String.valueOf(mCharacter.getFunds()));
        itemsIdsDatabase = ItemController.getItemsIds();
        itemsOnInventoryIds = InventoryController.getInventoryIds(id);
        itemDatabase = ItemController.getItem();

        retrieveItems();
        retrieveInventory(id);

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Item item = itemDatabase.get(i);
                String name = item.getName();
                String type = item.getType();
                int itemId = item.getItemID();
                int price = item.getPrice();
                //Log.v("QUANTITY BEFORE", "Quantity" + InventoryController.getItemQuantity(id, itemId));
                /*List<Inventory> inventories = App.myAppDatabase.inventoryDao().inventory();
                for(Inventory inv : inventories){
                    int invId = inv.getInventoryID();
                    int invChar = inv.getCharacterId();
                    int invItemId = inv.getItemID();
                    String invItemName = inv.getItemName();
                    String invIntemType = inv.getItemType();
                    int invQuantity = inv.getQuantity();

                    Log.v("INVENTORY", "Id: "+invId+" Char: "+invChar+" ItemId: "+ invItemId+ " ItemName: "+invItemName+" ItemType: "+invIntemType+" Quantity: "+invQuantity *//*+ "\n\n" + "Inventory List: " + itemsOnInventoryIds.toString()*//*);

                }*/

                if(!itemsOnInventoryIds.contains(itemId)){

                    if((funds-price) < 0){

                        Toast.makeText(getContext(), "Not enough funds to buy this item", Toast.LENGTH_SHORT).show();

                    } else {

                        quantity = 1;
                        Log.v("QTY", String.valueOf(quantity));
                        InventoryController.saveInventory(id, itemId, name, type, quantity);
                        itemsOnInventoryIds.add(itemId);
                        Log.v("INVENTORYITEMS", itemsOnInventoryIds.toString());
                        funds = funds - price;
                        CharacterController.updateFunds(funds, id);
                        Log.v("QUANTITY AFTER ONE", "Quantity" + InventoryController.getItemQuantity(id, itemId));
                        newfunds = CharacterController.getFunds(id);
                        characterFundsTextView.setText(String.valueOf(newfunds));
                        Log.v("FUNDS TEST", "Funds: " + String.valueOf(funds) + " | Price: " + String.valueOf(price) + " | New Funds: " + String.valueOf(newfunds));
                        Toast.makeText(getContext(), "Item " + name + " added to your inventory", Toast.LENGTH_SHORT).show();
                        retrieveInventory(id);
                    }

                } else {

                    if((funds-price) < 0){

                        Toast.makeText(getContext(), "Not enough funds to buy this item", Toast.LENGTH_SHORT).show();

                    } else {

                        quantity = InventoryController.getItemQuantity(id, itemId);
                        Log.v("QTY", "QTY ON INV: " + String.valueOf(quantity));
                        quantity++;
                        Log.v("QTY", "QTY After++: " + String.valueOf(quantity));
                        InventoryController.updateQuantity(id, itemId, quantity);
                        Log.v("QUANTITY AFTER TWO", "Quantity" + InventoryController.getItemQuantity(id, itemId));
                        funds = funds - price;
                        CharacterController.updateFunds(funds, id);
                        newfunds = CharacterController.getFunds(id);
                        //Log.v("FUNDS TEST", "Funds: " + String.valueOf(funds) + " | Price: " + String.valueOf(price) + " | New Funds: " + String.valueOf(newfunds));
                        characterFundsTextView.setText(String.valueOf(newfunds));
                        Toast.makeText(getContext(), "Item " + name + " added to your inventory. You have " + quantity + " Units", Toast.LENGTH_SHORT).show();
                        retrieveInventory(id);

                    }
                }

            }
        });

        itemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int id = itemsIdsDatabase.get(i);

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container,
                        ElementDescription.newInstance(id, type)).addToBackStack(null).commit();

                return false;
            }
        });

        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int itemId = itemsOnInventoryIds.get(i);
                Item item = ItemController.getData(itemId);
                String name = item.getName();
                int price = item.getPrice();

                quantity = InventoryController.getItemQuantity(id, itemId);
                Log.v("INVITEMS ItemFrag Bef", "IDS: " + itemsOnInventoryIds.toString() + " Names: " + inventoryNames.toString() + "Quantity: " + quantity);
                if(quantity == 1){
                    itemsOnInventoryIds.remove(i);
                    inventoryNames.remove(name);
                    quantity--;
                    InventoryController.deleteEntry(id, itemId);
                    Log.v("INVITEMS ItemFrag Rem", "IDS: " + itemsOnInventoryIds.toString() + " Names: " + inventoryNames.toString() + "Quantity: " + quantity);
                    Toast.makeText(getContext(), "Item " + name + " removed from inventory. You have " + quantity + " Units", Toast.LENGTH_SHORT).show();
                    funds = funds + price;
                    CharacterController.updateFunds(funds, id);
                    newfunds = CharacterController.getFunds(id);
                    characterFundsTextView.setText(String.valueOf(newfunds));
                    retrieveInventory(id);

                } else {
                    quantity--;
                    Log.v("INVENTORYITEMS Remove", "IDS: " + itemsOnInventoryIds.toString() + " Names: " + inventoryNames.toString());
                    InventoryController.updateQuantity(id, itemId, quantity);
                    Toast.makeText(getContext(), "Item " + name + " removed from inventory. You have " + quantity + " Units", Toast.LENGTH_SHORT).show();
                    funds = funds + price;
                    CharacterController.updateFunds(funds, id);
                    newfunds = CharacterController.getFunds(id);
                    characterFundsTextView.setText(String.valueOf(newfunds));
                }
            }
        });

        finishCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, CharacterSheet.newInstance(mCharacter))
                        .addToBackStack(null).commit();

            }
        });

    }

    public void retrieveItems(){

        try{

            //This gathers the Spell Names to display list
            itemsNamesDatabase = ItemController.getItemsNames();

            //Adapter to diaplay List of Spells
            itemsAdapter = new ArrayAdapter<String>(getContext(), R.layout.spell_list_layout, itemsNamesDatabase);
            itemsListView.setAdapter(itemsAdapter);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void retrieveInventory(int id){

        try{

            inventoryNames = InventoryController.retrieveItemsNames(id);

            inventorykAdapter = new ArrayAdapter<String>(getContext(), R.layout.spell_list_layout, inventoryNames);
            inventoryListView.setAdapter(inventorykAdapter);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
