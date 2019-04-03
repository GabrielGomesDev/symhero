package teststuff.studio.com.teststuff.Database.controllers;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Spellbook;

public class SpellBookController {


    public static void saveSpellBook(int characterId, int spellId, String spellName){
        App.myAppDatabase.spellBookDao().insert(new Spellbook(characterId, spellId, spellName));
    }

    public Spellbook getSpellBook(int charId){

        Spellbook spellBook = App.myAppDatabase.spellBookDao().getSpellBook(charId);

        return spellBook;

    }

    public static List<Integer> getSpellBookIds(){

        List<Integer> spellBookIds = App.myAppDatabase.spellBookDao().getSpellBookIds();

        return spellBookIds;
    }

    /*public static List<Integer> getSpellOnSpellBookIds(int sBookId){
        List<Integer> spells = App.myAppDatabase.spellBookDao().getSpellOnSpellBookIds(sBookId);

        return spells;
    }*/

    public static List<Integer> getCharacterSpells(int charId){
        List<Integer> characterSpells = App.myAppDatabase.spellBookDao().getCharacterSpells(charId);

        return characterSpells;
    }

    public static List<String> retrieveSpellNames(int characterId){
        List<String> retrievedSpellIds = App.myAppDatabase.spellBookDao().retrieveSpellNames(characterId);

        return retrievedSpellIds;
    }

    public static void deleteEntry(int charId, int spellId){

        App.myAppDatabase.spellBookDao().deleteEntry(charId, spellId);

    }

    public static void deleteCharacter(int charId){

        App.myAppDatabase.spellBookDao().deleteCharacter(charId);
    }

}
