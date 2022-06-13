package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "posts", foreignKeys =
        {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userID", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
        })
public class Post
{
    //--------------------
    // ATTRIBUTES
    //--------------------


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userID;

    private String content;

    private final long stampLong;

    //--------------------
    // CONSTRUCTOR
    //--------------------


    public Post(String userID, String content,long stampLong /*Use Instant.now().getEpochSecond()*/, int id /*Set to 0 when using this Method, Room should autogenerate*/)
    {
        this.userID = userID;
        this.content = content;
        this.stampLong = stampLong;
        this.id = id;
    }

    //--------------------
    // Get Methods
    //--------------------

    public String getContent() { return this.content; }
    public long getStampLong() { return this.stampLong; }
    public Timestamp getStamp() { return new Timestamp(stampLong); }
    public int getId() { return this.id; }
    public String getUserID() { return this.userID; }
}
