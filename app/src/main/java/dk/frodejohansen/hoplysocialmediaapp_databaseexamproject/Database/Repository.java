package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.w3c.dom.Text;

import java.util.List;

public class Repository
{
    // This Class will abstract access to multiple data sources.

    private UserDAO userDAO;
    private PostDAO postDAO;
    private ReactionDAO reactionDAO;
    public RoomDatabase database;

    private LiveData<List<User>> allUsers;

    public Repository(Application application)
    {
        database = RoomDatabase.getDatabase(application);
        userDAO = database.userDAO();
        postDAO = database.postDAO();
        reactionDAO = database.reactionDAO();

        allUsers = userDAO.getAlphabetizedUsers();
    }
    //--------------------
    // User Methods
    //--------------------
    public void insertUser(User user)
    {
        userDAO.insertUser(user);
    }

    //--------------------
    // Reaction Methods
    //--------------------

    // Returns true if the given user has already reacted to the chosen post.
    public boolean hasUserReacted(Post post, User user)
    {
        // Get the list of all the postIds that the user has reacted to.
        List<Integer> userReactionPostIDs = reactionDAO.getUserReactionPostIDs(user.getId()).getValue();

        if (userReactionPostIDs.contains(post.getId()))
            return true;
        return false;
    }

    // Returns all users, alphabetized
    LiveData<List<User>> getAllUsers()
    {
        return allUsers;
    }

    // Checks whether a user with the given ID already exists
    public boolean listContainsUserID(String ID)
    {
        if (userDAO.getUserMatchingID(ID).contains(ID))
        {
            return true;
        }
        return false;
    }

    // TODO: Implement Threading with the DAO statements:

    // Defining the User.Insert SQL statement.
    public void insert(User user)
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> {userDAO.insertUser(user);});
    }
}
