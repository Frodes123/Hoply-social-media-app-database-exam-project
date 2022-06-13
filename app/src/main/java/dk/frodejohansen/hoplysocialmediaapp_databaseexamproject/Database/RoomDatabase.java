package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Post.class, Reaction.class, Comment.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase
{
    // DAOs:
    public abstract UserDAO userDAO();
    public abstract PostDAO postDAO();
    public abstract ReactionDAO reactionDAO();
    public abstract CommentDAO commentDAO();

    public static volatile RoomDatabase INSTANCE;

    // Just set to 4 for now
    // TODO: Look into:
    private static final int NumberOfThreads = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NumberOfThreads);

    static RoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (RoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    //TODO allowMainThreadQueries er fyfy. fiks.
                    // INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "users").allowMainThreadQueries().build();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "users").build();
                }
            }
        }
        return INSTANCE;
    }
}