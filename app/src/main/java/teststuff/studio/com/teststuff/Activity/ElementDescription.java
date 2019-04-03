package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import teststuff.studio.com.teststuff.Database.Item;
import teststuff.studio.com.teststuff.Database.Spell;
import teststuff.studio.com.teststuff.Database.controllers.ItemController;
import teststuff.studio.com.teststuff.Database.controllers.SpellController;
import teststuff.studio.com.teststuff.R;


public class ElementDescription extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ELEMENT_ID = "element_id";
    private static final String ELEMENT_TYPE = "element_type";
    private String type;
    private int id;
    private TextView elementName, elementDescription, elementType, elementPrice;

    private OnFragmentInteractionListener mListener;

    public ElementDescription() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ElementDescription newInstance(int id, String type) {
        ElementDescription fragment = new ElementDescription();
        Bundle args = new Bundle();
        args.putInt(ELEMENT_ID, id);
        args.putString(ELEMENT_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ELEMENT_ID);
            type = getArguments().getString(ELEMENT_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_element_description, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        elementName = view.findViewById(R.id.elementName);
        elementDescription = view.findViewById(R.id.elementDescription);
        elementType = view.findViewById(R.id.elementType);
        elementPrice = view.findViewById(R.id.elementPrice);

        if(type.equals("item")){

            //searchItems
            Item item = ItemController.getData(id);
            elementName.setText(item.getName());
            elementDescription.setText("Description" + "\n\n" + item.getDescription());
            elementType.setText("Type: " + item.getType());
            elementPrice.setText("Price: " + String.valueOf(item.price));

        } else {

            //searchSpells
            Spell spell = SpellController.getData(id);
            elementName.setText(spell.getSpellName());
            elementDescription.setText("Description" + "\n\n" + spell.getSpellDescription());
            elementType.setText("Type: " + "Spell");
            elementPrice.setText("");

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
