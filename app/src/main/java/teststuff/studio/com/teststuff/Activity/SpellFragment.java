package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.Spell;
import teststuff.studio.com.teststuff.Database.Spellbook;
import teststuff.studio.com.teststuff.Database.controllers.SpellBookController;
import teststuff.studio.com.teststuff.Database.controllers.SpellController;
import teststuff.studio.com.teststuff.R;

public class SpellFragment extends Fragment {

    //Character access keys
    private static final String characterKey = "new_char";
    private static final String type = "spell";
    private static Character mCharacter;
    private OnFragmentInteractionListener mListener;
    //UI Elements
    //private TextView dbTest;
    private ImageView toItemsBtn;
    private ListView spellListView;
    private ListView spellBookView;
    //List Adapters
    private ArrayAdapter<String> spellsAdapter;
    private ArrayAdapter<String> spellBookAdapter;
    //Data members
    int charId;
    List<String> spellNamesDatabase;
    List<Integer> spellIdsDatabase;
    List<String> spellBookNames;
    List<Integer> spellsOnSpellBookIds;


    public SpellFragment() {
        // Required empty public constructor
    }

    public static SpellFragment newInstance(Character character) {
        SpellFragment fragment = new SpellFragment();
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
        View view = inflater.inflate(R.layout.fragment_spell, container, false);

        return view;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        spellListView = view.findViewById(R.id.spellsListView);
        spellBookView = view.findViewById(R.id.spellBookListView);
        toItemsBtn = view.findViewById(R.id.toItemsBtn);
        //dbTest = view.findViewById(R.id.dbTest);

        charId = mCharacter.getId();

        spellIdsDatabase = SpellController.getSpellsIds();
        spellsOnSpellBookIds = SpellBookController.getCharacterSpells(charId);

        retrieveSpells();
        retrieveSpellBook(charId);

        spellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int clickedPosition, long l) {

                if(spellsOnSpellBookIds.contains(spellIdsDatabase.get(clickedPosition))){

                    Toast.makeText(getContext(), "You already have this ability", Toast.LENGTH_LONG).show();

                } else {

                    SpellBookController.saveSpellBook(charId, spellIdsDatabase.get(clickedPosition), spellNamesDatabase.get(clickedPosition));
                    spellsOnSpellBookIds.add(spellIdsDatabase.get(clickedPosition));
                    retrieveSpellBook(charId);
                    Log.v("SPELLTEST", spellsOnSpellBookIds.toString());

                }
            }
        });

        spellBookView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int spellId = spellsOnSpellBookIds.get(position);
                Spell spell = SpellController.getData(spellId);
                String name = spell.getSpellName();
                spellsOnSpellBookIds.remove(position);
                List<Integer> superlist = SpellBookController.getCharacterSpells(charId);
                Log.v("SPELLTEST", spellsOnSpellBookIds.toString() + " DATABASE CONTENTS: " + superlist.toString());
                spellBookNames.remove(name);
                SpellBookController.deleteEntry(charId, spellId);
                retrieveSpellBook(charId);
            }
        });

        toItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrieveSpellBook(charId);

                if(spellsOnSpellBookIds.isEmpty()){

                    Toast.makeText(getContext(), "Please, select your spells", Toast.LENGTH_SHORT).show();

                } else {

                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, ItemFragment.newInstance(mCharacter)).addToBackStack(null).commit();

                }

            }
        });

        spellListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int id = spellIdsDatabase.get(i);

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container,
                        ElementDescription.newInstance(id, type)).addToBackStack(null).commit();

                return false;
            }
        });

    }

    public void retrieveSpells(){

        try{

            //This gathers the Spell Names to display list
            spellNamesDatabase = SpellController.getSpellsNames();

            //Adapter to diaplay List of Spells
            spellsAdapter = new ArrayAdapter<String>(getContext(), R.layout.spell_list_layout, spellNamesDatabase);
            spellListView.setAdapter(spellsAdapter);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void retrieveSpellBook(int id){

        try{

            spellBookNames = SpellBookController.retrieveSpellNames(id);

            spellBookAdapter = new ArrayAdapter<String>(getContext(), R.layout.spell_list_layout, spellBookNames);
            spellBookView.setAdapter(spellBookAdapter);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
