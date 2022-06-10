package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.time.Instant;

@Entity(tableName = "reactions", primaryKeys = {"post_id", "stamp"})
public class Reaction
{
    //--------------------
    // ATTRIBUTES
    //--------------------

    // TODO: UserID

    @NonNull
    private int post_id;

    // The type of reaction
    private int type;

    private Timestamp stamp;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public Reaction(int type, Post post)
    {
        this.type = type;
        this.stamp = new Timestamp(Instant.now().getEpochSecond());
        this.post_id = post.getId();
    }

    //--------------------
    // Get Methods
    //--------------------

    public int getId(){return this.post_id;}
}
