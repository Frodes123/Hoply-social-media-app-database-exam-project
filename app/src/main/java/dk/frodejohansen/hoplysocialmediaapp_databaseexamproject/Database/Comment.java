package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "comments", primaryKeys = {"postID", "userID", "timestampLong"}, foreignKeys =
        {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userID", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Post.class, parentColumns = "id", childColumns = "postID", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
        })
public class Comment
{
    //--------------------
    // ATTRIBUTES
    //--------------------

    // The PostID of the post that this comment is a comment to
    @NonNull
    private int postID;

    @NonNull
    private String userID;

    private String commentText;

    @NonNull
    private long timestampLong;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public Comment(String userID, int postID, String commentText, long timestampLong/*Use Instant.now().getEpochSecond()*/)
    {
        this.postID = postID;
        this.userID = userID;
        this.commentText = commentText;
        this.timestampLong = timestampLong;
    }

    //--------------------
    // Get Methods
    //--------------------

    public String getUserID() {return this.userID;}
    public int getPostID() {return this.postID;}
    public String getCommentText() {return this.commentText;}
    public Timestamp getTimestamp() {return new Timestamp(timestampLong);}
    public long getTimestampLong() {return this.timestampLong;}
}
