package aiman.elbouayadi.countries;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="Country")

public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name="text")
    private String text;

    @ColumnInfo(name="capital")
    private String capital;

    @ColumnInfo(name = "habitants")
    private Float habitants;
    public int getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public Float getHabitants() {
        return habitants;
    }

    public String getCapital() {
        return capital;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setHabitants(Float habitants) {
        this.habitants = habitants;
    }

    public void setText(String text) {
        this.text = text;
    }
}
