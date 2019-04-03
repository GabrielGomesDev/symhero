package teststuff.studio.com.teststuff.Activity;

import android.arch.persistence.room.Room;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import teststuff.studio.com.teststuff.Database.AppDatabase;
import teststuff.studio.com.teststuff.R;

public class MainActivity extends AppCompatActivity implements Home_Fragment.OnFragmentInteractionListener, Details_Fragment.OnFragmentInteractionListener,
Load_Screen.OnFragmentInteractionListener, Attributes_Fragment.OnFragmentInteractionListener, SpellFragment.OnFragmentInteractionListener,
        ItemFragment.OnFragmentInteractionListener, CharacterSheet.OnFragmentInteractionListener, SpellsManagement.OnFragmentInteractionListener,
        ItemManagement.OnFragmentInteractionListener, ElementDescription.OnFragmentInteractionListener{

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Revisar variaveis e classes. Utilizar nomes camelCase e nao snake_case.

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_Container) != null){

            if(savedInstanceState != null){

                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_Container, new Home_Fragment()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
