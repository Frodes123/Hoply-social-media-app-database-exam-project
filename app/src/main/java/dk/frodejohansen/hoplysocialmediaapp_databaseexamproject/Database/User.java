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
    private String user_id;

    private String name;
    private String password;
    private long stampLong;

    //--------------------
    // CONSTRUCTOR
    //--------------------

    public User(@NonNull String user_id, @NonNull String name, @NonNull String password, long stampLong)
    {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
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
    public String getUser_id(){ return this.user_id; }
    public String getPassword() { return this.password; }

    public Timestamp getTimestamp()
    {
        return new Timestamp(stampLong);
    }

}
