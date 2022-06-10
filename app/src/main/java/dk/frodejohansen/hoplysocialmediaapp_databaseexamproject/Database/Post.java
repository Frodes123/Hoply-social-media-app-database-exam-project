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

    @PrimaryKey
    @NonNull
    private int id;

    //TODO: UserID

    private String content;

    private Timestamp stamp;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public Post(String content)
    {
        // TODO: ID
        this.content = content;
        stamp = new Timestamp(Instant.now().getEpochSecond());
    }

    //--------------------
    // Get Methods
    //--------------------

    public String getContent() { return this.content; }
    public Timestamp getStamp() { return this.stamp; }
    public int getId() { return this.id; }
}
