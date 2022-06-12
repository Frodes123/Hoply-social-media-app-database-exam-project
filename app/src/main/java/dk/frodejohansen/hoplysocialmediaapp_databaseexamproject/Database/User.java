package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(tableName = "users")
public class User
{
    //--------------------
    // ATTRIBUTES
    //--------------------

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    private String name;
    private long stampLong;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public User(@NonNull String id, @NonNull String name, long stampLong)
    {
        this.id = id;
        this.name = name;
        this.stampLong = stampLong;
    }


    //--------------------
    // Get Methods
    //--------------------
    public String getName()
    {
        return this.name;
    }
    public long getStampLong() { return this.stampLong; }
    public String getId(){ return this.id; }

    public Timestamp getTimestamp()
    {
        return new Timestamp(stampLong);
    }

}
