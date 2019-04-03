package teststuff.studio.com.teststuff.Activity;

import android.arch.persistence.room.Query;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.controllers.CharacterController;
import teststuff.studio.com.teststuff.R;


public class CharacterSheet extends Fragment implements View.OnClickListener {
    //Character access keys
    private static final String characterKey = "new_char";
    private static Character mCharacter;
    private OnFragmentInteractionListener mListener;
    //UI Members
    private TextView charName, shadow, race, occupation, toughness, painThreshold, corruptionThreshold,
    accurate, cunning, discreet, quick, persuasive, strong, vigilant, resolute;
    private ImageView accRollBtn, cunRollBtn, disRollBtn, perRollBtn, quiRollBtn, resRollBtn, strRollBtn, vigRollBtn, saveCharBtn, spellsTabBtn, itemsTabBtn, touBtnUp, touBtnDwn;


    public CharacterSheet() {
        // Required empty public constructor
    }

    public static CharacterSheet newInstance(Character character) {
        CharacterSheet fragment = new CharacterSheet();
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
        View view = inflater.inflate(R.layout.fragment_character_sheet, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*Log.v("SHEET", "Sheet fund:" + String.valueOf(mCharacter.getFunds()));
        Log.v("SHEET", "Sheet fund:" + String.valueOf(mCharacter.getName()));*/

        shadow = view.findViewById(R.id.characterShadowTv);
        charName = view.findViewById(R.id.characterNameTv);
        race = view.findViewById(R.id.characterRaceTv);
        occupation = view.findViewById(R.id.characterOccupationTv);
        toughness = view.findViewById(R.id.toughness);
        painThreshold = view.findViewById(R.id.painThresholdTv);
        corruptionThreshold = view.findViewById(R.id.corruptionThresholdTv);
        accurate = view.findViewById(R.id.accurateTv);
        cunning = view.findViewById(R.id.cunningTv);
        discreet = view.findViewById(R.id.discreetTv);
        quick = view.findViewById(R.id.quickTv);
        persuasive = view.findViewById(R.id.persuasiveTv);
        strong = view.findViewById(R.id.strongTv);
        vigilant = view.findViewById(R.id.vigilantTv);
        resolute = view.findViewById(R.id.resoluteTv);
        accRollBtn = view.findViewById(R.id.rollAccBtn);
        cunRollBtn = view.findViewById(R.id.rollCunBtn);
        disRollBtn = view.findViewById(R.id.rollDisBtn);
        perRollBtn = view.findViewById(R.id.rollPerBtn);
        quiRollBtn = view.findViewById(R.id.rollQuiBtn);
        resRollBtn = view.findViewById(R.id.rollResBtn);
        strRollBtn = view.findViewById(R.id.rollStrBtn);
        vigRollBtn = view.findViewById(R.id.rollVigBtn);
        saveCharBtn = view.findViewById(R.id.saveCharBtn);
        spellsTabBtn = view.findViewById(R.id.spellTabBtn);
        itemsTabBtn = view.findViewById(R.id.itemsTabBtn);
        touBtnUp = view.findViewById(R.id.touBtnUp);
        touBtnDwn = view.findViewById(R.id.touBtnDwn);

        accRollBtn.setOnClickListener(this);
        cunRollBtn.setOnClickListener(this);
        disRollBtn.setOnClickListener(this);
        perRollBtn.setOnClickListener(this);
        quiRollBtn.setOnClickListener(this);
        resRollBtn.setOnClickListener(this);
        strRollBtn.setOnClickListener(this);
        vigRollBtn.setOnClickListener(this);
        spellsTabBtn.setOnClickListener(this);
        itemsTabBtn.setOnClickListener(this);
        touBtnUp.setOnClickListener(this);
        touBtnDwn.setOnClickListener(this);


        int accMod = calculateModifier(mCharacter.getAccurate());
        int disMod = calculateModifier(mCharacter.getDiscreet());
        int cunMod = calculateModifier(mCharacter.getCunning());
        int quiMod = calculateModifier(mCharacter.getQuick());
        int resMod = calculateModifier(mCharacter.getResolute());
        int perMod = calculateModifier(mCharacter.getPersuasive());
        int strMod = calculateModifier(mCharacter.getStrong());
        int vigMod = calculateModifier(mCharacter.getVigilant());

        charName.setText(mCharacter.getName());
        race.setText(mCharacter.getRace());
        shadow.setText(mCharacter.getShadow());
        occupation.setText(mCharacter.getOccupation());
        toughness.setText(String.valueOf(mCharacter.getToughness()));
        painThreshold.setText(String.valueOf(mCharacter.getPainThreshold()));
        corruptionThreshold.setText(String.valueOf(mCharacter.getCorruptionThreshold()));
        accurate.setText(String.valueOf(mCharacter.getAccurate())+ "(Mod: " + accMod + ")");
        cunning.setText(String.valueOf(mCharacter.getCunning())+ "(Mod: " + cunMod + ")");
        discreet.setText(String.valueOf(mCharacter.getDiscreet())+ "(Mod: " + disMod + ")");
        quick.setText(String.valueOf(mCharacter.getQuick())+ "(Mod: " + quiMod + ")");
        persuasive.setText(String.valueOf(mCharacter.getPersuasive())+ "(Mod: " + perMod + ")");
        strong.setText(String.valueOf(mCharacter.getStrong())+ "(Mod: " + strMod + ")");
        vigilant.setText(String.valueOf(mCharacter.getVigilant())+ "(Mod: " + vigMod + ")");
        resolute.setText(String.valueOf(mCharacter.getResolute())+ "(Mod: " + resMod + ")");

    }

    public int calculateModifier(int attribute){

        double modifier = 5-(((attribute-5)/2)*2);

        return (int)modifier;
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

    @Override
    public void onClick(View view) {

        int random;

        switch (view.getId()){
            case R.id.rollAccBtn:
                random = new Random().nextInt(20) + 1;

                if(random <= mCharacter.getAccurate()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollCunBtn:
                random = new Random().nextInt(20) + 1;

                if(random <= mCharacter.getDiscreet()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollDisBtn:
                random = new Random().nextInt(20) + 1;

                if(random <= mCharacter.getDiscreet()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollPerBtn:
                random = new Random().nextInt(21) + 1;

                if(random <= mCharacter.getPersuasive()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollQuiBtn:
                random = new Random().nextInt(21) + 1;

                if(random <= mCharacter.getQuick()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollResBtn:
                random = new Random().nextInt(21) + 1;

                if(random <= mCharacter.getResolute()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollStrBtn:
                random = new Random().nextInt(21) + 1;

                if(random <= mCharacter.getStrong()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rollVigBtn:
                random = new Random().nextInt(21) + 1;

                if(random <= mCharacter.getVigilant()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;

            /*case R.id.saveCharBtn:
                random = new Random().nextInt(21) + 1;

                if(random < mCharacter.getVigilant()){

                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Hit!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Rolling 1d20... " + random + "! Miss!", Toast.LENGTH_SHORT).show();
                }
                break;*/

            case R.id.spellTabBtn:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, SpellsManagement.newInstance(mCharacter)).addToBackStack(null).commit();
                break;

            case R.id.itemsTabBtn:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, ItemManagement.newInstance(mCharacter)).addToBackStack(null).commit();
                break;
            case R.id.touBtnUp:

                int charId = mCharacter.getId();
                String tough = toughness.getText().toString();
                int currTou = Integer.parseInt(tough);
                currTou++;
                toughness.setText(String.valueOf(currTou));
                mCharacter.setToughness(currTou);
                CharacterController.updateToughness(currTou, charId);
                break;

            case R.id.touBtnDwn:

                charId = mCharacter.getId();
                tough = toughness.getText().toString();
                currTou = Integer.parseInt(tough);
                currTou--;
                toughness.setText(String.valueOf(currTou));
                mCharacter.setToughness(currTou);
                CharacterController.updateToughness(currTou, charId);
                break;
        }

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
