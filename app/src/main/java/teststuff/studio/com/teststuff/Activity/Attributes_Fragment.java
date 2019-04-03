package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Character;
import teststuff.studio.com.teststuff.Database.Spellbook;
import teststuff.studio.com.teststuff.Database.controllers.CharacterController;
import teststuff.studio.com.teststuff.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Attributes_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Attributes_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Attributes_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String characterKey = "new_char";
    private Character character;

    private static Character mCharacter;

    //Screen Members
    private TextView accValue;
    private TextView cunValue;
    private TextView disValue;
    private TextView perValue;
    private TextView quiValue;
    private TextView resValue;
    private TextView strValue;
    private TextView vigValue;
    private TextView pointsRemaining;
    private ImageView accUp;
    private ImageView accDwn;
    private ImageView cunUp;
    private ImageView cunDwn;
    private ImageView disUp;
    private ImageView disDwn;
    private ImageView perUp;
    private ImageView perDwn;
    private ImageView resUp;
    private ImageView resDwn;
    private ImageView strUp;
    private ImageView strDwn;
    private ImageView quiUp;
    private ImageView quiDwn;
    private ImageView vigUp;
    private ImageView vigDwn;
    private ImageView toSpells;
    private int acc = 5;
    private int cun = 5;
    private int dis = 5;
    private int per = 5;
    private int qui = 5;
    private int res = 5;
    private int str = 5;
    private int vig = 5;
    private final int MAX = 15;
    private final int MIN = 5;
    private int totalPoints = 40;

    private OnFragmentInteractionListener mListener;

    public Attributes_Fragment() {
        // Required empty public constructor
    }

    public static Attributes_Fragment newInstance(Character character) {
        Attributes_Fragment fragment = new Attributes_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_attributes_, container, false);

        return view;

    }

    //TODO: Achar uma solucao pra isso aqui.
    /*private View.OnClickListener getListener(final int attribute, final TextView txtLabel) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = attribute;
                if(count <= 14 && totalPoints != 0) {
                    count++;
                    totalPoints--;
                    txtLabel.setText(Integer.toString(acc));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }*/

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        accValue = view.findViewById(R.id.accValue);
        disValue = view.findViewById(R.id.disValue);
        cunValue = view.findViewById(R.id.cunValue);
        perValue = view.findViewById(R.id.perValue);
        quiValue = view.findViewById(R.id.quiValue);
        resValue = view.findViewById(R.id.resValue);
        strValue = view.findViewById(R.id.strValue);
        vigValue = view.findViewById(R.id.vigValue);
        accUp = view.findViewById(R.id.accUp);
        accDwn = view.findViewById(R.id.accDwn);
        cunUp = view.findViewById(R.id.cunUp);
        cunDwn = view.findViewById(R.id.cunDwn);
        disUp = view.findViewById(R.id.disUp);
        disDwn = view.findViewById(R.id.disDwn);
        perUp = view.findViewById(R.id.perUp);
        perDwn = view.findViewById(R.id.perDwn);
        quiUp = view.findViewById(R.id.quiUp);
        quiDwn = view.findViewById(R.id.quiDwn);
        resUp = view.findViewById(R.id.resUp);
        resDwn = view.findViewById(R.id.resDwn);
        strUp = view.findViewById(R.id.strUp);
        strDwn = view.findViewById(R.id.strDwn);
        vigUp = view.findViewById(R.id.vigUp);
        vigDwn = view.findViewById(R.id.vigDwn);
        pointsRemaining = view.findViewById(R.id.pointsRemain);
        toSpells = view.findViewById(R.id.toSpells);

        pointsRemaining.setText(Integer.toString(totalPoints));

        accValue.setText(Integer.toString(acc));
        cunValue.setText(Integer.toString(cun));
        disValue.setText(Integer.toString(dis));
        perValue.setText(Integer.toString(per));
        quiValue.setText(Integer.toString(qui));
        resValue.setText(Integer.toString(res));
        strValue.setText(Integer.toString(str));
        vigValue.setText(Integer.toString(vig));

        accUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(acc <= 14 && totalPoints != 0) {
                    acc++;
                    totalPoints--;
                    accValue.setText(Integer.toString(acc));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        accDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(acc >= 6 && totalPoints != 0) {
                    acc--;
                    totalPoints++;
                    accValue.setText(Integer.toString(acc));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cunUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cun <= 14 && totalPoints != 0) {
                    cun++;
                    totalPoints--;
                    cunValue.setText(Integer.toString(cun));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cunDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cun >= 6 && totalPoints != 0) {
                    cun--;
                    totalPoints++;
                    cunValue.setText(Integer.toString(cun));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });
        disUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dis <= 14 && totalPoints != 0) {
                    dis++;
                    totalPoints--;
                    disValue.setText(Integer.toString(dis));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        disDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dis >= 6 && totalPoints != 0) {
                    dis--;
                    totalPoints++;
                    disValue.setText(Integer.toString(dis));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });
        perUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(per <= 14 && totalPoints != 0) {
                    per++;
                    totalPoints--;
                    perValue.setText(Integer.toString(per));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        perDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(per >= 6 && totalPoints != 0) {
                    per--;
                    totalPoints++;
                    perValue.setText(Integer.toString(per));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        quiUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(qui <= 14 && totalPoints != 0) {
                    qui++;
                    totalPoints--;
                    quiValue.setText(Integer.toString(qui));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        quiDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(qui >= 6 && totalPoints != 0) {
                    qui--;
                    totalPoints++;
                    quiValue.setText(Integer.toString(qui));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(res <= 14 && totalPoints != 0) {
                    res++;
                    totalPoints--;
                    resValue.setText(Integer.toString(res));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(res >= 6 && totalPoints != 0) {
                    res--;
                    totalPoints++;
                    resValue.setText(Integer.toString(res));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        strUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(str <= 14 && totalPoints != 0) {
                    str++;
                    totalPoints--;
                    strValue.setText(Integer.toString(str));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        strDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(str >= 6 && totalPoints != 0) {
                    str--;
                    totalPoints++;
                    strValue.setText(Integer.toString(str));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });
        vigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(vig <= 14 && totalPoints != 0) {
                    vig++;
                    totalPoints--;
                    vigValue.setText(Integer.toString(vig));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vigDwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(vig >= 6 && totalPoints != 0) {
                    vig--;
                    totalPoints++;
                    vigValue.setText(Integer.toString(vig));
                    pointsRemaining.setText(Integer.toString(totalPoints));
                } else {
                    Toast.makeText(getContext(), "Attributes cannot go below 5 or above 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toSpells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalPoints != 0){
                    Toast.makeText(getContext(), "You need to distribute all your points", Toast.LENGTH_SHORT).show();
                } else {

                    mCharacter.setAccurate(acc);
                    mCharacter.setCunning(cun);
                    mCharacter.setDiscreet(dis);
                    mCharacter.setPersuasive(per);
                    mCharacter.setQuick(qui);
                    mCharacter.setResolute(res);
                    mCharacter.setStrong(str);
                    mCharacter.setVigilant(vig);
                    mCharacter.setDefense(qui);
                    if(str > 10){
                        mCharacter.setToughness(str);
                    } else {
                        mCharacter.setToughness(10);
                    }
                    int painThreshold = str/2;
                    mCharacter.setPainThreshold(painThreshold);
                    int corrupThreshold = res/2;
                    mCharacter.setCorruptionThreshold(corrupThreshold);
                    mCharacter.setFunds(500);

                    if(!isCreated(mCharacter.getId())){
                        mCharacter.setId((int) App.myAppDatabase.charDao().insert(mCharacter));
                    }

                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_Container, SpellFragment.newInstance(mCharacter)).addToBackStack(null).commit();

                }

            }
        });


    }

    public Boolean isCreated(int id){

        boolean isCreated = false;

        if(CharacterController.getCharacterId(id) != 0){

            isCreated = true;

        }
        return isCreated;
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
