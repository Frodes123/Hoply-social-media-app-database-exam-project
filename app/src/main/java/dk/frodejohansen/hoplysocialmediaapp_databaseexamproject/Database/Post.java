package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.time.Instant;

@Entity(tableName = "posts")
public class Post
{
    //--------------------
    // ATTRIBUTES
    //--------------------


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    //TODO: UserID

    private String content;

    private final long stampLong;

    //--------------------
    // CONSTRUCTOR
    //--------------------


    public Post(String content,long stampLong /*Use Instant.now().getEpochSecond()*/, int id /*Set to 0 when using this Method, Room should autogenerate*/)
    {
        // TODO: ID
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
}
