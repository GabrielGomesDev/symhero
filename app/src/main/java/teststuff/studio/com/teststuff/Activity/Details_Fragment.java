package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Details_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Details_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Details_Fragment extends Fragment {

    private static final String characterKey = "new_char";

    //Interface members
    private EditText new_Name;
    private EditText new_Race;
    private EditText new_Occupation;
    private EditText new_Shadow;
    private ImageView next;
    //private TextView db_test;
    private static Character mCharacter;
    private OnFragmentInteractionListener mListener;

    public Details_Fragment() {
    // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     *
     * @return A new instance of fragment Details_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Details_Fragment newInstance(Character character) {
        Details_Fragment fragment = new Details_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_details_, container, false);

        new_Name = view.findViewById(R.id.new_Name);
        new_Occupation = view.findViewById(R.id.new_Occup);
        new_Race = view.findViewById(R.id.new_Race);
        new_Shadow = view.findViewById(R.id.new_Shadow);
        next = view.findViewById(R.id.to_Attributes_Button);
        //db_test = view.findViewById(R.id.db_test);

        return view;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = new_Name.getText().toString();
                String occupation = new_Occupation.getText().toString();
                String race = new_Race.getText().toString();
                String shadow = new_Shadow.getText().toString();

                if(name.equals("") || occupation.equals("") || race.equals("") || shadow.equals("")){

                    Toast.makeText(getActivity(), "Please fill out all the details", Toast.LENGTH_SHORT).show();

                } else {

                    mCharacter.setName(name);
                    mCharacter.setOccupation(occupation);
                    mCharacter.setRace(race);
                    mCharacter.setShadow(shadow);

                    Toast.makeText(getActivity(), "Character created successfully", Toast.LENGTH_SHORT).show();

                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, Attributes_Fragment.newInstance(mCharacter))
                            .addToBackStack(null).commit();
                }
            }
        });
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
