package aiman.elbouayadi.countries;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    @Insert(onConflict =REPLACE)
    void insert(MainData mainData);
    @Delete
    void delete(MainData mainData);

    @Query("DELETE FROM Country")
    void reset();

    @Query("UPDATE Country SET text= :sText,capital=:cText,habitants=:nText  where ID=:sID")
    void update(int sID, String sText, String cText, Float nText);

    @Query("SELECT * FROM Country")
    List<MainData> getAll();


}
