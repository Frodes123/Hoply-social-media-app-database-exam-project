package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(tableName = "users")
class User
{
    //--------------------
    // ATTRIBUTES
    //--------------------

    @PrimaryKey
    @NonNull
    // TODO: Can it really be the case that this must be a String?
    private String id;

    private String name;

    private Timestamp stamp;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public User(@NonNull String name)
    {
        this.name = name;
        stamp = new Timestamp(Instant.now().getEpochSecond());
    }


    //--------------------
    // Get Methods
    //--------------------
    public String getName()
    {
        return this.name;
    }
    public Timestamp getStamp() { return this.stamp; }

}
