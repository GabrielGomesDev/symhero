package teststuff.studio.com.teststuff.Database.controllers;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Item;
import teststuff.studio.com.teststuff.Database.Spell;

public class SpellController {

    public static void saveSpell(String name, String description) {
        App.myAppDatabase.spellDao().insert(new Spell(name, description));
    }

    public static List<String> getSpellsNames(){

        List<String> spellNames = App.myAppDatabase.spellDao().getSpellsNames();

        return spellNames;
    }

    public static List<Integer> getSpellsIds(){
        List<Integer> spellIds = App.myAppDatabase.spellDao().getSpellsIds();

        return spellIds;
    }

    public static Spell getData(int spellId){

        Spell spell = App.myAppDatabase.spellDao().getData(spellId);

        return spell;
    }

}
