package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.time.Instant;

@Entity(tableName = "reactions", primaryKeys = {"postId", "stampLong", "userId"})
public class Reaction
{
    //--------------------
    // ATTRIBUTES
    //--------------------

    @NonNull
    private String userId;

    @NonNull
    private int postId;

    // The type of reaction
    private int type;

    @NonNull
    private long stampLong;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public Reaction(long stampLong /*Use Instant.now().getEpochSecond()*/, int type, String userId, int postId)
    {
        this.type = type;
        this.stampLong = stampLong;
        this.userId = userId;
        this.postId = postId;
    }

    //--------------------
    // Get Methods
    //--------------------

    public int getPostId() { return this.postId; }
    public String getUserId() { return this.userId; }
    public long getStampLong() { return this.stampLong; }
    public int getType() { return this.type; }
    public Timestamp getTimestamp() { return new Timestamp(stampLong); }
}
