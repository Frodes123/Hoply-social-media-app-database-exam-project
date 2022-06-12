package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDAO
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPost(Post post);

    // Clear the posts table
    @Query("DELETE FROM posts")
    void deleteAll();

    // Get all posts from a given User
    @Query("SELECT * FROM posts WHERE id = :userID")
    LiveData<List<Post>> getUserPosts(int userID);


}
