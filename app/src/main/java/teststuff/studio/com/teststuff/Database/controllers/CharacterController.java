package teststuff.studio.com.teststuff.Database.controllers;

import java.util.List;

import teststuff.studio.com.teststuff.App;
import teststuff.studio.com.teststuff.Database.Character;

public class CharacterController {

    public static void saveCharacter(Character character){
        App.myAppDatabase.charDao().insert(character);
    }

    public static List<Character> getCharacters(){
        List<Character> characterList = App.myAppDatabase.charDao().getCharacters();

        return characterList;

    }

    public static void updateCharacter(Character character){
        App.myAppDatabase.charDao().update(character);
    }

    public static int getCharacterId(int id){
        //TODO: Nao criar nova instancia somente para retornar.
        int results = App.myAppDatabase.charDao().getCharacterId(id);

        return results;
    }

    public static String getCurrentCharacter(String name, String occupation, String race){

        return App.myAppDatabase.charDao().getCurrentCharacter(name, occupation, race);

    }

    public static void updateFunds(int funds, int charId){

        App.myAppDatabase.charDao().updateFunds(funds, charId);
    }

    public static int getFunds(int charId){

        int funds = App.myAppDatabase.charDao().getFunds(charId);

        return funds;
    }

    public static List<String> getNames(){

        List<String> names = App.myAppDatabase.charDao().getNames();

        return names;
    }

    public static void deleteCharacter(int charId){

        App.myAppDatabase.charDao().deleteCharacter(charId);

    }

    public static void updateToughness(int toughness, int charId){

        App.myAppDatabase.charDao().updateToughess(toughness, charId);
    }
}
