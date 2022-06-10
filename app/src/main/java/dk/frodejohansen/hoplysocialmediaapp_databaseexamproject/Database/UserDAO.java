package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("DELETE FROM users")
    void deleteAll();


    @Query("SELECT * FROM users ORDER BY name ASC")
    LiveData<List<User>> getAlphabetizedUsers();

    // If this is 1 the id already exists.
    @Query("SELECT id FROM users WHERE id = :ID")
    LiveData<List<User>> getUserMatchingID(String ID);

}
