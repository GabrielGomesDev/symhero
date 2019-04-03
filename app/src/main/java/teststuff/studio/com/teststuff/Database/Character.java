package teststuff.studio.com.teststuff.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Character", indices = @Index(value = "characterID")/*, foreignKeys = {@ForeignKey(entity = Inventory.class, parentColumns = "inventoryID", childColumns = "inventory_ID", onDelete = CASCADE),
@ForeignKey(entity = Spellbook.class, parentColumns = "spellBookID", childColumns = "spellBook_ID", onDelete = CASCADE)}*/)

public class Character implements Serializable {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "characterID")
    public int id;

    public String name;

    public String race;

    public String occupation;

    public String shadow;

    public int accurate;

    public int cunning;

    public int discreet;

    public int persuasive;

    public int quick;

    public int resolute;

    public int strong;

    public int vigilant;

    public int toughness;

    public int painThreshold;

    public int corruptionThreshold;

    public int defense;

    public int funds;

    public Character() {
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getShadow() {
        return shadow;
    }

    public void setShadow(String shadow) {
        this.shadow = shadow;
    }

    public int getAccurate() {
        return accurate;
    }

    public void setAccurate(int accurate) {
        this.accurate = accurate;
    }

    public int getCunning() {
        return cunning;
    }

    public void setCunning(int cunning) {
        this.cunning = cunning;
    }

    public int getDiscreet() {
        return discreet;
    }

    public void setDiscreet(int discreet) {
        this.discreet = discreet;
    }

    public int getPersuasive() {
        return persuasive;
    }

    public void setPersuasive(int persuasive) {
        this.persuasive = persuasive;
    }

    public int getQuick() {
        return quick;
    }

    public void setQuick(int quick) {
        this.quick = quick;
    }

    public int getResolute() {
        return resolute;
    }

    public void setResolute(int resolute) {
        this.resolute = resolute;
    }

    public int getStrong() {
        return strong;
    }

    public void setStrong(int strong) {
        this.strong = strong;
    }

    public int getVigilant() {
        return vigilant;
    }

    public void setVigilant(int vigilant) {
        this.vigilant = vigilant;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getPainThreshold() {
        return painThreshold;
    }

    public void setPainThreshold(int painThreshold) {
        this.painThreshold = painThreshold;
    }

    public int getCorruptionThreshold() {
        return corruptionThreshold;
    }

    public void setCorruptionThreshold(int corruptionThreshold) {
        this.corruptionThreshold = corruptionThreshold;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }
}
