package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.Item;
import teststuff.studio.com.teststuff.Database.Spell;
import teststuff.studio.com.teststuff.Database.Spellbook;
import teststuff.studio.com.teststuff.Database.controllers.ItemController;
import teststuff.studio.com.teststuff.Database.controllers.SpellBookController;
import teststuff.studio.com.teststuff.Database.controllers.SpellController;
import teststuff.studio.com.teststuff.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpellsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpellsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpellsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    //UI elements
    private ListView spellListView;
    private ListView spellBookView;
    //List Adapters
    private ArrayAdapter<String> spellsAdapter;
    private ArrayAdapter<String> spellBookAdapter;
    //Data members
    List<String> spellNamesDatabase;
    List<String> spellBookNames;
    private TextView dbTest;
    //Character key
    private static final String characterKey = "new_char";
    private static Character mCharacter;

    private OnFragmentInteractionListener mListener;

    public SpellsFragment() {
        // Required empty public constructor
    }

    public static SpellsFragment newInstance(Character character) {
        SpellsFragment fragment = new SpellsFragment();
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
        View view = inflater.inflate(R.layout.fragment_spells, container, false);

        return view;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        /*int charId = mCharacter.getCharacterID();

        spellListView = view.findViewById(R.id.spellsListView);
        spellBookView = view.findViewById(R.id.spellBookView);
        dbTest = view.findViewById(R.id.dbTest);
        dbTest.setText(String.valueOf(charId));

        try{


        } catch (Exception e){

            e.printStackTrace();

        }*/

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

    public void retrieveSpellBook(int charIdSpellbook){

        try{

            /*spellNamesOnSpellBook = SpellBookController.retrieveSpellNames(charIdSpellbook);

            spellBookAdapter = new ArrayAdapter<String>(getContext(), R.layout.spell_list_layout, spellNamesOnSpellBook);
            spellBookView.setAdapter(spellBookAdapter);*/

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
