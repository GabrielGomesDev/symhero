package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.Item;
import teststuff.studio.com.teststuff.Database.controllers.CharacterController;
import teststuff.studio.com.teststuff.Database.controllers.InventoryController;
import teststuff.studio.com.teststuff.Database.controllers.ItemController;
import teststuff.studio.com.teststuff.R;


public class ItemManagement extends Fragment {
    //Character access keys
    private static final String characterKey = "new_char";
    private static final String type = "item";
    private static Character mCharacter;
    private OnFragmentInteractionListener mListener;
    //private TextView dbTest;
    private ImageView toCharBtn, toSpellsBtn;
    private ListView itemsListView;
    private ListView inventoryListView;
    private TextView characterFundsTextView;
    //List Adapters
    private ArrayAdapter<String> itemsAdapter;
    private ArrayAdapter<String> inventorykAdapter;
    //Data members
    int id, funds, newfunds, quantity;
    private List<String> itemsNamesDatabase;
    private List<Integer> itemsIdsDatabase;
    private List<String> inventoryNames;
    private List<Integer> itemsOnInventoryIds;
    private List<Item> itemDatabase;

    public ItemManagement() {
        // Required empty public constructor
    }

    public static ItemManagement newInstance(Character character) {
        ItemManagement fragment = new ItemManagement();
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
        View view = inflater.inflate(R.layout.fragment_item_management, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemsListView = view.findViewById(R.id.itemsListView);

        inventoryListView = view.findViewById(R.id.inventoryListView);
        characterFundsTextView = view.findViewById(R.id.characterFundsTextView);
        toCharBtn = view.findViewById(R.id.sheetTabBtn);
        toSpellsBtn = view.findViewById(R.id.spellsTabBtn);


        id = mCharacter.getId();
        funds = CharacterController.getFunds(id);
        characterFundsTextView.setText(String.valueOf(funds));
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
                Log.v("QTY on DB", String.valueOf(quantity));

                if (!itemsOnInventoryIds.contains(itemId)) {

                    if ((funds - price) < 0) {

                        Toast.makeText(getContext(), "Not enough funds to buy this item", Toast.LENGTH_SHORT).show();

                    } else {

                        quantity = 1;
                        InventoryController.saveInventory(id, itemId, name, type, quantity);
                        Log.v("QTY Insert 1", String.valueOf(quantity));
                        itemsOnInventoryIds.add(itemId);
                        funds = funds - price;
                        CharacterController.updateFunds(funds, id);
                        newfunds = CharacterController.getFunds(id);
                        characterFundsTextView.setText(String.valueOf(newfunds));
                        Log.v("FUNDS TEST", "ID: " + id + "Funds: " + String.valueOf(funds) + " | Price: " + String.valueOf(price) + " | New Funds: " + String.valueOf(newfunds));
                        Toast.makeText(getContext(), "Item " + name + " added to your inventory", Toast.LENGTH_SHORT).show();
                        retrieveInventory(id);
                    }

                } else {

                    if ((funds - price) < 0) {

                        Toast.makeText(getContext(), "Not enough funds to buy this item", Toast.LENGTH_SHORT).show();

                    } else {

                        quantity = InventoryController.getItemQuantity(id, itemId);
                        quantity++;
                        Log.v("QTY Sum", String.valueOf(quantity));
                        InventoryController.updateQuantity(id, itemId, quantity);
                        //itemsOnInventoryIds.add(itemId);
                        funds = funds - price;
                        CharacterController.updateFunds(funds, id);
                        newfunds = CharacterController.getFunds(id);
                        Log.v("FUNDS TEST", "Funds: " + String.valueOf(funds) + " | Price: " + String.valueOf(price) + " | New Funds: " + String.valueOf(newfunds));
                        characterFundsTextView.setText(String.valueOf(newfunds));
                        Toast.makeText(getContext(), "Item " + name + " added to your inventory. You have " + quantity + " Units", Toast.LENGTH_SHORT).show();
                        retrieveInventory(id);
                    }
                }

            }
        });

        inventoryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                Log.v("QTY Del DB", String.valueOf(quantity));
                Log.v("INVITEMS ItemFrag Bef", "IDS: " + itemsOnInventoryIds.toString() + " Names: " + inventoryNames.toString() + "Quantity: " + quantity);

                if (quantity == 1) {
                    quantity--;
                    itemsOnInventoryIds.remove(i);
                    inventoryNames.remove(name);
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

        toCharBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, CharacterSheet.newInstance(mCharacter)).addToBackStack(null).commit();

            }
        });

        toSpellsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, SpellsManagement.newInstance(mCharacter)).addToBackStack(null).commit();

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
