package teststuff.studio.com.teststuff.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.controllers.CharacterController;
import teststuff.studio.com.teststuff.Database.controllers.InventoryController;
import teststuff.studio.com.teststuff.Database.controllers.SpellBookController;
import teststuff.studio.com.teststuff.R;

public class Load_Screen extends Fragment {

    //Character access keys
    private static final String characterKey = "new_char";
    private static Character mCharacter;
    private OnFragmentInteractionListener mListener;
    //UI Elements
    private ListView charactersLV;
    private ArrayAdapter<String> namesAdapter;
    //Data members
    private List<String> namesList;
    private List<Character> characterList;
    private AlertDialog.Builder deleteAlert;

    public Load_Screen() {
    // Required empty public constructor
    }

    public static Load_Screen newInstance(Character character) {
        Load_Screen fragment = new Load_Screen();
        Bundle args = new Bundle();
        args.putSerializable(characterKey, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacter = (Character) getArguments().getSerializable(characterKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load__screen, container, false);

        return view;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        charactersLV = view.findViewById(R.id.chatactersListView);
        retrieveCharacters();
        characterList = CharacterController.getCharacters();

        charactersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCharacter = characterList.get(i);

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, CharacterSheet.newInstance(mCharacter))
                        .addToBackStack(null).commit();

            }
        });

        charactersLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCharacter = characterList.get(i);
                //generates alert dialog
                deleteAlert = new AlertDialog.Builder(getContext());

                //config dialog
                deleteAlert.setTitle("Delete Character");
                deleteAlert.setMessage("Do you really want to delete this Character?");
                deleteAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                deleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int charId = mCharacter.getId();
                        String name = mCharacter.getName();
                        Log.v("DELETETEST ID", String.valueOf(charId));
                        CharacterController.deleteCharacter(charId);
                        List<String> newnames = CharacterController.getNames();
                        Log.v("DELETETEST", newnames.toString());
                        SpellBookController.deleteCharacter(charId);
                        InventoryController.deleteCharacter(charId);
                        namesList.remove(name);

                        retrieveCharacters();
                    }
                });
                deleteAlert.show();
                return true;
            }
        });

    }

    public void retrieveCharacters(){

        try{

            namesList = CharacterController.getNames();

            //Adapter to diaplay List of Spells
            namesAdapter = new ArrayAdapter<>(getContext(), R.layout.character_load_list, namesList);

            charactersLV.setAdapter(namesAdapter);


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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
