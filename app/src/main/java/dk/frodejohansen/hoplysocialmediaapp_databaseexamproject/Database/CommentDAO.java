package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentDAO
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertComment(Comment comment);

    @Delete
    void deleteComment(Comment comment);

    // Delete all comments from a specific post.
    @Query("DELETE FROM comments WHERE postID = :postID")
    void deletePostComments(int postID);

    @Query("SELECT commentText FROM comments WHERE postID = :postId ORDER BY timeStampLong DESC")
    List<String> getPostComments(int postId);
}
