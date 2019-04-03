package teststuff.studio.com.teststuff.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.controllers.ItemController;
import teststuff.studio.com.teststuff.Database.controllers.SpellController;
import teststuff.studio.com.teststuff.R;

public class SplashActivity extends AppCompatActivity {

    private static final String FIRSTRUN = "firstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences(FIRSTRUN, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        boolean firstStart = preferences.getBoolean("firstStart", true);

        if(firstStart){

            startItemDb();
            startSpellDb();

            editor.putBoolean("firstStart", false).apply();

        }

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void startItemDb(){

        ItemController.saveItem("Bastard Sword", "A big sword", "Weapon", 10);
        ItemController.saveItem("Mace", "A shaft with a spiked metal ball in it", "Weapon", 5);
        ItemController.saveItem("Axe", "The ultimate cutting machine", "Weapon", 8);
        ItemController.saveItem("Staff", "For your fireball needs", "Weapon", 11);
        ItemController.saveItem("Wolf skin", "Wolf pelts for the cold", "Armor", 10);
        ItemController.saveItem("Bandages", "For boo-hoos", "Equipment", 2);
        ItemController.saveItem("Bear trap", "Sleep comfy on the woods", "Equipment", 5);
        ItemController.saveItem("Drinking horm", "Because what's wrong with tankards?", "Equipment", 5);

    }

    public void startSpellDb(){

        SpellController.saveSpell("Acrobat (Novice)", "Sample Description");
        SpellController.saveSpell("Acrobat (Adept)", "Sample Description");
        SpellController.saveSpell("Acrobat (Master)", "Sample Description");
        SpellController.saveSpell("Alchemist (Novice)", "Sample Description");
        SpellController.saveSpell("Alchemist (Adept)", "Sample Description");
        SpellController.saveSpell("Alchemist (Master)", "Sample Description");
    }
}
