package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

@Dao
public interface UserDAO
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("DELETE FROM users")
    void deleteAll();


    @Query("SELECT * FROM users ORDER BY id ASC")
    List<User> getAlphabetizedUsers();

    @Query("SELECT id FROM users")
    List<String> getUserIds();

    // If this is 1 the id already exists.
    @Query("SELECT id FROM users WHERE id = :ID")
    // TODO: See if this still works after SuppressWarnings
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<String> getUserMatchingID(String ID);

    // Get hashed password from username
    @Query("SELECT password FROM users WHERE id = :ID")
    List<String> getPasswordFromID(String ID);

}
