package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReactionDAO
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertReaction(Reaction reaction);

    @Query("DELETE FROM reactions")
    void deleteAll();

    @Delete
    void deleteReaction(Reaction reaction);

    // Get all reactions from a given Post
    @Query("SELECT type FROM reactions WHERE postId = :postID")
    LiveData<List<Integer>> getPostReactions(int postID);

    // Returns the postIDs from all posts the user has reacted to.
    @Query("SELECT postId FROM reactions WHERE userId = :userIdToCheck")
    LiveData<List<Integer>> getUserReactionPostIDs(String userIdToCheck);


    // TODO: Add more SQL
}
